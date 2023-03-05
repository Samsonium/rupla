package ru.samsonium.lexer.token;

import java.util.ArrayList;
import java.util.List;

public class TokenList {
    public static final List<TokenType> list = new ArrayList<>();
    static {
        
        // Data types
        list.add(new TokenType("D_Number", "[0-9]*"));
        list.add(new TokenType("D_Hex",    "#[A-f0-9]*"));
        list.add(new TokenType("D_Char",   "'.'"));
        list.add(new TokenType("D_String", "\".*\""));
        list.add(new TokenType("D_Bool",   "(да|нет|истинно|ложно)"));
        
        // Common
        list.add(new TokenType("D_Type",    "(Число|Хекс|Символ|Строка|Булев)"));
        list.add(new TokenType("D_Name",    "[A-zА-я_]([A-zА-я0-9_]*)?"));
        
        // Binary operators
        list.add(new TokenType("B_Add", "[\\+]"));
        list.add(new TokenType("B_Sub", "[\\-]"));
        list.add(new TokenType("B_Mul", "[\\*]"));
        list.add(new TokenType("B_Div", "[\\/]"));
        list.add(new TokenType("B_Exp", "[\\^]"));
        list.add(new TokenType("B_Mod", "[\\%]"));

        // Conditional operators
        list.add(new TokenType("C_And",  "(и)"));
        list.add(new TokenType("C_Or",   "(или)"));
        list.add(new TokenType("C_Neg",  "(не)"));
        list.add(new TokenType("C_Eq",   "(==)"));
        list.add(new TokenType("C_Gt",   "(\\>)"));
        list.add(new TokenType("C_GtEq", "(\\>\\=)"));
        list.add(new TokenType("C_Lt",   "(\\<)"));
        list.add(new TokenType("C_LtEq", "(\\<\\=)"));
        list.add(new TokenType("C_NEq",  "(\\!\\=)"));
        list.add(new TokenType("C_IOf",  "(это)"));
        list.add(new TokenType("C_In",   "(элемент)"));

        // Assign operators
        list.add(new TokenType("As",     "[=]{1}"));
        list.add(new TokenType("As_Add", "(\\+=)"));
        list.add(new TokenType("As_Sub", "(\\-=)"));
        list.add(new TokenType("As_Mul", "(\\*=)"));
        list.add(new TokenType("As_Div", "(\\/=)"));
        list.add(new TokenType("As_Exp", "(\\^=)"));
        list.add(new TokenType("As_Mod", "(\\%=)"));

        // Conditional structures
        list.add(new TokenType("CC_If",     "(если)"));
        list.add(new TokenType("CC_ElseIf", "(иначе если)"));
        list.add(new TokenType("CC_Else",   "(иначе)"));
        list.add(new TokenType("CC_Switch", "(выбрать)"));
        list.add(new TokenType("CC_Case",   "(вариант)"));
        list.add(new TokenType("CC_Break",  "(закончить)"));

        // Cycle structures
        list.add(new TokenType("CF_For",   "(цикл)"));
        list.add(new TokenType("CF_Iter",  "(перебор)"));
        list.add(new TokenType("CF_While", "(пока)"));
        list.add(new TokenType("CF_Do",    "(выполнять|выполнить)"));

        // Function structures
        list.add(new TokenType("CFn_Func",    "(функция)"));

        // Etc operators
        list.add(new TokenType("E_Ret",    "(вернуть)"));
        list.add(new TokenType("E_Input",  "(ввод)"));
        list.add(new TokenType("E_Output", "(вывод|вывести)"));
        list.add(new TokenType("E_Var",    "[A-zА-я_][A-zА-я0-9_]+"));

        // Etc symbols
        list.add(new TokenType("EC_Semicolon", "[\\;]"));
        list.add(new TokenType("EC_Colon",     "[\\:]"));
        list.add(new TokenType("EC_Comma",     "[\\,]"));
        list.add(new TokenType("EC_Dot",       "[\\.]"));
        list.add(new TokenType("EC_WS",        "[ \\n\\t\\r]"));
        list.add(new TokenType("EC_ParL",      "[\\(]"));
        list.add(new TokenType("EC_ParR",      "[\\)]"));
        list.add(new TokenType("EC_BrL",       "[\\{]"));
        list.add(new TokenType("EC_BrR",       "[\\}]"));
    }
}
