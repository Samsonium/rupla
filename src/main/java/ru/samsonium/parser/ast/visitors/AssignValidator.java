package ru.samsonium.parser.ast.visitors;

import ru.samsonium.lib.Variables;
import ru.samsonium.parser.ast.statements.AssignmentStatement;

public class AssignValidator extends AbstractVisitor {
    @Override
    public void visit(AssignmentStatement s) {
        super.visit(s);
        if (Variables.exists(s.variable))
            throw new Error("Ошибка: нельзя присвоить значение константе");
    }
}
