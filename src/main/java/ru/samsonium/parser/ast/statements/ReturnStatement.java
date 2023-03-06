package ru.samsonium.parser.ast.statements;

import ru.samsonium.lib.types.Value;
import ru.samsonium.parser.Visitor;
import ru.samsonium.parser.ast.Expression;
import ru.samsonium.parser.ast.Statement;

public class ReturnStatement extends RuntimeException implements Statement {
    public final Expression expression;
    private Value result;

    public ReturnStatement(Expression expression) {
        this.expression = expression;
    }

    public Value getResult() {
        return result;
    }

    @Override
    public void execute() {
        result = expression.eval();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "вернуть => " + expression;
    }
}
