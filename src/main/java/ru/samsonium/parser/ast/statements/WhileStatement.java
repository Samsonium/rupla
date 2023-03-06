package ru.samsonium.parser.ast.statements;

import ru.samsonium.parser.Visitor;
import ru.samsonium.parser.ast.Expression;
import ru.samsonium.parser.ast.Statement;

public class WhileStatement implements Statement {
    public final Expression condition;
    public final Statement statement;
    
    public WhileStatement(Expression condition, Statement statement) {
        this.condition = condition;
        this.statement = statement;
    }

    @Override
    public void execute() {
        while (condition.eval().getNumber() != 0) {
            try {
                statement.execute();
            } catch (BreakStatement br) {
                break;
            }
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "пока " + condition + " => " + statement;
    }
}
