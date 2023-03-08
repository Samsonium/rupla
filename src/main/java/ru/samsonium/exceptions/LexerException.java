package ru.samsonium.exceptions;

public class LexerException extends RuplaException {
    public LexerException(String message) {
        super("[Лексер]: " + message);
    }
}
