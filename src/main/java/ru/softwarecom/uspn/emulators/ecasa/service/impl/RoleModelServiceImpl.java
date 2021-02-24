package ru.softwarecom.uspn.emulators.ecasa.service.impl;

import com.rstyle.pfr.ecasa.integration.jaxb.ApplicationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.softwarecom.uspn.emulators.ecasa.domain.*;
import ru.softwarecom.uspn.emulators.ecasa.repository.*;
import ru.softwarecom.uspn.emulators.ecasa.service.RoleModelService;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class RoleModelServiceImpl implements RoleModelService {

    private final EcasaActionTypeRepository actionTypeRepository;
    private final EcasaApplicationRepository applicationRepository;
    private final EcasaPolicyRepository policyRepository;
    private final EcasaResourceRepository resourceRepository;
    private final EcasaResourceTypeRepository resourceTypeRepository;
    private final EcasaRoleRepository roleRepository;

    @PostConstruct
    public void setup() throws JAXBException, IOException {
//        loadUspnRoleModel();
    }

    @Transactional
    @Override
    public void loadUspnRoleModel() throws JAXBException, IOException {
        EcasaApplication storedApplication = applicationRepository.findByName("USPN");

        if (storedApplication != null) {
            // удалить EcasaAction
            // удалить EcasaPolicy
            policyRepository.deleteAllByApplication(storedApplication);

            // удалить EcasaActionType
            actionTypeRepository.deleteAllByApplication(storedApplication);

            // удалить EcasaRole
            roleRepository.deleteAllByApplication(storedApplication);

            // удалить EcasaResource
            resourceRepository.deleteAllByApplication(storedApplication);

            // удалить EcasaResourceType
            resourceTypeRepository.deleteAllByApplication(storedApplication);

            // удалить EcasaApplication
            applicationRepository.delete(storedApplication);
        }

        ApplicationType applicationType = (ApplicationType) JAXBContext
                .newInstance(ApplicationType.class)
                .createUnmarshaller()
                .unmarshal(
                        new ClassPathResource("integration/uspn/ecasa.xml")
                                .getInputStream()
                );

        EcasaApplication application = extractApplication(applicationType);
//        applicationRepository.save(application);

        final Set<EcasaRole> roles = extractRoles(applicationType, application);
//        roleRepository.saveAll(roles);

        Map<String, EcasaRole> mappedRoles = roles
                .stream()
                .collect(Collectors.toMap(EcasaRole::getName, role -> role));

        final Set<EcasaResource> resources = extractResources(applicationType, application);
//        resourceRepository.saveAll(resources);

        Map<String, EcasaResource> mappedResources = resources
                .stream()
                .collect(Collectors.toMap(EcasaResource::getName, resource -> resource));

        Set<EcasaPolicy> policies = extractPolicies(applicationType, application, mappedRoles, mappedResources);

        policyRepository.saveAll(policies);
    }

    private Set<EcasaPolicy> extractPolicies(ApplicationType applicationType, EcasaApplication application, Map<String, EcasaRole> mappedRoles, Map<String, EcasaResource> mappedResources) {
        Map<String, EcasaActionType> actionTypes = new HashMap<>();
        return applicationType
                .getPolicy()
                .stream()
                .map(policyType -> extractPolicy(application, mappedRoles, mappedResources, actionTypes, policyType))
                .collect(Collectors.toSet());
    }

    private EcasaPolicy extractPolicy(EcasaApplication application, Map<String, EcasaRole> roles, Map<String, EcasaResource> mappedResources, Map<String, EcasaActionType> actionTypes, com.rstyle.pfr.ecasa.integration.jaxb.PolicyType source) {
        Assert.notNull(source, "");
        Assert.notNull(source.getName(), "");
        Assert.notNull(source.getDisplayName(), "");
        return new EcasaPolicy(
                source.getName(),
                source.getDisplayName(),
                source.getDescription() != null ? source.getDescription() : "",
                source.isDeny(),
                source.isSemanticAnd(),
                roles.computeIfAbsent(
                        source.getRoleName(),
                        s -> {
                            throw new RuntimeException("Not found role [" + source.getRoleName() + "]");
                        }
                ),
                application,
                source
                        .getResourceActions()
                        .stream()
                        .flatMap(resourceActionsType -> resourceActionsType
                                .getResourceTypeAction()
                                .stream()
                                .map(actionTypeName -> extractPolicyAction(application, mappedResources, actionTypes, resourceActionsType, actionTypeName))
                        )
                        .collect(Collectors.toSet())
        );
    }

    private EcasaAction extractPolicyAction(EcasaApplication application, Map<String, EcasaResource> resources, Map<String, EcasaActionType> types, com.rstyle.pfr.ecasa.integration.jaxb.ResourceActionsType resourceActionsType, String actionTypeName) {
        return new EcasaAction(
                resources.computeIfAbsent(
                        resourceActionsType.getResourceName(),
                        s -> {
                            throw new RuntimeException("Not found resource [ " + resourceActionsType.getResourceName() + " ]");
                        }
                ),
                types.computeIfAbsent(
                        actionTypeName,
                        name -> new EcasaActionType(name, application)
                )
        );
    }

    private Set<EcasaResource> extractResources(ApplicationType applicationType, EcasaApplication application) {
        Map<String, EcasaResourceType> resourceTypes = new HashMap<>();
        return applicationType
                .getResourceType()
                .stream()
                .flatMap(resourceTypeType -> resourceTypeType.getResource()
                        .stream()
                        .map(resourceType -> extractResource(application, resourceTypes, resourceTypeType, resourceType)))
                .collect(Collectors.toSet());
    }

    private EcasaResource extractResource(EcasaApplication application, Map<String, EcasaResourceType> resourceTypes, com.rstyle.pfr.ecasa.integration.jaxb.ResourceTypeType resourceTypeType, com.rstyle.pfr.ecasa.integration.jaxb.ResourceType source) {
        Assert.notNull(source, "");
        Assert.notNull(source.getName(), "");
        Assert.notNull(source.getDisplayName(), "");
        return new EcasaResource(
                source.getName(),
                source.getDisplayName(),
                source.getDescription() != null ? source.getDescription() : "",
                resourceTypes.computeIfAbsent(
                        resourceTypeType.getName(),
                        resourceTypeName -> extractResourceType(application, resourceTypeType)),
                application
        );
    }

    private EcasaResourceType extractResourceType(EcasaApplication application, com.rstyle.pfr.ecasa.integration.jaxb.ResourceTypeType source) {
        Assert.notNull(source, "");
        Assert.notNull(source.getName(), "");
        Assert.notNull(source.getDisplayName(), "");
        return new EcasaResourceType(
                source.getName(),
                source.getDisplayName(),
                source.getDescription() != null ? source.getDescription() : "",
                application
        );
    }

    private Set<EcasaRole> extractRoles(ApplicationType applicationType, EcasaApplication application) {
        return applicationType
                .getRole()
                .stream()
                .map(roleType -> extractRole(application, roleType))
                .collect(Collectors.toSet());
    }

    private EcasaRole extractRole(EcasaApplication application, com.rstyle.pfr.ecasa.integration.jaxb.RoleType source) {
        Assert.notNull(source, "");
        Assert.notNull(source.getName(), "");
        Assert.notNull(source.getDisplayName(), "");
        return new EcasaRole(
                source.getName(),
                source.getDisplayName(),
                source.getDescription() != null ? source.getDescription() : "",
                application
        );
    }

    private EcasaApplication extractApplication(ApplicationType source) {
        Assert.notNull(source, "");
        Assert.notNull(source.getName(), "");
        Assert.notNull(source.getDisplayName(), "");
        return new EcasaApplication(
                source.getName(),
                source.getDisplayName(),
                source.getDescription() != null ? source.getDescription() : "",
                source.isRegional()
        );
    }
}
