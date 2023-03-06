package ru.samsonium.parser;

import java.util.List;

import ru.samsonium.lexer.token.Token;

public class Parser {
    private final List<Token> tokens;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    /** Generate statements and expressions */
    public int parse() {
        return 0;
    }
}
