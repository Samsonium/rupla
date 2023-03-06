package ru.samsonium.lib.types;

public class StringValue implements Value {
    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public double getNumber() {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public String getString() {
        return value;
    }

    @Override
    public String toString() {
        return getString();
    }
}
