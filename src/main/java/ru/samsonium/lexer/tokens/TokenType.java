package ru.samsonium.lexer.tokens;

import java.util.regex.Pattern;

public enum TokenType {

    // Binary operators
    b_add("[+]"),
    b_sub("[-]"),
    b_mul("[*]"),
    b_div("[/]"),
    b_mod("[%]"),

    // Conditional operators
    c_or("( или )"),
    c_and("( и )"),
    c_neg("(не )"),
    c_eq("[=]{2}"),
    c_neq("![=]{1}"),
    c_gt("[>]{1}"),
    c_gteq("[>]{1}[=]{1}"),
    c_lt("[<]{1}"),
    c_lteq("[<]{1}[=]{1}"),

    // Assign operator
    as("[=]{1}"),

    // Special assign operators
    as_add("[+]{1}[=]{1}"),
    as_sub("[-]{1}[=]{1}"),
    as_mul("[*]{1}[=]{1}"),
    as_div("[/]{1}[=]{1}"),

    // Data types
    d_numb("[0-9]+([.]{1}[0-9]{1,5})?"),
    d_hex("#[A-f0-9]+"),
    d_char("'.'"),
    d_str("\\\".+\\\""),
    d_bool("(да|нет|истина|ложь)"),

    // Type definition
    typedef("(число|хекс|символ|строка|булев)"),

    // Conditional structures
    cc_if("(если)"),
    cc_else("(иначе)"),
    cc_switch("(выбор)"),
    cc_case("(вариант)"),
    cc_break("(прервать)"),
    cc_default("(по умолчанию)"),

    // Cycle structures
    cf_for("(цикл)"),
    cf_while("(пока)"),
    cf_do("(выполнить|выполнять)"),

    // Function structures
    cfn_func("(функция)"),
    cfn_ret("(вернуть)"),

    // Word
    word("[A-zА-я_$№]([A-zА-я0-9_$№])?"),

    // Special symbols
    ec_semicolon("[;]{1}"),
    ec_colon("[:]{1}"),
    ec_comma("[,]{1}"),
    ec_dot("[.]{1}"),
    ec_arrow("[-]{1}[>]{1}"),
    ec_whitespace("[ \\n\\t\\r]"),
    ec_lparen("[(]{1}"),
    ec_rparen("[)]{1}"),
    ec_lbrace("[{]{1}"),
    ec_rbrace("[}]{1}"),
    ec_lbracket("[\\[]{1}"),
    ec_rbracket("[\\]]{1}");

    private final String name;

    private TokenType(String name) {
        this.name = name;
    }

    public Pattern getRegex() {
        return Pattern.compile("^" + name);
    }
}
