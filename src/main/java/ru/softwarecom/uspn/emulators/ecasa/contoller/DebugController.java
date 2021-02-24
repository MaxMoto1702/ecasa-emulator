package ru.softwarecom.uspn.emulators.ecasa.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.softwarecom.uspn.emulators.ecasa.service.RoleModelService;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;

import java.io.IOException;

import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("debug")
public class DebugController {

    private final RoleModelService roleModelService;

    @PostMapping("loadDefaultUspnRoleModel")
    public ResponseEntity<String> loadDefaultUspnRoleModel()
            throws JAXBException, IOException
    {
        roleModelService.loadUspnRoleModel();
        return ok("OK");
    }

}
