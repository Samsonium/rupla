package ru.samsonium.lib.stdlib;

import ru.samsonium.lib.Function;
import ru.samsonium.lib.Functions;
import ru.samsonium.lib.types.NumberValue;
import ru.samsonium.lib.types.Value;

public class StdLib implements Lib {

    @Override
    public void init() {
        Function outputFunc = args -> {
            for (Value arg : args) {
                System.out.print(arg.getString());
                System.out.print(" ");
            }
            System.out.print("\n");
            return new NumberValue(0);
        };

        Functions.set("вывод", outputFunc);
        Functions.set("вывести", outputFunc);
    }
}
