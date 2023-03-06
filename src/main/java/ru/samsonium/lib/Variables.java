package ru.samsonium.lib;

import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

import ru.samsonium.lib.types.NumberValue;
import ru.samsonium.lib.types.Value;

public final class Variables {
    private static final Stack<Map<String, Value>> stack;
    private static Map<String, Value> variables;

    static {
        stack = new Stack<>();
        variables = new ConcurrentHashMap<>();
        variables.put("да", new NumberValue(1));
        variables.put("нет", new NumberValue(0));
    }

    /** Push variables to stack */
    public static void push() {
        stack.push(new ConcurrentHashMap<>(variables));
    }

    /** Pop variables from stack */
    public static void pop() {
        variables = stack.pop();
    }

    /** Check is variables exists */
    public static boolean exists(String name) {
        return variables.containsKey(name);
    }

    /** Get value stored in variable */
    public static Value read(String name) {
        if (!exists(name)) return new NumberValue(0);
        return variables.get(name);
    }

    /** Set variable */
    public static void set(String name, Value value) {
        variables.put(name, value);
    }
}
