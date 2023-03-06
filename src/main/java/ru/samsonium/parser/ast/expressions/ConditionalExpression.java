package ru.samsonium.parser.ast.expressions;

import ru.samsonium.lib.types.NumberValue;
import ru.samsonium.lib.types.StringValue;
import ru.samsonium.lib.types.Value;
import ru.samsonium.parser.Visitor;
import ru.samsonium.parser.ast.Expression;

public class ConditionalExpression implements Expression {
    public static enum Operator {
        Eq  ("=="),
        NEq ("!="),
        Gt  (">"),
        GtEq(">="),
        Lt  ("<"),
        LtEq("<="),
        And ("и"),
        Or  ("или");

        private final String name;
        private Operator(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public final Expression e1, e2;
    public final Operator op;

    public ConditionalExpression(Operator op, Expression e1, Expression e2) {
        this.op = op;
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public Value eval() {
        final Value v1 = e1.eval();
        final Value v2 = e2.eval();
        switch (op) {
            case And: return NumberValue.fromBool(
                (v1.getNumber() != 0) && (v2.getNumber() != 0)
            );

            case Or: return NumberValue.fromBool(
                (v1.getNumber() != 0) || (v2.getNumber() != 0)
            );
        }

        double n1, n2;
        if (v1 instanceof StringValue) {
            n1 = v1.getString().compareTo(v2.getString());
            n2 = 0;
        } else {
            n1 = v1.getNumber();
            n2 = v2.getNumber();
        }

        boolean result;
        switch (op) {
            case Eq     : result = n1 == n2; break;
            case NEq    : result = n1 != n2; break;
            case Lt     : result = n1 < n2; break;
            case LtEq   : result = n1 <= n2; break;
            case Gt     : result = n1 > n2; break;
            case GtEq   : result = n1 >= n2; break;

            default:
                throw new RuntimeException("Ошибка: неизвестная операция \"" + op + "\"");
        }

        return NumberValue.fromBool(result);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", e1, op.getName(), e2);
    }
}
