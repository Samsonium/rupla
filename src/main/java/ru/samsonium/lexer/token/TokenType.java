package ru.samsonium.lexer.token;

import java.util.regex.Pattern;

public enum TokenType {

    // Conditional operators
    cond_or  ("Дизъюнкция",       "( или )"),
    cond_and ("Конъюнкция",       "( и )"),
    cond_neg ("Инверсия",         "(не )"),
    cond_eq  ("Равенство",        "[=]{2}"),
    cond_neq ("Неравенство",      "![=]{1}"),
    cond_gt  ("Больше",           "[>]{1}"),
    cond_gteq("Больше или равно", "[>]{1}[=]{1}"),
    cond_lt  ("Меньше",           "[<]{1}"),
    cond_lteq("Меньше или равно", "[<]{1}[=]{1}"),

    // Special assignment operators
    as_add("Присвоение со сложением",   "[+]{1}[=]{1}"),
    as_sub("Присвоение с вычитанием",   "[-]{1}[=]{1}"),
    as_mul("Присвоение с умножением",   "[*]{1}[=]{1}"),
    as_div("Присвоение с делением",     "[/]{1}[=]{1}"),

    // Assignment operator
    as("Присвоение", "[=]"),
    
    // Binary operators
    bin_add("Сложение",             "[+]"),
    bin_sub("Вычитание",            "[-]"),
    bin_mul("Умножение",            "[*]"),
    bin_div("Деление",              "[/]"),
    bin_mod("Остаток от деления",   "[%]"),

    // Data types
    type_numb("Десятеричное число",       "[0-9]+([.]{1}[0-9]{1,5})?"),
    type_hex ("Шестнадцатиричное число",  "#[A-f0-9]+"),
    type_char("Символ",                   "'.'"),
    type_str ("Строка",                   "\".+\""),
    type_bool("Булев",                    "(да|нет|истина|ложь)"),

    // Data type define
    typedef("Тип", "(число|хекс|символ|строка|строка|булев)"),

    // Conditional keywords
    cond_if     ("Если",         "(если)"),
    cond_else   ("Иначе",        "(иначе)"),
    cond_switch ("Выбор",        "(выбор)"),
    cond_case   ("Вариант",      "(вариант)"),
    cond_break  ("Прервать",     "(прервать)"),
    cond_default("По умолчанию", "(по умолчанию)"),

    // Cycle structures
    cycle_for  ("Пошаговый цикл", "(цикл)"),
    cycle_while("Условный цикл",  "(пока)"),
    cycle_do   ("Выполнять",      "(выполнять|выполнить)"),

    // Function structures
    func_def("Функция", "(функция)"),
    func_ret("Вернуть", "(вернуть|возврат)"),

    // Some word
    word("Слово", "[A-zА-я_$№]([A-zА-я0-9_$№]+)?"),

    // Special symbols
    spec_whitespace("Пробел",        "[ \\n\\t\\r]"),
    spec_semicolon ("Конец команды", "[;]{1}"),
    spec_colon     ("Двоеточие",     "[:]{1}"),
    spec_comma     ("Запятая",       "[,]{1}"),
    spec_dot       ("Точка",         "[.]{1}"),
    spec_arrow     ("Стрелка",       "[-]{1}[>]{1}"),
    spec_lparen    ("Скобка откр.",  "[(]{1}"),
    spec_rparen    ("Скобка закр.",  "[)]{1}"),
    spec_lbrace    ("Блок откр.",    "[{]{1}"),
    spec_rbrace    ("Блок закр.",    "[}]{1}"),
    spec_lbracket  ("Индекс откр.",  "[\\[]{1}"),
    spec_rbracket  ("Индекс закр.",  "[\\]]{1}");
    
    private String name;
    private String regex;
    private TokenType(String name, String regex) {
        this.name = name;
        this.regex = regex;
    }

    /** Compile and return regex pattern */
    public Pattern toRegex() {
        return Pattern.compile(regex);
    }

    @Override
    public String toString() {
        return name;
    }
}
