package ru.softwarecom.uspn.emulators.ecasa.service;

import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface RoleModelService {
    @PostConstruct
    @Transactional
    void loadUspnRoleModel() throws JAXBException, IOException;
}
