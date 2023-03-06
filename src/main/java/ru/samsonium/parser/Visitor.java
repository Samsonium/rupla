package ru.samsonium.parser;

import ru.samsonium.parser.ast.expressions.BinaryExpression;
import ru.samsonium.parser.ast.expressions.ConditionalExpression;
import ru.samsonium.parser.ast.expressions.FunctionalExpression;
import ru.samsonium.parser.ast.expressions.TernaryExpression;
import ru.samsonium.parser.ast.expressions.UnaryExpression;
import ru.samsonium.parser.ast.expressions.ValueExpression;
import ru.samsonium.parser.ast.statements.AssignmentStatement;
import ru.samsonium.parser.ast.statements.BlockStatement;
import ru.samsonium.parser.ast.statements.BreakStatement;
import ru.samsonium.parser.ast.statements.ConditionalStatement;
import ru.samsonium.parser.ast.statements.DoWhileStatement;
import ru.samsonium.parser.ast.statements.ForStatement;
import ru.samsonium.parser.ast.statements.FuncDefineStatement;
import ru.samsonium.parser.ast.statements.FunctionStatement;
import ru.samsonium.parser.ast.statements.OutputStatement;
import ru.samsonium.parser.ast.statements.ReturnStatement;

public interface Visitor {
    // Statements
    void visit(AssignmentStatement expression);
    void visit(BlockStatement expression);
    void visit(BreakStatement expression);
    void visit(ConditionalStatement expression);
    void visit(DoWhileStatement expression);
    void visit(ForStatement expression);
    void visit(FuncDefineStatement expression);
    void visit(FunctionStatement expression);
    void visit(OutputStatement expression);
    void visit(ReturnStatement expression);

    // Expressions
    void visit(BinaryExpression expression);
    void visit(ConditionalExpression expression);
    void visit(FunctionalExpression expression);
    void visit(TernaryExpression expression);
    void visit(UnaryExpression expression);
    void visit(ValueExpression expression);
}
