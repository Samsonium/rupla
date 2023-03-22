package ru.samsonium.lexer.token;

import java.util.regex.Pattern;

public enum TokenType {

    // Conditional operators
    cond_or  ("( или )"),
    cond_and ("( и )"),
    cond_neg ("(не )"),
    cond_eq  ("[=]{2}"),
    cond_neq ("![=]{1}"),
    cond_gt  ("[>]{1}"),
    cond_gteq("[>]{1}[=]{1}"),
    cond_lt  ("[<]{1}"),
    cond_lteq("[<]{1}[=]{1}"),

    // Special assignment operators
    as_add("[+]{1}[=]{1}"),
    as_sub("[-]{1}[=]{1}"),
    as_mul("[*]{1}[=]{1}"),
    as_div("[/]{1}[=]{1}"),

    // Assignment operator
    as("[=]"),
    
    // Binary operators
    binary_op("[*|/|+|-]"),

    // Data types
    type_numb("[0-9]+([.]{1}[0-9]{1,5})?"),
    type_hex ("#[A-f0-9]+"),
    type_char("'.'"),
    type_str (".+\""),
    type_bool("(да|нет|истина|ложь)"),

    // Data type define
    typedef("(число|хекс|символ|строка|строка|булев)"),

    // Conditional keywords
    cond_if     ("(если)"),
    cond_else   ("(иначе)"),
    cond_switch ("(выбор)"),
    cond_case   ("(вариант)"),
    cond_break  ("(прервать)"),
    cond_default("(по умолчанию)"),

    // Cycle structures
    cycle_for  ("(цикл)"),
    cycle_while("(пока)"),
    cycle_do   ("(выполнять|выполнить)"),

    // Function structures
    func_def("(функция)"),
    func_ret("(вернуть|возврат)"),

    // Some word
    word("[A-zА-я_$№]([A-zА-я0-9_$№]+)?"),

    // Special symbols
    spec_whitespace("[ \\t\\f\\r\\n]"),
    spec_semicolon ("[;]{1}"),
    spec_colon     ("[:]{1}"),
    spec_comma     ("[,]{1}"),
    spec_dot       ("[.]{1}"),
    spec_arrow     ("[-]{1}[>]{1}"),
    spec_lparen    ("[(]{1}"),
    spec_rparen    ("[)]{1}"),
    spec_lbrace    ("[{]{1}"),
    spec_rbrace    ("[}]{1}"),
    spec_lbracket  ("[\\[]{1}"),
    spec_rbracket  ("[\\]]{1}");
    
    private String regex;
    private TokenType(String regex) {
        this.regex = regex;
    }

    /** Compile and return regex pattern */
    public Pattern toRegex() {
        return Pattern.compile(regex);
    }

    @Override
    public String toString() {
        return name();
    }
}
