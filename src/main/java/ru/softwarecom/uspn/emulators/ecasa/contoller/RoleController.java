package ru.softwarecom.uspn.emulators.ecasa.contoller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaRole;
import ru.softwarecom.uspn.emulators.ecasa.domain.dto.RoleDetailDto;
import ru.softwarecom.uspn.emulators.ecasa.domain.dto.RoleDto;
import ru.softwarecom.uspn.emulators.ecasa.repository.EcasaRoleRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.function.Function;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(value = "api/roles", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RoleController {
    private static final Logger log = getLogger(RoleController.class);

    private final EcasaRoleRepository roleRepository;
    private final TypeMap<EcasaRole, RoleDto> roleToDto;
    private final TypeMap<EcasaRole, RoleDetailDto> roleToDetailDto;


    @GetMapping
    public ResponseEntity<Page<RoleDto>> list(Pageable pageable) {
        log.debug("Get roles by [ {} ]", pageable);
        return ok(
                roleRepository.findAll(pageable)
                        .map(roleToDto::map)
//                        .map(role -> new RoleDto(
//                                role.getId(),
//                                role.getName(),
//                                role.getDisplayName(),
//                                role.getDescription()
//                        ))
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<RoleDetailDto> get(@PathVariable Long id) {
        log.debug("Get role by ID [ {} ]", id);
        if (!roleRepository.existsById(id)) {
            log.error("EcasaRole not found by ID [ " + id + " ]");
            return notFound().build();
        }
        final EcasaRole role = roleRepository.getOne(id);
        final RoleDetailDto dto = roleToDetailDto.map(role);
        return ok(dto);
    }
}
