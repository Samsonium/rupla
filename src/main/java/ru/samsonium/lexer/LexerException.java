package ru.samsonium.lexer;

public class LexerException extends RuntimeException {
    public LexerException(String message) {
        super(String.format("[?:?]: %s", message));
    }

    public LexerException(int row, int col, String message) {
        super(String.format("[%d:%d]: %s", row, col, message));
    }
}
