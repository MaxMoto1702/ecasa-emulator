package ru.softwarecom.uspn.emulators.ecasa;

import com.rstyle.pfr.ecasa.integration.jaxb.ApplicationType;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.util.Assert;
import ru.softwarecom.uspn.emulators.ecasa.domain.*;
import ru.softwarecom.uspn.emulators.ecasa.repository.*;

import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

//@DataJpaTest(showSql = false)
//@ActiveProfiles("test")
public class EcasaUspnModelTests {
    private static final Logger log = getLogger(EcasaUspnModelTests.class);

    @Autowired private EntityManager entityManager;

    @Autowired private EcasaActionTypeRepository actionTypeRepository;
    @Autowired private EcasaApplicationRepository applicationRepository;
    @Autowired private EcasaPolicyRepository policyRepository;
    @Autowired private EcasaResourceRepository resourceRepository;
    @Autowired private EcasaResourceTypeRepository resourceTypeRepository;
    @Autowired private EcasaRoleRepository roleRepository;

//    @Test
    @Commit()
    void run_over_emf() throws JAXBException, IOException {
        String applicationName = "USPN";
        {
            ApplicationType applicationType = (ApplicationType) JAXBContext
                    .newInstance(ApplicationType.class)
                    .createUnmarshaller()
                    .unmarshal(
                            new ClassPathResource("integration/uspn/ecasa.xml")
                                    .getInputStream()
                    );

            final EcasaApplication application = extractApplication(applicationType);
            applicationRepository.save(application);

            applicationName = application.getName();

            final Set<EcasaRole> roles = extractRoles(applicationType, application);
            roleRepository.saveAll(roles);

            Map<String, EcasaRole> mappedRoles = roles
                    .stream()
                    .collect(Collectors.toMap(EcasaRole::getName, role -> role));

            final Set<EcasaResource> resources = extractResources(applicationType, application);
            resourceRepository.saveAll(resources);

            Map<String, EcasaResource> mappedResources = resources
                    .stream()
                    .collect(Collectors.toMap(EcasaResource::getName, resource -> resource));

            Set<EcasaPolicy> policies = extractPolicies(applicationType, application, mappedRoles, mappedResources);

            policyRepository.saveAll(policies);
        }

        TestTransaction.end();
        log.debug("End save transaction");

        log.debug("Start delete transaction");
        TestTransaction.start();

        {
//            EcasaApplication application = entityManager
//                    .createQuery(
//                            "select i from EcasaApplication i where i.name = :name",
//                            EcasaApplication.class
//                    )
//                    .setParameter(
//                            "name",
//                            APPLICATION_NAME
//                    )
//                    .getSingleResult();
//
//            List<EcasaPolicy> policies = entityManager
//                    .createQuery(
//                            "select p from EcasaPolicy p where p.application = :application",
//                            EcasaPolicy.class
//                    )
//                    .setParameter(
//                            "application",
//                            application
//                    )
//                    .getResultList();
//
//            log.debug("Found policies [ {} ]", policies);
//
//            for (EcasaPolicy policy : policies) {
//                entityManager.remove(
//                        entityManager.find(
//                                EcasaPolicy.class,
//                                policy.getId()
//                        )
//                );
//            }
//
//            List<EcasaResource> resources = entityManager
//                    .createQuery(
//                            "select r from EcasaResource r where r.application = :application",
//                            EcasaResource.class
//                    )
//                    .setParameter(
//                            "application",
//                            application
//                    )
//                    .getResultList();
//
//            for (EcasaResource resource : resources) {
//                entityManager.remove(resource);
//            }
//
//            List<EcasaRole> roles = entityManager
//                    .createQuery(
//                            "select r from EcasaRole r where r.application = :application",
//                            EcasaRole.class
//                    )
//                    .setParameter(
//                            "application",
//                            application
//                    )
//                    .getResultList();
//
//            for (EcasaRole role : roles) {
//                entityManager.remove(role);
//            }
//
//            List<EcasaResourceType> resourceTypes = entityManager
//                    .createQuery(
//                            "select t from EcasaResourceType t where t.application = :application",
//                            EcasaResourceType.class
//                    )
//                    .setParameter(
//                            "application",
//                            application
//                    )
//                    .getResultList();
//
//            for (EcasaResourceType type : resourceTypes) {
//                entityManager.remove(type);
//            }
//
//            List<EcasaActionType> actionTypes = entityManager
//                    .createQuery(
//                            "select t from EcasaActionType t where t.application = :application",
//                            EcasaActionType.class
//                    )
//                    .setParameter(
//                            "application",
//                            application
//                    )
//                    .getResultList();
//
//            for (EcasaActionType type : actionTypes) {
//                entityManager.remove(type);
//            }
//
//            entityManager.remove(application);
            EcasaApplication application = applicationRepository.findByName(applicationName);
            policyRepository.deleteAllByApplication(application);
            actionTypeRepository.deleteAllByApplication(application);
            roleRepository.deleteAllByApplication(application);
            resourceRepository.deleteAllByApplication(application);
            resourceTypeRepository.deleteAllByApplication(application);
            applicationRepository.delete(application);
        }
        TestTransaction.end();
        log.debug("End delete transaction");

        log.debug("Start check transaction");
        TestTransaction.start();
        {
            assert entityManager
                    .createQuery(
                            "select i from EcasaApplication i where i.name = :name",
                            EcasaApplication.class
                    )
                    .setParameter(
                            "name",
                            applicationName
                    )
                    .getResultList()
                    .size() == 0;
        }
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
