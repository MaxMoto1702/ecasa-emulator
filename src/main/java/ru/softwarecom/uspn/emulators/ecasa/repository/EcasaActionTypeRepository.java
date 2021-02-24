package ru.softwarecom.uspn.emulators.ecasa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaActionType;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaApplication;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaPolicy;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaResourceType;

public interface EcasaActionTypeRepository extends JpaRepository<EcasaActionType, Long> {

    @Modifying
    @Query("delete from EcasaActionType p where p.application.name = :applicationName")
    void deleteByApplicationName(String applicationName);

    void deleteAllByApplication(EcasaApplication application);

    boolean existsByName(String name);

    EcasaActionType findByName(String name);
}
