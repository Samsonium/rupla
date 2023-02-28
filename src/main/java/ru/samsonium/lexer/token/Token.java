package ru.samsonium.lexer.token;

public class Token {
    private final TokenType type;
    private final String    data;

    /**
     * Create token
     * @param type
     * @param data
     */
    public Token(TokenType type, String data) {
        this.type = type;
        this.data = data;
    }

    /**
     * @return the type
     */
    public TokenType getType() {
        return type;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }    
}
