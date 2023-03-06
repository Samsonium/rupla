package ru.samsonium.parser.ast.statements;

import ru.samsonium.parser.Visitor;
import ru.samsonium.parser.ast.Expression;
import ru.samsonium.parser.ast.Statement;

public class ForStatement implements Statement {
    public final Statement init;
    public final Expression term;
    public final Statement inc;
    public final Statement stmt;

    /*
     * constructor
     * 
     * 
     */
    
    public ForStatement(Statement init, Expression term, Statement inc, Statement stmt) {
        this.init = init;
        this.term = term;
        this.inc = inc;
        this.stmt = stmt;
    }

    @Override
    public void execute() {
        for (init.execute(); term.eval().getNumber() != 0; inc.execute()) {
            try {
                stmt.execute();
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
        return "цикл (" + init + "; " + term + "; " + inc + ") => " + stmt;
    }
}
