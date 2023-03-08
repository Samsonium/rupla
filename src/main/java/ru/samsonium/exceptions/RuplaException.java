package ru.samsonium.exceptions;

public class RuplaException extends RuntimeException {
    public RuplaException(String message) {
        super("[Ошибка]" + message + "\nВыход...");
    }
}
