package ru.samsonium.parser.ast.visitors;

import ru.samsonium.parser.Visitor;
import ru.samsonium.parser.ast.Expression;
import ru.samsonium.parser.ast.Statement;
import ru.samsonium.parser.ast.expressions.*;
import ru.samsonium.parser.ast.statements.*;

public class AbstractVisitor implements Visitor {

    @Override
    public void visit(AssignmentStatement s) {
        s.expression.accept(this);
    }

    @Override
    public void visit(BlockStatement s) {
        for (Statement stmt : s.statements)
            stmt.accept(this);
    }

    @Override
    public void visit(BreakStatement s) {}

    @Override
    public void visit(ConditionalStatement s) {
        s.expression.accept(this);
        s.ifStmt.accept(this);
        if (s.elseStmt != null)
            s.elseStmt.accept(this);
    }

    @Override
    public void visit(DoWhileStatement s) {
        s.condition.accept(this);
    }

    @Override
    public void visit(ForStatement s) {
        s.init.accept(this);
        s.term.accept(this);
        s.inc.accept(this);
        s.stmt.accept(this);
    }

    @Override
    public void visit(FuncDefineStatement s) {
        s.body.accept(this);
    }

    @Override
    public void visit(FunctionStatement s) {
        s.function.accept(this);
    }

    @Override
    public void visit(OutputStatement s) {
        s.expression.accept(this);
    }

    @Override
    public void visit(ReturnStatement s) {
        s.expression.accept(this);
    }

    @Override
    public void visit(WhileStatement s) {
        s.condition.accept(this);
        s.statement.accept(this);
    }

    @Override
    public void visit(BinaryExpression s) {
        s.e1.accept(this);
        s.e2.accept(this);
    }

    @Override
    public void visit(ConditionalExpression s) {
        s.e1.accept(this);
        s.e2.accept(this);
    }

    @Override
    public void visit(FunctionalExpression s) {
        for (Expression arg : s.args)
            arg.accept(this);
    }

    @Override
    public void visit(TernaryExpression s) {
        s.condition.accept(this);
        s.trueExpr.accept(this);
        s.falseExpr.accept(this);
    }

    @Override
    public void visit(UnaryExpression s) {
        s.expression.accept(this);
    }

    @Override
    public void visit(ValueExpression s) {}

    @Override
    public void visit(VariableExpression s) {}
}
