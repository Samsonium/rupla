package ru.samsonium.parser.ast.statements;

import java.util.ArrayList;
import java.util.List;

import ru.samsonium.parser.Visitor;
import ru.samsonium.parser.ast.Statement;

public class BlockStatement implements Statement {
    public final List<Statement> statements;

    public BlockStatement() {
        statements = new ArrayList<>();
    }

    /** Add statement to statements list */
    public void add(Statement statement) {
        statements.add(statement);
    }

    @Override
    public void execute() {
        for (Statement s : statements)
            s.execute();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        for (Statement s : statements)
            result.append(s.toString()).append(System.lineSeparator());
        return result.toString();
    }
}
