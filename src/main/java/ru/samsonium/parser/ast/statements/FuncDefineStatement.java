package ru.samsonium.parser.ast.statements;

import java.util.List;

import ru.samsonium.lib.Functions;
import ru.samsonium.lib.UserFunction;
import ru.samsonium.parser.Visitor;
import ru.samsonium.parser.ast.Statement;

public class FuncDefineStatement implements Statement {
    public final String name;
    public final List<String> argNames;
    public final Statement body;
    
    public FuncDefineStatement(String name, List<String> argNames, Statement body) {
        this.name = name;
        this.argNames = argNames;
        this.body = body;
    }

    @Override
    public void execute() {
        Functions.set(name, new UserFunction(argNames, body));
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return String.format("функция %s (%s) => %s", name, argNames, body);
    }
}
