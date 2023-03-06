package ru.samsonium.parser.ast.expressions;

import ru.samsonium.lib.types.NumberValue;
import ru.samsonium.lib.types.StringValue;
import ru.samsonium.lib.types.Value;
import ru.samsonium.parser.Visitor;
import ru.samsonium.parser.ast.Expression;

public class ValueExpression implements Expression {
    public final Value value;

    public ValueExpression(double value) {
        this.value = new NumberValue(value);
    }

    public ValueExpression(String value) {
        this.value = new StringValue(value);
    }

    @Override
    public Value eval() {
        return value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return value.getString();
    }
}
