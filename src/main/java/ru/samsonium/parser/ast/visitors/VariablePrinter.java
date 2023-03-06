package ru.samsonium.parser.ast.visitors;

import ru.samsonium.parser.ast.expressions.VariableExpression;
import ru.samsonium.parser.ast.statements.AssignmentStatement;

public class VariablePrinter extends AbstractVisitor {
    @Override
    public void visit(AssignmentStatement s) {
        super.visit(s);
        System.out.println(s.variable);
    }

    @Override
    public void visit(VariableExpression s) {
        super.visit(s);
        System.out.println(s.name);
    }
}
