package ru.softwarecom.uspn.emulators.ecasa.contoller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaRole;
import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaUser;
import ru.softwarecom.uspn.emulators.ecasa.domain.dto.RoleDto;
import ru.softwarecom.uspn.emulators.ecasa.domain.dto.UserDetailDto;
import ru.softwarecom.uspn.emulators.ecasa.domain.dto.UserDto;
import ru.softwarecom.uspn.emulators.ecasa.exception.DuplicateLoginException;
import ru.softwarecom.uspn.emulators.ecasa.repository.EcasaRoleRepository;
import ru.softwarecom.uspn.emulators.ecasa.repository.EcasaUserRepository;

import javax.validation.Valid;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(value = "api/users", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {
    private static final Logger log = getLogger(UserController.class);

    private final EcasaUserRepository userRepository;
    private final TypeMap<EcasaUser, UserDto> userToDto;
    private final TypeMap<EcasaUser, UserDetailDto> userToDetailDto;
    private final TypeMap<EcasaRole, RoleDto> roleToDto;
    private final EcasaRoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    public ResponseEntity<Page<UserDto>> list(Pageable pageable) {
        log.debug("Get users by [ {} ]", pageable);
        return ok(userRepository.findAll(pageable)
                .map(userToDto::map));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDetailDto> get(@PathVariable Long id) {
        log.debug("Get user by ID [ {} ]", id);
        if (!userRepository.existsById(id)) {
            log.error("EcasaUser not found by ID [ " + id + " ]");
            return notFound().build();
        }
        final EcasaUser user = userRepository.getOne(id);
        final UserDetailDto dto = userToDetailDto.map(user);
        return ok(dto);
    }

    @PostMapping
    public ResponseEntity<UserDetailDto> post(@RequestBody @Valid UserDetailDto dto) throws DuplicateLoginException {
        log.debug("Save new user [ {} ]", dto);
        if (userRepository.existsByUsername(dto.getUsername())) {
            return badRequest().build();
        }
//        EcasaUser user = modelMapper.map(dto, EcasaUser.class);
        EcasaUser user = new EcasaUser(dto.getUsername(), dto.getEmail());
//        user.setLogin(dto.getLogin() != null ? dto.getLogin() : user.getLogin());
//        user.setEmail(dto.getEmail() != null ? dto.getEmail() : user.getEmail());
        user.setLastName(dto.getLastName());
        user.setMiddleName(dto.getMiddleName());
        user.setFirstName(dto.getFirstName());
        user.setDescription(dto.getDescription());
        user.setName(dto.getName());
        if (!StringUtils.isEmpty(dto.getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        }
        user.setExpired(dto.isExpired());
        user.setLocked(dto.isLocked());
        user.setCredentialsExpired(dto.isCredentialsExpired());
        user.setEnabled(dto.isEnabled());
        userRepository.save(user);
        return ok(userToDetailDto.map(user));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDetailDto> put(@PathVariable Long id, @RequestBody UserDetailDto dto) throws DuplicateLoginException {
        log.debug("Save new user [ {} ]", dto);
        if (!userRepository.existsById(id)) {
            log.error("EcasaUser not found by ID [ " + id + " ]");
            return notFound().build();
        }
        dto.setId(id);
//        EcasaUser user = modelMapper.map(dto, EcasaUser.class);
        EcasaUser user = userRepository.getOne(id);
//        user.setLogin(dto.getLogin() != null ? dto.getLogin() : user.getLogin());
//        user.setEmail(dto.getEmail() != null ? dto.getEmail() : user.getEmail());
        user.setLastName(dto.getLastName() != null ? dto.getLastName() : user.getLastName());
        user.setMiddleName(dto.getMiddleName() != null ? dto.getMiddleName() : user.getMiddleName());
        user.setFirstName(dto.getFirstName() != null ? dto.getFirstName() : user.getFirstName());
        user.setDescription(dto.getDescription() != null ? dto.getDescription() : user.getDescription());
        user.setName(dto.getName() != null ? dto.getName() : user.getName());
        if (!StringUtils.isEmpty(dto.getPassword())) {
            user.setPassword(
//                    bCryptPasswordEncoder.encode(
                    dto.getPassword()
//                    )
            );
        }
        user.setExpired(dto.isExpired());
        user.setLocked(dto.isLocked());
        user.setCredentialsExpired(dto.isCredentialsExpired());
        user.setEnabled(dto.isEnabled());
//        user = entityManagerFactory..merge(user);
        return ok(userToDetailDto.map(user));
    }

    @GetMapping("{id}/roles")
    public ResponseEntity<Set<RoleDto>> getRoles(@PathVariable Long id) {
        log.debug("Get user roles by user ID [ {} ]", id);
        if (!userRepository.existsById(id)) {
            log.error("EcasaUser not found by ID [ " + id + " ]");
            return notFound().build();
        }
        final EcasaUser user = userRepository.getOne(id);
        final Set<EcasaRole> roles = user.getRoles();
        return ok(roles.stream().map(roleToDto::map).collect(Collectors.toSet()));
    }

    @GetMapping("{id}/roles/nonIncluded")
    public ResponseEntity<Set<RoleDto>> getNonIncludedRoles(@PathVariable Long id) {
        log.debug("Get non included user roles by user ID [ {} ]", id);
        if (!userRepository.existsById(id)) {
            log.error("EcasaUser not found by ID [ " + id + " ]");
            return notFound().build();
        }
        final EcasaUser user = userRepository.getOne(id);
        final List<EcasaRole> roles = user.getRoles().isEmpty() ?
                roleRepository.findAll() :
                roleRepository.findAllExclude(user.getRoles());
        return ok(roles.stream().map(roleToDto::map).collect(Collectors.toSet()));
    }

    @SuppressWarnings("rawtypes")
    @PutMapping("{id}/roles/{roleId}")
    public ResponseEntity putRoles(@PathVariable Long id, @PathVariable Long roleId) {
        log.debug("Add role with ID [ {} ] to user with ID [ {} ]", roleId, id);
        if (!userRepository.existsById(id)) {
            log.error("EcasaUser not found by ID [ " + id + " ]");
            return notFound().build();
        }
        if (!roleRepository.existsById(roleId)) {
            log.error("EcasaRole not found by ID [ " + id + " ]");
            return notFound().build();
        }
        final EcasaUser user = userRepository.getOne(id);
        final EcasaRole role = roleRepository.getOne(roleId);
        user.addRole(role);
        userRepository.save(user);
        return accepted().build();
    }

    @SuppressWarnings("rawtypes")
    @PutMapping("{id}/roles/allNonIncluded")
    public ResponseEntity putAllNonIncludedRoles(@PathVariable Long id) {
        log.debug("Add non included roles to user with ID [ {} ]", id);
        if (!userRepository.existsById(id)) {
            log.error("EcasaUser not found by ID [ " + id + " ]");
            return notFound().build();
        }
        final EcasaUser user = userRepository.getOne(id);
        final List<EcasaRole> roles = user.getRoles().isEmpty() ?
                roleRepository.findAll() :
                roleRepository.findAllExclude(user.getRoles());
        user.addRoles(roles);
        userRepository.save(user);
        return accepted().build();
    }

    @SuppressWarnings("rawtypes")
    @DeleteMapping("{id}/roles/{roleId}")
    public ResponseEntity deleteRoles(@PathVariable Long id, @PathVariable Long roleId) {
        log.debug("Remove role with ID [ {} ] from user with ID [ {} ]", roleId, id);
        if (!userRepository.existsById(id)) {
            log.error("EcasaUser not found by ID [ " + id + " ]");
            return notFound().build();
        }
        if (!roleRepository.existsById(roleId)) {
            log.error("EcasaRole not found by ID [ " + id + " ]");
            return notFound().build();
        }
        final EcasaUser user = userRepository.getOne(id);
        final EcasaRole role = roleRepository.getOne(roleId);
        if (!user.getRoles().contains(role)) {
            log.error("User [ " + user + " ] not contains role [ " + role + " ]");
            return badRequest().build();
        }
        user.removeRole(role);
        userRepository.save(user);
        return accepted().build();
    }

    @SuppressWarnings("rawtypes")
    @DeleteMapping("{id}/roles/all")
    public ResponseEntity deleteRoles(@PathVariable Long id) {
        log.debug("Remove all roles from user with ID [ {} ]", id);
        if (!userRepository.existsById(id)) {
            log.error("EcasaUser not found by ID [ " + id + " ]");
            return notFound().build();
        }
        final EcasaUser user = userRepository.getOne(id);
        user.clearRoles();
        userRepository.save(user);
        return accepted().build();
    }
}
