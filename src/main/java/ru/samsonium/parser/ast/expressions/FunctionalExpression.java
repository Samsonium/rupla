package ru.samsonium.parser.ast.expressions;

import java.util.ArrayList;
import java.util.List;

import ru.samsonium.lib.Function;
import ru.samsonium.lib.Functions;
import ru.samsonium.lib.UserFunction;
import ru.samsonium.lib.Variables;
import ru.samsonium.lib.types.Value;
import ru.samsonium.parser.Visitor;
import ru.samsonium.parser.ast.Expression;

public class FunctionalExpression implements Expression {
    public final String name;
    public final List<Expression> args;
    
    public FunctionalExpression(String name) {
        this.name = name;
        args = new ArrayList<>();
    }

    public FunctionalExpression(String name, List<Expression> args) {
        this.name = name;
        this.args = args;
    }

    public void addArg(Expression arg) {
        args.add(arg);
    }

    @Override
    public Value eval() {
        final int argsCount = args.size();
        final Value[] values = new Value[argsCount];

        for (int i = 0; i < argsCount; i++)
            values[i] = args.get(i).eval();

        final Function function = Functions.get(name);
        if (function instanceof UserFunction) {
            final UserFunction userFunc = (UserFunction) function;
            if (argsCount != userFunc.getArgsCount())
                throw new RuntimeException(String.format(
                    "Ошибка: функции %s требуется %d аргументов, указано %d", 
                    name, userFunc.getArgsCount(), argsCount));
            
            Variables.push();
            for (int i = 0; i < argsCount; i++)
                Variables.set(userFunc.getArgName(i), values[i]);

            final Value result = userFunc.exec(values);
            Variables.pop();
            return result;
        }

        return function.exec(values);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "вызов " + name + "(" + args.toString() + ")";
    }
}
