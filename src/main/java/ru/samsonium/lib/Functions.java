package ru.samsonium.lib;

import java.util.HashMap;
import java.util.Map;

public class Functions {
    private static final Map<String, Function> functions;
    static {
        functions = new HashMap<>();
    }

    /** Check is function exists */
    public static boolean exists(String name) {
        return functions.containsKey(name);
    }

    /** Get function */
    public static Function get(String name) {
        if (!exists(name))
            throw new RuntimeException("Ошибка: неизвестная функция \"" + name + "\"");
        return functions.get(name);
    }

    /** Set function */
    public static void set(String name, Function func) {
        functions.put(name, func);
    }
}
