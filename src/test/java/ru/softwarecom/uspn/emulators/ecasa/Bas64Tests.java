package ru.softwarecom.uspn.emulators.ecasa;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

public class Bas64Tests {
//    @Test
    public void test() {
        String value = "Тест";
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        byte[] encodeBytes = Base64.getEncoder().encode(bytes);
        String encoded = new String(encodeBytes);
        String transfer = "UTF-8     " + encoded;
        String substring = transfer.substring(10);
        assert Objects.equals(encoded, substring);
        byte[] decodeBytes = Base64.getDecoder().decode(substring);
        String s = new String(decodeBytes, StandardCharsets.UTF_8);
        System.out.println(s);
    }
}
