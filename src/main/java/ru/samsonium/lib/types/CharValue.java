package ru.samsonium.lib.types;

public class CharValue implements Value {
    private final char value;

    public CharValue(char value) {
        this.value = value;
    }

    @Override
    public double getNumber() {
        return Character.getNumericValue(value);
    }

    @Override
    public String getString() {
        return String.valueOf(value);
    }
}
