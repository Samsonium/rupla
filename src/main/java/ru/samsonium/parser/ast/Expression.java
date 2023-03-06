package ru.samsonium.parser.ast;

import ru.samsonium.lib.types.Value;

public interface Expression extends Node {
    Value eval();
}
