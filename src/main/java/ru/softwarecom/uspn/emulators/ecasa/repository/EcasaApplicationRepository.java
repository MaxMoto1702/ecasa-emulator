package ru.softwarecom.uspn.emulators.ecasa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaApplication;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaPolicy;

public interface EcasaApplicationRepository extends JpaRepository<EcasaApplication, Long> {

    @Modifying
    @Query("delete from EcasaApplication p where p.name = :applicationName")
    void deleteByApplicationName(String applicationName);

    EcasaApplication findByName(String applicationName);
}
