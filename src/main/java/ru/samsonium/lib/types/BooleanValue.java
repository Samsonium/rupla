package ru.samsonium.lib.types;

public class BooleanValue implements Value {
    private final boolean value;

    public BooleanValue(double value) {
        this.value = value == 1;
    }

    @Override
    public double getNumber() {
        return value ? 1 : 0;
    }

    @Override
    public String getString() {
        return String.valueOf(value);
    }
}
