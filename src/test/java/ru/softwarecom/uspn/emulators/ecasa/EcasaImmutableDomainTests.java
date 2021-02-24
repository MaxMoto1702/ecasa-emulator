package ru.softwarecom.uspn.emulators.ecasa;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.transaction.TestTransaction;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaActionType;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaApplication;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaResourceType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

//@DataJpaTest(showSql = false)
//@ActiveProfiles("test")
public class EcasaImmutableDomainTests {
    private static final Logger log = getLogger(EcasaImmutableDomainTests.class);

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

//    @Test
    @Commit()
    void run_over_emf() {
        Long resourceTypeId;
        Long actionTypeId;
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
            entityManager.persist(resourceType);
            resourceTypeId = resourceType.getId();

            EcasaActionType actionType = new EcasaActionType(
                    "view",
                    application
            );
            entityManager.persist(actionType);
            actionTypeId = actionType.getId();
        }

        TestTransaction.end();
        assertThat(TestTransaction.isActive()).isFalse();
        log.debug("End save transaction");

        log.debug("Start delete transaction");
        TestTransaction.start();

        {
//            EcasaResourceType resourceType = entityManager.find(
//                    EcasaResourceType.class,
//                    resourceTypeId
//            );
            EcasaResourceType resourceType = entityManager
                    .createQuery(
                            "select i from EcasaResourceType i where i.name = :name",
                            EcasaResourceType.class
                    )
                    .setParameter(
                            "name",
                            "Test"
                    )
                    .getSingleResult();
            entityManager.remove(resourceType);

            EcasaActionType actionType = entityManager.find(
                    EcasaActionType.class,
                    actionTypeId
            );
            entityManager.remove(actionType);
        }
    }
}
