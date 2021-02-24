package ru.softwarecom.uspn.emulators.ecasa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaApplication;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaPolicy;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaRole;

public interface EcasaPolicyRepository extends JpaRepository<EcasaPolicy, Long> {

    @Modifying
    @Query("delete from EcasaPolicy p where p.application.name = :applicationName")
    void deleteByApplicationName(String applicationName);

    void deleteAllByApplication(EcasaApplication application);

    boolean existsByName(String name);

    EcasaPolicy findByName(String name);
}
