package ru.samsonium.lexer.token;

public class Token {
    private final TokenType type;
    private final String    data;
    private final int       pos;

    /**
     * Create token
     * @param type
     * @param data
     */
    public Token(TokenType type, String data, int pos) {
        this.type = type;
        this.data = data;
        this.pos = pos;
    }

    public TokenType getType() {
        return type;
    }

    public String getData() {
        return data;
    }
    
    public int getPos() {
        return pos;
    }

    @Override
    public String toString() {
        StringBuilder buff = new StringBuilder();
        buff.append("ТОКЕН(");

        String d = "тип=" + type.getName() + ",";
        String d_append = "";
        if (d.length() < 18)
            d_append = " ".repeat(18 - d.length());

        buff.append(d + d_append);
        buff.append("данные=<" + data + ">)");

        return buff.toString();
    }
}
