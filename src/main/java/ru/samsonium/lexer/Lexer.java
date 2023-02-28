package ru.samsonium.lexer;

import java.util.ArrayList;
import java.util.List;

import ru.samsonium.lexer.token.Token;
import ru.samsonium.lexer.token.TokenType;

public class Lexer {
    private static final Token EOF_TOKEN = new Token(TokenType.EC_EOF, null);

    // Initial data section
    private final String source;
    private final int    length;

    // Active data section
    private List<Token> tokens;
    private int position;

    public Lexer(String source) {
        this.source = source;
        length = source.length();
        position = 0;
        tokens = new ArrayList<>();
    }

    /** Generate tokens list from source code */
    public List<Token> tokenize() {
        return tokens;
    }
}
