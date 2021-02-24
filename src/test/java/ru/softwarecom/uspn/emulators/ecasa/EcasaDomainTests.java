package ru.softwarecom.uspn.emulators.ecasa;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.transaction.TestTransaction;
import ru.softwarecom.uspn.emulators.ecasa.domain.*;
import ru.softwarecom.uspn.emulators.ecasa.repository.*;

import javax.persistence.EntityManager;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

//@DataJpaTest(showSql = false)
//@ActiveProfiles("test")
public class EcasaDomainTests {
    private static final Logger log = getLogger(EcasaDomainTests.class);
    private static final String APPLICATION_NAME = "Test";

    @Autowired private EntityManager entityManager;

    @Autowired private EcasaActionTypeRepository actionTypeRepository;
    @Autowired private EcasaApplicationRepository applicationRepository;
    @Autowired private EcasaPolicyRepository policyRepository;
    @Autowired private EcasaResourceRepository resourceRepository;
    @Autowired private EcasaResourceTypeRepository resourceTypeRepository;
    @Autowired private EcasaRoleRepository roleRepository;

//    @Test
    @Commit()
    void run_over_emf() {
        {
            EcasaApplication application = new EcasaApplication(
                    APPLICATION_NAME,
                    "Test application",
                    "Description about test application",
                    false
            );

            EcasaResourceType type = new EcasaResourceType(
                    "Test",
                    "Test resource type",
                    "",
                    application
            );

            EcasaResource resource = new EcasaResource(
                    "Test",
                    "Test resource",
                    "",
                    type,
                    application
            );

            EcasaRole role = new EcasaRole(
                    "Test",
                    "Test role",
                    "",
                    application
            );

            EcasaPolicy policy = new EcasaPolicy(
                    "Test",
                    "Test policy",
                    "",
                    false,
                    false,
                    role,
                    application
            );
            entityManager.persist(policy);

            EcasaActionType actionType = new EcasaActionType(
                    "view",
                    application
            );

            EcasaAction action = new EcasaAction(
                    resource,
                    actionType
            );

            policy.getActions()
                    .add(action);
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
            EcasaApplication application = applicationRepository.findByName(APPLICATION_NAME);
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
                            APPLICATION_NAME
                    )
                    .getResultList()
                    .size() == 0;
        }
    }
}
