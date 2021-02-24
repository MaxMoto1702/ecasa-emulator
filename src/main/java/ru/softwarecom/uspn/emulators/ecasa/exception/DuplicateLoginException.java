package ru.softwarecom.uspn.emulators.ecasa.exception;

public class DuplicateLoginException extends Exception {
    public DuplicateLoginException(String login) {
        super("EcasaUser with login [ " + login + " ] exist in DB");
    }
}
