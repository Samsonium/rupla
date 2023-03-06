package ru.samsonium.parser.ast.expressions;

import ru.samsonium.lib.types.NumberValue;
import ru.samsonium.lib.types.StringValue;
import ru.samsonium.lib.types.Value;
import ru.samsonium.parser.Visitor;
import ru.samsonium.parser.ast.Expression;

public class BinaryExpression implements Expression {
    public static enum Operator {
        Add("+"),
        Sub("-"),
        Mul("*"),
        Div("/"),
        Exp("^"),
        Mod("%");

        private final String name;
        private Operator(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public final Operator op;
    public final Expression e1, e2;

    public BinaryExpression(Operator op, Expression e1, Expression e2) {
        this.op = op;
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public Value eval() {
        final Value v1 = e1.eval();
        final Value v2 = e2.eval();

        if (v1 instanceof StringValue) {
            final String s1 = v1.getString();
            switch (op) {
                case Mul: {
                    final int needIters = (int) v2.getNumber();
                    final StringBuilder buff = new StringBuilder();
                    for (int i = 0; i < needIters; i++)
                        buff.append(s1);
                    return new StringValue(buff.toString());
                }

                case Add:
                default:
                    return new StringValue(s1 + v2.getString());
            }
        }

        final double n1 = v1.getNumber();
        final double n2 = v2.getNumber();
        double result;

        switch (op) {
            case Add: result = n1 + n2; break;
            case Sub: result = n1 - n2; break;
            case Mul: result = n1 * n2; break;
            case Div: result = n1 / n2; break;
            case Exp: result = Math.pow(n1, n2); break;
            case Mod: result = n1 % n2; break;

            default:
                throw new RuntimeException("Ошибка: неизвестная операция \" + op + \"");
        }
        return new NumberValue(result);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return String.format("%s %s %S", e1, op, e2);
    }
}
