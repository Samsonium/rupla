package ru.samsonium.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import ru.samsonium.lexer.token.Token;
import ru.samsonium.lexer.token.TokenType;

public class Lexer {
    
    /** Source code of programm */
    private final String source;

    /** Token list */
    private final List<Token> tokens;

    /** Current character position */
    private int position;

    /**
     * Instantiate Lexer class
     * @param source Source code
     */
    public Lexer(String source) {
        this.source = source;
        position = 0;
        tokens = new ArrayList<>();
    }

    /** Analyze provided source code and generate list of tokens */
    public List<Token> analyze() {
        List<Token> result = new ArrayList<>();
        
        while (position < source.length()) {
            boolean match = false;

            // Iterate over token types and find by regex
            for (TokenType type : TokenType.values()) {
                Matcher matcher = type.toRegex().matcher(source.substring(position));

                // If token found by token type
                if (match = matcher.find()) {
                    String value = matcher.group().trim();
                    tokens.add(new Token(type, value));
                    position += value.length();
                    break;
                }
            }

            // Check is token isn't found
            if (!match) throw new RuntimeException("Illegal character: " + source.charAt(position));
        }

        // Generate tokens list without whitespaces
        for (Token t : tokens)
            if (t.getType() != TokenType.spec_whitespace.name())
                result.add(t);
        
        return result;
    }
}
