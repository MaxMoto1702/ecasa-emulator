package ru.softwarecom.uspn.emulators.ecasa;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.transaction.TestTransaction;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaApplication;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaRole;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaUser;
import ru.softwarecom.uspn.emulators.ecasa.repository.EcasaUserRepository;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.slf4j.LoggerFactory.getLogger;

//@DataJpaTest(showSql = false)
//@ActiveProfiles("test")
public class EcasaUserTests {
    private static final Logger log = getLogger(EcasaUserTests.class);

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EcasaUserRepository ecasaUserRepository;

//    @Test
    @Commit()
    void byLoginNotThrowException() {
        Long userId;
        {
            EcasaApplication application = new EcasaApplication(
                    "Test",
                    "Test application",
                    "Description about test application",
                    false
            );

            EcasaRole role = new EcasaRole(
                    "Test",
                    "Test role",
                    "",
                    application
            );
            entityManager.persist(role);

            final EcasaUser user = new EcasaUser("admin", "admin@email.net");
            user.setPassword("Qwerty123");
            user.setName("Администратор");
            user.setDescription("Пользователь для администрирования");
            user.addRole(role);
            entityManager.persist(user);

            userId = user.getId();
        }
        TestTransaction.end();
        assertFalse(TestTransaction.isActive());
        log.debug("End save transaction");

        log.debug("Start get transaction");
        TestTransaction.start();
        EcasaUser userByLogin;
        EcasaUser userById;
        {
            userByLogin = ecasaUserRepository.findByUsername("admin");
        }
        TestTransaction.end();
        assertFalse(TestTransaction.isActive());
        log.debug("End get transaction");


        assertEquals(userByLogin.getLogin(), "admin");
        assertEquals(userByLogin.getRoles().size(), 1);
        assertDoesNotThrow(() -> {
            //noinspection ResultOfMethodCallIgnored
            userByLogin.getRoles().size();
        });
        assertDoesNotThrow(() -> {
            //noinspection OptionalGetWithoutIsPresent
            return userByLogin.getRoles().stream().findFirst().get();
        });
    }

//    @Test
    @Commit()
    void byIdThrowException() {
        Long userId;
        {
            EcasaApplication application = new EcasaApplication(
                    "Test2",
                    "Test application 2",
                    "Description about test application",
                    false
            );

            EcasaRole role = new EcasaRole(
                    "Test2",
                    "Test role 2",
                    "",
                    application
            );
            entityManager.persist(role);

            final EcasaUser user = new EcasaUser("admin2", "admin@email.net");
            user.setPassword("Qwerty123");
            user.setName("Администратор");
            user.setDescription("Пользователь для администрирования");
            user.addRole(role);
            entityManager.persist(user);

            userId = user.getId();
        }
        TestTransaction.end();
        assertFalse(TestTransaction.isActive());
        log.debug("End save transaction");

        log.debug("Start get transaction");
        TestTransaction.start();
        EcasaUser userById;
        {
            userById = ecasaUserRepository.getOne(userId);
        }
        TestTransaction.end();
        assertFalse(TestTransaction.isActive());
        log.debug("End get transaction");

        assertThrows(Exception.class, () -> {
            //noinspection ResultOfMethodCallIgnored
            userById.getRoles().size();
            //noinspection OptionalGetWithoutIsPresent
            userById.getRoles().stream().findFirst().get();
        });
    }
}
