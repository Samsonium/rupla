package ru.samsonium.parser.ast.statements;

import ru.samsonium.parser.Visitor;
import ru.samsonium.parser.ast.Expression;
import ru.samsonium.parser.ast.Statement;

public class DoWhileStatement implements Statement {
    public final Expression condition;
    public final Statement statement;
    
    public DoWhileStatement(Expression condition, Statement statement) {
        this.condition = condition;
        this.statement = statement;
    }

    @Override
    public void execute() {
        do {
            try {
                statement.execute();
            } catch (BreakStatement bs) {
                break;
            }
        } while (condition.eval().getNumber() != 0);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "выполнять " + statement + " пока " + condition;
    }
}
