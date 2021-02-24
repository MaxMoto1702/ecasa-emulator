package ru.softwarecom.uspn.emulators.ecasa.service;

import ru.softwarecom.uspn.emulators.ecasa.domain.EcasaUser;
import ru.softwarecom.uspn.emulators.ecasa.exception.DuplicateLoginException;

public interface UserService {

    /**
     * Регистрация пользователя.
     *
     * @param user регистрируемы пользователь.
     * @return зарегистрированный пользователь.
     * @throws DuplicateLoginException выбрасывается в случае если логин уже зарегистрирован.
     */
    EcasaUser registerUser(EcasaUser user) throws DuplicateLoginException;
}
