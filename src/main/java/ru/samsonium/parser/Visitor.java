package ru.samsonium.parser;

import ru.samsonium.parser.ast.expressions.BinaryExpression;
import ru.samsonium.parser.ast.expressions.ConditionalExpression;
import ru.samsonium.parser.ast.expressions.ValueExpression;
import ru.samsonium.parser.ast.statements.AssignmentStatement;
import ru.samsonium.parser.ast.statements.BlockStatement;
import ru.samsonium.parser.ast.statements.BreakStatement;
import ru.samsonium.parser.ast.statements.DoWhileStatement;
import ru.samsonium.parser.ast.statements.ForStatement;
import ru.samsonium.parser.ast.statements.FuncDefineStatement;

public interface Visitor {
    // Statements
    void visit(AssignmentStatement expression);
    void visit(BlockStatement expression);
    void visit(BreakStatement expression);
    void visit(DoWhileStatement expression);
    void visit(ForStatement expression);
    void visit(FuncDefineStatement expression);

    // Expressions
    void visit(BinaryExpression expression);
    void visit(ConditionalExpression expression);
    void visit(ValueExpression expression);
}
