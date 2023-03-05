package ru.samsonium.lexer.token;

import java.util.regex.Pattern;

public class TokenType {
    private final String name;
    private final String regex;
    
    public TokenType(String name, String regex) {
        this.name = name;
        this.regex = regex;
    }

    public String getName() {
        return name;
    }

    public Pattern getRegex() {
        return Pattern.compile("^" + regex);
    }

    @Override
    public String toString() {
        return "ТИП(" + name + ")";
    }
}
