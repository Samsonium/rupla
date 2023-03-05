package ru.samsonium.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import ru.samsonium.lexer.token.Token;
import ru.samsonium.lexer.token.TokenType;
import ru.samsonium.lexer.token.TokenList;

public class Lexer {


    // Initial data section
    private final String source;
    private final int length;

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
        while (nextToken()) {}

        List<Token> result = new ArrayList<>();
        for (Token token : tokens) {
            if (token.getType().getName() != "EC_WS")
                result.add(token);
        }

        return result;
    }

    /** Read next token */
    public boolean nextToken() {
        if (position >= length)
            return false;

        TokenType[] types = TokenList.list.toArray(new TokenType[TokenList.list.size()]);
        String data = "<отсутствует>";
        TokenType type = null;
        for (int i = 0; i < types.length; i++) {
            type = types[i];
            Matcher result = type.getRegex().matcher(
                source.substring(position));
            boolean found = result.find();

            if (!found) continue;
            data = source.substring(position).substring(result.start(), result.end());

            if (!data.isEmpty()) {
                tokens.add(new Token(type, data, position));
                position += result.end();
                return true;
            }
        }

        System.out.println("Ошибка: неизвестный токен (позиция " 
            + position + ", последовательность: \"" + data + "\")");
        System.exit(1);
        return false;
    }
}
