package ru.softwarecom.uspn.emulators.ecasa;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.transaction.TestTransaction;
import ru.softwarecom.uspn.emulators.ecasa.domain.*;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

//@DataJpaTest(showSql = false)
//@ActiveProfiles("test")
public class EcasaResourceTests {
    private static final Logger log = getLogger(EcasaResourceTests.class);

    @Autowired
    private EntityManager entityManager;

//    @Test
    @Commit()
    void run_over_emf() {
        Long resourceTypeId;
        {
            EcasaApplication application = new EcasaApplication(
                    "Test",
                    "Test application",
                    "Description about test application",
                    false
            );

            EcasaResourceType resourceType = new EcasaResourceType(
                    "Test",
                    "Test resource type",
                    "Resource type for test",
                    application
            );

            EcasaResource resource = new EcasaResource(
                    "Test",
                    "Test resource",
                    "",
                    resourceType,
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
                    "Test",
                    "Test",
                    false,
                    false,
                    role,
                    application
            );
//            entityManager.persist(resource);
//            entityManager.persist(role);
            entityManager.persist(policy);
        }

        TestTransaction.end();
        assertThat(TestTransaction.isActive()).isFalse();
        log.debug("End save transaction");

        log.debug("Start delete transaction");
        TestTransaction.start();

        {
            EcasaApplication application = entityManager
                    .createQuery(
                            "select i from EcasaApplication i where i.name = :name",
                            EcasaApplication.class
                    )
                    .setParameter(
                            "name",
                            "Test"
                    )
                    .getSingleResult();

            EcasaPolicy policy = entityManager
                    .createQuery(
                            "select i from EcasaPolicy i where i.application = :application",
                            EcasaPolicy.class
                    )
                    .setParameter(
                            "application",
                            application
                    )
                    .getSingleResult();
            entityManager.remove(policy);

//            EcasaResource resource = entityManager
//                    .createQuery(
//                            "select i from EcasaResource i where i.application = :application",
//                            EcasaResource.class
//                    )
//                    .setParameter(
//                            "application",
//                            application
//                    )
//                    .getSingleResult();
//            entityManager.remove(resource);

            EcasaRole role = entityManager
                    .createQuery(
                            "select i from EcasaRole i where i.application = :application",
                            EcasaRole.class
                    )
                    .setParameter(
                            "application",
                            application
                    )
                    .getSingleResult();
            entityManager.remove(role);

            entityManager.remove(application);
        }
    }
}
