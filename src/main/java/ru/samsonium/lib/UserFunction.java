package ru.samsonium.lib;

import java.util.List;

import ru.samsonium.lib.types.NumberValue;
import ru.samsonium.lib.types.Value;
import ru.samsonium.parser.ast.Statement;
import ru.samsonium.parser.ast.statements.ReturnStatement;

public class UserFunction implements Function {
    private final List<String> argNames;
    private final Statement body;

    public UserFunction(List<String> argNames, Statement body) {
        this.argNames = argNames;
        this.body = body;
    }

    /** Get arguments count */
    public int getArgsCount() {
        return argNames.size();
    }

    /** Get argument name by index */
    public String getArgName(int index) {
        if (index < 0 || index >= getArgsCount())
            throw new RuntimeException("Ошибка: неверный индекс аргумента функции");
        return argNames.get(index);
    }

    @Override
    public Value exec(Value... args) {
        try {
            body.execute();
            return new NumberValue(0);
        } catch (ReturnStatement rt) {
            return rt.getResult();
        }
    }
    
}
