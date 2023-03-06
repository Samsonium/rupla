package ru.samsonium.parser;

import java.util.ArrayList;
import java.util.List;

import ru.samsonium.lexer.token.Token;
import ru.samsonium.lexer.token.TokenList;
import ru.samsonium.parser.ast.Expression;
import ru.samsonium.parser.ast.Statement;
import ru.samsonium.parser.ast.expressions.BinaryExpression;
import ru.samsonium.parser.ast.expressions.ConditionalExpression;
import ru.samsonium.parser.ast.expressions.FunctionalExpression;
import ru.samsonium.parser.ast.expressions.UnaryExpression;
import ru.samsonium.parser.ast.expressions.ValueExpression;
import ru.samsonium.parser.ast.expressions.VariableExpression;
import ru.samsonium.parser.ast.statements.AssignmentStatement;
import ru.samsonium.parser.ast.statements.BlockStatement;
import ru.samsonium.parser.ast.statements.BreakStatement;
import ru.samsonium.parser.ast.statements.ConditionalStatement;
import ru.samsonium.parser.ast.statements.DoWhileStatement;
import ru.samsonium.parser.ast.statements.EmptyStatement;
import ru.samsonium.parser.ast.statements.ForStatement;
import ru.samsonium.parser.ast.statements.FuncDefineStatement;
import ru.samsonium.parser.ast.statements.FunctionStatement;
import ru.samsonium.parser.ast.statements.OutputStatement;
import ru.samsonium.parser.ast.statements.ReturnStatement;
import ru.samsonium.parser.ast.statements.WhileStatement;

public class Parser {
    private final List<Token> tokens;
    private final int size;

    private int pos;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        size = tokens.size();
        pos = 0;
    }

    /** Generate statements and expressions */
    public Statement parse() {
        final BlockStatement result = new BlockStatement();
        while (!match("EOF"))
            result.add(statement());
        return result;
    }

    ////////////////////////////////////////////////// STATEMENTS
    
    /** Create block statement */
    private Statement block() {
        final BlockStatement block = new BlockStatement();
        consume("EC_BrL");
        while (!match("EC_BrR"))
            block.add(statement());
        return block;
    }

    /** Return block or basically statement */
    private Statement stmtOrBlock() {
        if (lookMatch(0, "EC_BrL"))
            return block();
        return statement();
    }    

    /** Statement parsing */
    private Statement statement() {
        if (match("E_Output"))
            return new OutputStatement(expression());

        if (match("CC_If"))
            return ifElseStatement();

        if (match("CF_While"))
            return whileStatement();

        if (match("CF_Do"))
            return doWhileStatement();

        if (match("CC_Break"))
            return new BreakStatement();

        if (match("E_Ret"))
            return new ReturnStatement(expression());

        if (match("CF_For"))
            return forStatement();

        if (match("CFn_Func"))
            return functionDefineStatement();

        if (lookMatch(0, "D_Name") && lookMatch(1, "EC_ParL"))
            return new FunctionStatement(function());

        if (match("EC_Semicolon"))
            return new EmptyStatement();

        return assignmentStatement();
    }

    /** Parse assignment */
    private Statement assignmentStatement() {
        if (lookMatch(0, "D_Type") && lookMatch(1, "D_Name") && lookMatch(2, "As")) {
            final String type = consume("D_Type").getData();
            final AssignmentStatement.DataType dataType;
            switch (type.trim()) {
                case "Число":
                    dataType = AssignmentStatement.DataType.Number;
                    break;
                case "Хекс":
                    dataType = AssignmentStatement.DataType.Hex;
                    break;
                case "Символ":
                    dataType = AssignmentStatement.DataType.Char;
                    break;
                case "Строка":
                    dataType = AssignmentStatement.DataType.String;
                    break;
                case "Булев":
                    dataType = AssignmentStatement.DataType.Bool;
                    break;

                default:
                    throw new RuntimeException("Ошибка: неизвестный тип \"" + type + "\"");
            }

            final String name = consume("D_Name").getData();
            consume("As");
            return new AssignmentStatement(dataType, name, expression());
        }

        throw new RuntimeException("Ошибка: неизвестное выражение \"" + get(0) + "\"");
    }

    /** Parse if-else blocks */
    private Statement ifElseStatement() {
        final Expression condition = expression();
        final Statement ifStmt = stmtOrBlock();
        final Statement elseStmt;

        if (match("CC_Else"))
            elseStmt = stmtOrBlock();
        else elseStmt = null;

        return new ConditionalStatement(condition, ifStmt, elseStmt);
    }

    /** Parse while loop */
    private Statement whileStatement() {
        final Expression condition = expression();
        final Statement statement = stmtOrBlock();
        return new WhileStatement(condition, statement);
    }

    /** Parse do-while loop */
    private Statement doWhileStatement() {
        final Statement statement = stmtOrBlock();
        consume("CF_While");

        final Expression condition = expression();
        return new DoWhileStatement(condition, statement);
    }

    /** Parse for statement */
    private Statement forStatement() {
        match("EC_ParL");
        final Statement init = assignmentStatement();

        match("EC_Comma");
        final Expression term = expression();

        match("EC_Comma");
        final Statement inc = assignmentStatement();

        match("EC_ParR");
        final Statement statement = stmtOrBlock();
        return new ForStatement(init, term, inc, statement);
    }

    /** Parse function definition */
    private FuncDefineStatement functionDefineStatement() {
        final String name = consume("D_Name").getData();
        consume("EC_ParL");

        final List<String> args = new ArrayList<>();
        while (!match("EC_ParR")) {
            args.add(consume("D_Name").getData());
            match("EC_Comma");
        }

        final Statement body = stmtOrBlock();
        return new FuncDefineStatement(name, args, body);
    }
    
    ////////////////////////////////////////////////// EXPRESSIONS

    /** Parse function call expression */
    private FunctionalExpression function() {
        final String name = consume("D_Name").getData();
        consume("EC_ParL");

        final FunctionalExpression function = new FunctionalExpression(name);
        while (!match("EC_ParR")) {
            function.addArg(expression());
            match("EC_Comma");
        }

        return function;
    }

    /** Parse expression base */
    private Expression expression() {
        return ternary();
    }

    /** Parse ternary expression */
    private Expression ternary() {
        Expression result = logicalOr();

        // if (match("null"));

        return result;
    }

    /** Parse logical OR expression */
    private Expression logicalOr() {
        Expression result = logicalAnd();

        while (true) {
            if (match("C_Or")) {
                result = new ConditionalExpression(ConditionalExpression.Operator.Or, result, logicalAnd());
                continue;
            }
            break;
        }

        return result;
    }

    /** Parse logical AND expression */
    private Expression logicalAnd() {
        Expression result = equality();

        while (true) {
            if (match("C_And")) {
                result = new ConditionalExpression(ConditionalExpression.Operator.And, result, equality());
                continue;
            }
            break;
        }

        return result;
    }

    /** Parse equality expression */
    private Expression equality() {
        Expression result = conditional();

        if (match("C_Eq"))
            return new ConditionalExpression(ConditionalExpression.Operator.Eq, result, conditional());
        if (match("C_NEq"))
            return new ConditionalExpression(ConditionalExpression.Operator.NEq, result, conditional());

        return result;
    }

    /** Parse conditional expression */
    private Expression conditional() {
        Expression result = additive();

        while (true) {
            if (match("C_Lt")) {
                result = new ConditionalExpression(ConditionalExpression.Operator.Lt, result, additive());
                continue;
            }
            if (match("C_LtEq")) {
                result = new ConditionalExpression(ConditionalExpression.Operator.LtEq, result, additive());
                continue;
            }
            if (match("C_Gt")) {
                result = new ConditionalExpression(ConditionalExpression.Operator.Gt, result, additive());
                continue;
            }
            if (match("C_GtEq")) {
                result = new ConditionalExpression(ConditionalExpression.Operator.GtEq, result, additive());
                continue;
            }

            break;
        }

        return result;
    }

    /** Parse additive expression */
    private Expression additive() {
        Expression result = multiplicative();

        while (true) {
            if (match("B_Add")) {
                result = new BinaryExpression(BinaryExpression.Operator.Add, result, multiplicative());
                continue;
            }
            if (match("B_Sub")) {
                result = new BinaryExpression(BinaryExpression.Operator.Sub, result, multiplicative());
                continue;
            }
            break;
        }

        return result;
    }

    /** Parse multiplicative expression */
    private Expression multiplicative() {
        Expression result = exponentiative();

        while (true) {
            if (match("B_Mul")) {
                result = new BinaryExpression(BinaryExpression.Operator.Mul, result, exponentiative());
                continue;
            }
            if (match("B_Div")) {
                result = new BinaryExpression(BinaryExpression.Operator.Div, result, exponentiative());
                continue;
            }
            if (match("B_Mod")) {
                result = new BinaryExpression(BinaryExpression.Operator.Mod, result, exponentiative());
                continue;
            }

            break;
        }

        return result;
    }

    /** Parse exponentiative expression */
    private Expression exponentiative() {
        Expression result = unary();

        while (true) {
            if (match("B_Exp")) {
                result = new BinaryExpression(BinaryExpression.Operator.Exp, result, unary());
                continue;
            }
            break;
        }
        
        return result;
    }

    /** Parse unary expression */
    private Expression unary() {
        if (match("B_Sub"))
            return new UnaryExpression(UnaryExpression.Operator.Negate, primary());

        if (match("C_Neg"))
            return new UnaryExpression(UnaryExpression.Operator.Not, primary());

        return primary();
    }

    /** Parse primary expression */
    private Expression primary() {
        final Token c = get(0);

        if (match("D_Number"))
            return new ValueExpression(Double.parseDouble(c.getData()));

        if (match("D_Hex"))
            return new ValueExpression(Long.parseLong(c.getData(), 16));

        if (lookMatch(0, "D_Name") && lookMatch(1, "EC_ParL"))
            return function();

        if (match("D_Name"))
            return new VariableExpression(c.getData());

        if (match("D_String"))
            return new ValueExpression(c.getData());

        if (match("EC_ParL")) {
            Expression result = expression();
            match("EC_ParR");
            return result;
        }

        throw new RuntimeException("Ошибка: неизвестное выражение \"" + c + "\"");
    }

    ////////////////////////////////////////////////// SUPPORT METHODS

    /** Consume current token */
    private Token consume(String type) {
        final Token c = get(0);
        if (type != c.getType().getName())
            throw new RuntimeException("Ошибка: токен \"" + c.getType() + "\" не является \"" + type + "\"");
        pos++;
        return c;
    }

    /** Match token */
    private boolean match(String type) {
        final Token c = get(0);
        if (type != c.getType().getName())
            return false;
        pos++;
        return true;
    }

    /** Match token in position */
    private boolean lookMatch(int offset, String type) {
        return get(offset).getType().getName() == type;
    }

    /** Get current token or by offset */
    private Token get(int offset) {
        final int position = pos + offset;
        if (position >= size)
            return new Token(TokenList.EOF, "", position);
        return tokens.get(position);
    }
}
