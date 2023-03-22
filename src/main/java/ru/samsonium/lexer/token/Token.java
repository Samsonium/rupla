package ru.samsonium.lexer.token;

public class Token {

    /** Type of the token */
    private final TokenType type;

    /** Token content */
    private final String data;

    /**
     * Create token
     * @param type Token type
     * @param data Token data
     */
    public Token(TokenType type, String data) {
        this.type = type;
        this.data = data;
    }

    /** Get token type name */
    public String getType() {
        return type.name();
    }

    /** Get token data */
    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return String.format("T<%s>(%s)", type.name(), data);
    }
}
