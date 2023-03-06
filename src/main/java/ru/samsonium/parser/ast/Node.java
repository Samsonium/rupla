package ru.samsonium.parser.ast;

import ru.samsonium.parser.Visitor;

public interface Node {
    void accept(Visitor visitor);
}
