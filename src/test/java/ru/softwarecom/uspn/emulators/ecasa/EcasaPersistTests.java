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
import javax.persistence.EntityManagerFactory;

import static org.slf4j.LoggerFactory.getLogger;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@ActiveProfiles("test")
public class EcasaPersistTests {
    private static final Logger log = getLogger(EcasaPersistTests.class);
    private static final String APPLICATION_NAME = "Test";

    @Autowired private EntityManager entityManager;
    @Autowired private EntityManagerFactory entityManagerFactory;

    @Test
    @Commit()
    void persist() {
        Long userId;
        {
//            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EcasaUser user = new EcasaUser(
                    "test_login",
                    "test@test.test"
            );
            user.setName("persist");
            user.setFirstName("test");
            user.setMiddleName("test");
            user.setLastName("test");
            user.setDescription("test");
            entityManager.persist(user);
            userId = user.getId();
        }

        TestTransaction.end();
        log.debug("End persist transaction");

        log.debug("Start get transaction");
        TestTransaction.start();

        {
//            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EcasaUser user = entityManager.find(EcasaUser.class, userId);
            assertThat(user.getName()).isEqualTo("persist");
        }
    }

    @Test
    @Commit()
    void mergeTransientAndNotExistInDb() {
        Long userId;
        {
//            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EcasaUser user = new EcasaUser(
                    "test_login",
                    "test@test.test"
            );
            user.setName("merge");
            user.setFirstName("test");
            user.setMiddleName("test");
            user.setLastName("test");
            user.setDescription("test");
            user = entityManager.merge(user);
            userId = user.getId();
        }

        TestTransaction.end();
        log.debug("End merge transaction");

        log.debug("Start get transaction");
        TestTransaction.start();

        {
//            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EcasaUser user = entityManager.find(EcasaUser.class, userId);
            assertThat(user.getName()).isEqualTo("merge");
        }
    }

    @Test
    @Commit()
    void mergeTransientAndExistInDb() {
        Long userId;
        {
//            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EcasaUser user = new EcasaUser(
                    "test_login",
                    "test@test.test"
            );
            user.setName("persist");
            user.setFirstName("test");
            user.setMiddleName("test");
            user.setLastName("test");
            user.setDescription("test");
            entityManager.persist(user);
            userId = user.getId();
        }

        TestTransaction.end();
        log.debug("End persist transaction");

        log.debug("Start merge transaction");
        TestTransaction.start();
        {
//            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EcasaUser user = new EcasaUser(
                    "test_login",
                    "test@test.test"
            );

            // для эксперимента
            user.setId(userId);

            user.setName("merge");
            user.setFirstName("test");
            user.setMiddleName("test");
            user.setLastName("test");
            user.setDescription("test");
            user = entityManager.merge(user);
        }

        TestTransaction.end();
        log.debug("End merge transaction");

        log.debug("Start delete transaction");
        TestTransaction.start();

        {
//            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EcasaUser user = entityManager.find(EcasaUser.class, userId);
            assertThat(user.getName()).isEqualTo("merge");
        }
    }

    @Test
    @Commit()
    void mergeDetachedAndExistInDb() {
        Long userId;
        EcasaUser detachedUser;
        {
//            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EcasaUser user = new EcasaUser(
                    "test_login",
                    "test@test.test"
            );
            user.setName("persist");
            user.setFirstName("test");
            user.setMiddleName("test");
            user.setLastName("test");
            user.setDescription("test");
            log.info("Transient user [ {} ]", user);

            entityManager.persist(user);
            log.info("Persisted user [ {} ]", user);

            userId = user.getId();
            log.info("User ID [ {} ]", userId);
        }
        TestTransaction.end();
        log.debug("End persist transaction");

        log.debug("Start merge transaction");
        TestTransaction.start();
        {
//            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EcasaUser user = entityManager.find(EcasaUser.class, userId);
            entityManager.detach(user);
            detachedUser = user;
            log.info("Detached user [ {} ]", detachedUser);

            EcasaUser mergedUser = entityManager.merge(detachedUser);
            mergedUser.setName("merge");
            log.info("Merged user [ {} ]", mergedUser);
        }
        TestTransaction.end();
        log.debug("End merge transaction");

        log.debug("Start delete transaction");
        TestTransaction.start();
        {
//            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EcasaUser user = entityManager.find(EcasaUser.class, userId);
            assertThat(user.getName()).isEqualTo("merge");
        }
    }

    @Test
    @Commit()
    void mergePersisted() {
        Long userId;
        EcasaUser detachedUser;
        {
//            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EcasaUser user = new EcasaUser(
                    "test_login",
                    "test@test.test"
            );
            user.setName("persist");
            user.setFirstName("test");
            user.setMiddleName("test");
            user.setLastName("test");
            user.setDescription("test");
            entityManager.persist(user);
            userId = user.getId();
            log.info("User ID [ {} ]", userId);
            log.info("Persisted user [ {} ]", user);

            EcasaUser mergedUser = entityManager.merge(user);
            mergedUser.setName("merge");
            log.info("Merged user [ {} ]", mergedUser);
        }

        TestTransaction.end();
        log.debug("End persist and merge transaction");

        log.debug("Start get transaction");
        TestTransaction.start();

        {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EcasaUser user = entityManager.find(EcasaUser.class, userId);
            assertThat(user.getName()).isEqualTo("merge");
        }
    }
}
