package ru.softwarecom.uspn.emulators.ecasa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaUser;

public interface EcasaUserRepository extends JpaRepository<EcasaUser, Long> {

    /**
     * Поиск пользователя по логину.
     * Данный поиск требуется для spring-security.
     *
     * @param login логин пользователя;
     * @return найденный пользователь.
     */
    @Query("select u from EcasaUser u left join fetch u.roles where u.username = :login")
    EcasaUser findByUsername(String login);

    /**
     * Проверка наличия пользователя с логином.
     *
     * @param login проверяемый логин.
     * @return {@code true} если пользователь есть в БД.
     */
    boolean existsByUsername(String login);
}
