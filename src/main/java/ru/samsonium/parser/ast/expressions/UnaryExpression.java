package ru.samsonium.parser.ast.expressions;

import ru.samsonium.lib.types.NumberValue;
import ru.samsonium.lib.types.Value;
import ru.samsonium.parser.Visitor;
import ru.samsonium.parser.ast.Expression;

public class UnaryExpression implements Expression {
    public static enum Operator {
        Negate("-"),
        Not("не");

        private final String name;
        private Operator(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public final Expression expression;
    public final Operator op;
    
    public UnaryExpression(Expression expression, Operator op) {
        this.expression = expression;
        this.op = op;
    }

    @Override
    public Value eval() {
        final Value val = expression.eval();
        switch (op) {
            case Negate: return new NumberValue(-val.getNumber());
            case Not: return new NumberValue(val.getNumber() != 0 ? 0 : 1);

            default:
                throw new RuntimeException("Ошибка: неизвестная операция \"" + op + "\"");
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return String.format("%s %s", op, expression);
    }
}
