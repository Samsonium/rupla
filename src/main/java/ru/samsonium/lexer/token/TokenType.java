package ru.samsonium.lexer.token;

public enum TokenType {
    
    // Data types
    D_Number,
    D_Hex,
    D_Char,
    D_String,
    D_Bool,

    // Binary operators
    B_Add,
    B_Sub,
    B_Mul,
    B_Div,
    B_Exp,
    B_Mod,

    // Assignment operators
    As,
    As_Add,
    As_Sub,
    As_Mul,
    As_Div,
    As_Exp,
    As_Mod,

    // Conditional operators
    C_And,
    C_Or,
    C_Neg,
    C_Eq,
    C_Gt,
    C_GtEq,
    C_Lt,
    C_LtEq,
    C_NEq,
    C_IOf,
    C_In,

    // Etc operators
    E_Ret,
    E_Input,
    E_Output,

    // Conditional structure
    CC_If,
    CC_ElseIf,
    CC_Else,

    // Cycle structures
    CF_For,
    CF_Iter,
    CF_While,
    CF_Do,

    // Function structure
    CFn_Func,
    CFn_Name,
    CFn_ArgType,
    CFn_ArgName,
    CFn_Type,

    // Etc symbols
    EC_Semicolon,
    EC_Comma,
    EC_EOF
}
