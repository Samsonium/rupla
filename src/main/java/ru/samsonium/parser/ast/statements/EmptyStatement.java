package ru.samsonium.parser.ast.statements;

import ru.samsonium.parser.Visitor;
import ru.samsonium.parser.ast.Statement;

public class EmptyStatement implements Statement {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    @Override
    public void execute() {}
}
