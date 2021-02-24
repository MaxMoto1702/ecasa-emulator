package ru.softwarecom.uspn.emulators.ecasa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.rstyle.pfr.ecasa.integration.jaxb.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.core.io.ClassPathResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.slf4j.LoggerFactory.getLogger;

class XmlTests {
    private static final Logger log = getLogger(XmlTests.class);

//    @Test
    @Disabled
    void contextLoads() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JaxbAnnotationModule());
        String xml = xmlMapper.writeValueAsString(new ApplicationType());
        log.info("XML: {}", xml);
    }

//    @Test
//    public void whenJavaSerializedToXmlFile_thenCorrect() throws IOException {
//        XmlMapper xmlMapper = new XmlMapper();
//        xmlMapper.writeValue(new File("simple_bean.xml"), new SimpleBean());
//        File file = new File("simple_bean.xml");
//
//    }

//    @Test
    @Disabled
    public void whenJavaGotFromXmlFile_thenCorrect() throws IOException {
        File file = new File("simple_bean.xml");
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JaxbAnnotationModule());
        String xml = inputStreamToString(new ClassPathResource("integration/fake/ecasa.xml").getInputStream());
        ApplicationType value = xmlMapper.readValue(xml, ApplicationType.class);
//        assertTrue(value.getX() == 1 && value.getY() == 2);
    }

    public String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

//    @Test
    @Disabled
    public void jaxb() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(ApplicationType.class);
        ApplicationType applicationType = (ApplicationType) context.createUnmarshaller()
                .unmarshal(new ClassPathResource("integration/uspn/ecasa.xml").getInputStream());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper
                .typeMap(ApplicationType.class, Application.class);
        modelMapper
                .typeMap(RoleType.class, Role.class);
        final Application application = modelMapper.map(applicationType, Application.class);
        application.setRoles(
                applicationType
                        .getRole()
                        .stream()
                        .map(roleType -> modelMapper.map(roleType, Role.class))
                        .collect(Collectors.toList())
        );
        application.setResources(
                applicationType
                        .getResourceType()
                        .stream()
                        .flatMap((Function<ResourceTypeType, Stream<Resource>>) resourceTypeType -> {
                            Resource.Type type = modelMapper.map(resourceTypeType, Resource.Type.class);
                            return resourceTypeType.getResource()
                                    .stream()
                                    .map(resourceType -> {
                                        final Resource resource = modelMapper.map(resourceType, Resource.class);
                                        resource.setType(type);
                                        return resource;
                                    });
                        })
                        .collect(Collectors.toList())
        );

        application.setPolicies(
                applicationType
                        .getPolicy()
                        .stream()
                        .map(policyType -> {
                            try {
                                final Policy policy = modelMapper.map(policyType, Policy.class);
                                policy.setRole(
                                        application
                                                .getRoles()
                                                .stream()
                                                .filter(role -> Objects.equals(role.getName(), policyType.getRoleName()))
                                                .findFirst()
                                                .orElseThrow((Supplier<Throwable>) () -> new NotFoundException("Not found role [ " + policyType.getRoleName() + " ]"))
                                );
                                policy.setActions(
                                        policyType
                                                .getResourceActions()
                                                .stream()
                                                .flatMap((Function<ResourceActionsType, Stream<Policy.Action>>) resourceActionsType -> {
                                                    try {
                                                        final Resource resource  = application
                                                                .getResources()
                                                                .stream()
                                                                .filter(resource1 -> Objects.equals(resource1.getName(), resourceActionsType.getResourceName()))
                                                                .findFirst()
                                                                .orElseThrow((Supplier<Throwable>) () -> new NotFoundException("Not found resource [ " + resourceActionsType.getResourceName() + " ] for policy [ " + policyType.getName() + " ]"));
                                                        return resourceActionsType
                                                                .getResourceTypeAction()
                                                                .stream()
                                                                .map(actionTypeName -> {
                                                                    final Policy.Action action = new Policy.Action();
                                                                    action.setResource(resource);
                                                                    final Policy.Action.Type type = new Policy.Action.Type();
                                                                    type.setName(actionTypeName);
                                                                    action.setType(type);
                                                                    return action;
                                                                });
                                                    } catch (Throwable throwable) {
                                                        throw new RuntimeException(throwable);
                                                    }
                                                })
                                                .collect(Collectors.toList())
                                );
                                return policy;
                            } catch (Throwable throwable) {
                                throw new RuntimeException(throwable);
                            }
                        })
                        .collect(Collectors.toList())
        );
        log.info("Application [ {} ]", application);
//        log.info("Roles [ {} ]", application.getRoles());
//        log.info("Resources [ {} ]", application.getResources());
//        log.info("Application [ {} ]", ReflectionToStringBuilder.toString(applicationType));

    }

    public static class Application {

        private String name;
        private String displayName;
        private List<Role> roles;
        private List<Resource> resources;
        private List<Policy> policies;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public List<Policy> getPolicies() {
            return policies;
        }

        public void setPolicies(List<Policy> policies) {
            this.policies = policies;
        }

        @Override
        public String toString() {
            return "Application{" +
                    "name='" + name + '\'' +
                    ", displayName='" + displayName + '\'' +
                    '}';
        }

        public void setRoles(List<Role> roles) {
            this.roles = roles;
        }

        public List<Role> getRoles() {
            return roles;
        }

        public void setResources(List<Resource> resources) {
            this.resources = resources;
        }

        public List<Resource> getResources() {
            return resources;
        }
    }

    public static class Role {
        protected String name;
        protected String displayName;
        protected String description;
        protected String roleCategoryName;
        protected String parentRoleName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getRoleCategoryName() {
            return roleCategoryName;
        }

        public void setRoleCategoryName(String roleCategoryName) {
            this.roleCategoryName = roleCategoryName;
        }

        public String getParentRoleName() {
            return parentRoleName;
        }

        public void setParentRoleName(String parentRoleName) {
            this.parentRoleName = parentRoleName;
        }

        @Override
        public String toString() {
            return "Role{" +
                    "name='" + name + '\'' +
                    ", displayName='" + displayName + '\'' +
                    ", description='" + description + '\'' +
                    ", roleCategoryName='" + roleCategoryName + '\'' +
                    ", parentRoleName='" + parentRoleName + '\'' +
                    '}';
        }
    }

    public static class Policy {
        protected String name;
        protected String displayName;
        protected String description;
        protected boolean deny;
        protected boolean semanticAnd;
        protected String condition;
        //        protected String roleName;
        private Role role;

        public void setRole(Role role) {
            this.role = role;
        }

        //        protected List<ConditionAttributeType> conditionAttribute;
//        protected List<String> entitlementName;
//        protected List<ResourceActionsType> resourceActions;
        private List<Action> actions;
//        protected List<FilteredResourcesActionsType> filteredResourcesActions;
//        protected List<ObligationType> obligation;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isDeny() {
            return deny;
        }

        public void setDeny(boolean deny) {
            this.deny = deny;
        }

        public boolean isSemanticAnd() {
            return semanticAnd;
        }

        public void setSemanticAnd(boolean semanticAnd) {
            this.semanticAnd = semanticAnd;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public Role getRole() {
            return role;
        }

        public List<Action> getActions() {
            return actions;
        }

        public void setActions(List<Action> actions) {
            this.actions = actions;
        }

        @Override
        public String toString() {
            return "Policy{" +
                    "name='" + name + '\'' +
                    ", displayName='" + displayName + '\'' +
                    ", description='" + description + '\'' +
                    ", deny=" + deny +
                    ", semanticAnd=" + semanticAnd +
                    ", condition='" + condition + '\'' +
                    ", role=" + role +
                    ", actions=" + actions +
                    '}';
        }

        public static class Action {
            private Resource resource;
            private Type type;

            public Resource getResource() {
                return resource;
            }

            public void setResource(Resource resource) {
                this.resource = resource;
            }

            public Type getType() {
                return type;
            }

            public void setType(Type type) {
                this.type = type;
            }

            @Override
            public String toString() {
                return "Action{" +
                        "resource=" + resource +
                        ", type=" + type +
                        '}';
            }

            public static class Type {
                private String name;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                @Override
                public String toString() {
                    return "Type{" +
                            "name='" + name + '\'' +
                            '}';
                }
            }
        }
    }

    public static class Resource {
        private Type type;
        protected String name;
        protected String displayName;
        protected String description;

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "Resource{" +
                    "type=" + type +
                    ", name='" + name + '\'' +
                    ", displayName='" + displayName + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }

        public static class Type {
            protected String name;
            protected String displayName;
            protected String description;
            protected List<String> action;
            protected List<String> attributeName;
            protected List<ResourceType> resource;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDisplayName() {
                return displayName;
            }

            public void setDisplayName(String displayName) {
                this.displayName = displayName;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public List<String> getAction() {
                return action;
            }

            public void setAction(List<String> action) {
                this.action = action;
            }

            public List<String> getAttributeName() {
                return attributeName;
            }

            public void setAttributeName(List<String> attributeName) {
                this.attributeName = attributeName;
            }

            public List<ResourceType> getResource() {
                return resource;
            }

            public void setResource(List<ResourceType> resource) {
                this.resource = resource;
            }

            @Override
            public String toString() {
                return "Type{" +
                        "name='" + name + '\'' +
                        ", displayName='" + displayName + '\'' +
                        ", description='" + description + '\'' +
                        ", action=" + action +
                        ", attributeName=" + attributeName +
                        ", resource=" + resource +
                        '}';
            }
        }
    }

    private class NotFoundException extends Exception {
        public NotFoundException(String message) {
            super(message);
        }
    }
}
