package ru.softwarecom.uspn.emulators.ecasa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaApplication;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaRole;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface EcasaRoleRepository extends JpaRepository<EcasaRole, Long> {

    @Modifying
    @Query("delete from EcasaRole p where p.application.name = :applicationName")
    void deleteByApplicationName(String applicationName);

    void deleteAllByApplication(EcasaApplication application);

    boolean existsByName(String name);

    EcasaRole findByName(String name);

    @Query("select r from EcasaRole r where r not in :roles")
    List<EcasaRole> findAllExclude(Collection<EcasaRole> roles);
}
