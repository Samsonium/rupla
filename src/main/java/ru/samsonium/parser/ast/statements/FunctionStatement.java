package ru.samsonium.parser.ast.statements;

import ru.samsonium.parser.Visitor;
import ru.samsonium.parser.ast.Statement;
import ru.samsonium.parser.ast.expressions.FunctionalExpression;

public class FunctionStatement implements Statement {
    public final FunctionalExpression function;

    public FunctionStatement(FunctionalExpression function) {
        this.function = function;
    }

    @Override
    public void execute() {
        function.eval();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return function.toString();
    }
}
