package ru.softwarecom.uspn.emulators.ecasa;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MapTests {

//    @Test
    public void test() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("key1", "value1");
        map.put("key1", "value2");
        map.put("key2", "value2");
        System.out.println(map);

        System.out.println(map.compute("key1", (key, value) -> value + " UPD"));
        System.out.println(map.compute("keyNotExist", (key, value) -> value + " UPD"));
        System.out.println(map);

        System.out.println(map.computeIfAbsent("key2", key -> key + " UPD"));
        System.out.println(map.computeIfAbsent("keyNotExist2", key -> key + " UPD"));
        System.out.println(map);
    }
}
