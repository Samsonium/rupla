package ru.samsonium.parser.ast.statements;

import ru.samsonium.parser.Visitor;
import ru.samsonium.parser.ast.Expression;
import ru.samsonium.parser.ast.Statement;

public class ConditionalStatement implements Statement {
    public final Expression expression;
    public final Statement ifStmt, elseStmt;
    
    public ConditionalStatement(Expression expression, Statement ifStmt, Statement elseStmt) {
        this.expression = expression;
        this.ifStmt = ifStmt;
        this.elseStmt = elseStmt;
    }

    @Override
    public void execute() {
        final double result = expression.eval().getNumber();
        if (result != 0) 
            ifStmt.execute();
        else if (elseStmt != null)
            elseStmt.execute();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder("если " + expression + " => " + ifStmt);
        if (elseStmt != null)
            buf.append("; иначе => " + elseStmt);
        return buf.toString();
    }
}
