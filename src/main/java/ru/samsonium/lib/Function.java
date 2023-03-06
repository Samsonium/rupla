package ru.samsonium.lib;

import ru.samsonium.lib.types.Value;

public interface Function {
    Value exec(Value... args);
}
