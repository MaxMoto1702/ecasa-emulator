package ru.softwarecom.uspn.emulators.ecasa.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.softwarecom.uspn.emulators.ecasa.domain.*;
import ru.softwarecom.uspn.emulators.ecasa.exception.DuplicateLoginException;
import ru.softwarecom.uspn.emulators.ecasa.repository.*;
import ru.softwarecom.uspn.emulators.ecasa.service.UserService;

import javax.annotation.PostConstruct;

import static org.slf4j.LoggerFactory.getLogger;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private static final Logger log = getLogger(UserServiceImpl.class);

    private final EcasaUserRepository ecasaUserRepository;
    private final EcasaRoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EcasaResourceRepository resourceRepository;
    private final EcasaPolicyRepository policyRepository;
    private final EcasaResourceTypeRepository resourceTypeRepository;
    private final EcasaActionTypeRepository actionTypeRepository;

    @PostConstruct
    public void configureSystemAndAdminUsers() {
        if (!ecasaUserRepository.existsByUsername("system")) {
            final EcasaUser user = new EcasaUser("system", "system@email.net");
            user.setPassword(bCryptPasswordEncoder.encode(""));
            user.setName("Система");
            user.setFirstName("Система");
            user.setMiddleName("Система");
            user.setLastName("Система");
            user.setDescription("Системный пользователь");
            ecasaUserRepository.save(user);
        }
        final EcasaApplication application = new EcasaApplication(
                "ECASA",
                "ЕЦАСА",
                "Эмулятор ЕЦАСА",
                false
        );
        final EcasaRole role;
        if (!roleRepository.existsByName("admin")) {
            role = new EcasaRole(
                    "admin",
                    "Администратор",
                    "Роль для администрирования",
                    application
            );
//            roleRepository.save(role);
        } else {
            role = roleRepository.findByName("admin");
        }
        final EcasaResourceType resourceType;
        if (!resourceTypeRepository.existsByName("admin")) {
            resourceType = new EcasaResourceType(
                    "admin",
                    "Администратор",
                    "Тип ресурса для администрирования",
                    application
            );
//            resourceTypeRepository.save(resourceType);
        } else {
            resourceType = resourceTypeRepository.findByName("admin");
        }
        final EcasaResource resource;
        if (!resourceRepository.existsByName("admin")) {
            resource = new EcasaResource(
                    "admin",
                    "Администратор",
                    "Ресурс для администрирования",
                    resourceType,
                    application
            );
//            resourceRepository.save(resource);
        } else {
            resource = resourceRepository.findByName("admin");
        }
        final EcasaActionType actionType;
        if (!actionTypeRepository.existsByName("admin")) {
            actionType = new EcasaActionType(
                    "admin",
                    application
            );
//            actionTypeRepository.save(actionType);
        } else {
            actionType = actionTypeRepository.findByName("admin");
        }
        final EcasaPolicy policy;
        if (!policyRepository.existsByName("admin")) {
            policy = new EcasaPolicy(
                    "admin",
                    "Администратор",
                    "Политика для администрирования",
                    false,
                    false,
                    role,
                    application
            );
            policy.getActions().add(
                    new EcasaAction(
                            resource,
                            actionType
                    )
            );
            policyRepository.save(policy);
//        } else {
//            policy = policyRepository.findByName("admin");
        }
        if (!ecasaUserRepository.existsByUsername("admin")) {
            final EcasaUser user = new EcasaUser("admin", "admin@email.net");
            user.setPassword(bCryptPasswordEncoder.encode("Qwerty123"));
            user.setName("Администратор");
            user.setFirstName("Администратор");
            user.setMiddleName("Администратор");
            user.setLastName("Администратор");
            user.setDescription("Пользователь для администрирования");
            user.addRole(role);
            ecasaUserRepository.save(user);
        }
    }

    @Override
    @Transactional
    public EcasaUser registerUser(EcasaUser user) throws DuplicateLoginException {
        log.debug("Register user [ {} ]", user);
        if (ecasaUserRepository.existsByUsername(user.getUsername())) {
            log.error("EcasaUser with login [ " + user.getUsername() + " ] exist in DB");
            throw new DuplicateLoginException(user.getUsername());
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        ecasaUserRepository.save(user);
        log.debug("Saved user [ {} ]", user);
        return user;
    }

    /////////////////////////////////////////////////////////
    // Имплементация методов интерфейса UserDetailsService //
    /////////////////////////////////////////////////////////

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Load user by username(login) [ {} ]", username);
        final EcasaUser user = ecasaUserRepository.findByUsername(username);
        if (user == null) {
            log.error("EcasaUser not found by username(login) [ " + username + " ]");
            throw new UsernameNotFoundException("EcasaUser not found by username(login) [ " + username + " ]");
        }
        log.debug("Found user [ {} ]", user);
        return user;
    }
}
