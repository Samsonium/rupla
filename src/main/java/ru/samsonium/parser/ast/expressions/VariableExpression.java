package ru.samsonium.parser.ast.expressions;

import ru.samsonium.lib.Variables;
import ru.samsonium.lib.types.Value;
import ru.samsonium.parser.Visitor;
import ru.samsonium.parser.ast.Expression;

public class VariableExpression implements Expression {
    public final String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public Value eval() {
        if (!Variables.exists(name))
            throw new RuntimeException("Ошибка: переменная \"" + name + "\" не существует");
        return Variables.read(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return name;
    }
}
