package ru.samsonium.parser.ast.statements;

import ru.samsonium.parser.Visitor;
import ru.samsonium.parser.ast.Expression;
import ru.samsonium.parser.ast.Statement;

public class OutputStatement implements Statement {
    public final Expression expression;

    public OutputStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute() {
        System.out.println(expression.eval());
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "вывод => " + expression;
    }
}
