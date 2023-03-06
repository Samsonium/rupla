package ru.samsonium.lib.types;

public class NumberValue implements Value {
    private final double value;

    public NumberValue(double value) {
        this.value = value;
    }

    /** Create number interpretation of boolean value */
    public static NumberValue fromBool(boolean b) {
        return new NumberValue(b ? 0 : 1); 
    }

    @Override
    public double getNumber() {
        return value;
    }

    @Override
    public String getString() {
        return String.valueOf(value);
    }

    @Override
    public String toString() {
        return getString();
    }
}
