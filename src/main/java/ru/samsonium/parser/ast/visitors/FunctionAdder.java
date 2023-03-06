package ru.samsonium.parser.ast.visitors;

import ru.samsonium.parser.ast.statements.FuncDefineStatement;

public class FunctionAdder extends AbstractVisitor {
    @Override
    public void visit(FuncDefineStatement s) {
        super.visit(s);
        s.execute();
    }
}
