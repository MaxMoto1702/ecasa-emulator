package ru.softwarecom.uspn.emulators.ecasa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaApplication;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaResource;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaResourceType;

public interface EcasaResourceTypeRepository extends JpaRepository<EcasaResourceType, Long> {

    @Modifying
    @Query("delete from EcasaResourceType p where p.application.name = :applicationName")
    void deleteByApplicationName(String applicationName);

    void deleteAllByApplication(EcasaApplication application);

    boolean existsByName(String name);

    EcasaResourceType findByName(String name);
}
