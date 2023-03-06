package ru.samsonium.lib.modules;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;

import ru.samsonium.lib.Constants;
import ru.samsonium.lib.Function;
import ru.samsonium.lib.Functions;
import ru.samsonium.lib.Variables;
import ru.samsonium.lib.types.NumberValue;

public class MathLib implements Lib {
    private static final DoubleFunction<NumberValue> doubleToNumber = v -> new NumberValue(v);

    @Override
    public void init() {
        Functions.set("Абсолют", getConvertFunc(Math::abs));
        Functions.set("Синус", getConvertFunc(Math::sin));
        Functions.set("Косинус", getConvertFunc(Math::cos));
        Functions.set("Корень", getConvertFunc(Math::sqrt));
        Functions.set("Градусы", getConvertFunc(Math::toDegrees));
        Functions.set("Радианы", getConvertFunc(Math::toRadians));

        Functions.set("Степень", getBinaryFunctionConvert(Math::pow));
        Functions.set("АркТангенс2", getBinaryFunctionConvert(Math::atan2));

        Variables.set("ПИ", new NumberValue(Math.PI));
        Variables.set("Е", new NumberValue(Math.E));
    }

    /** Get unary function */
    private static Function getConvertFunc(DoubleUnaryOperator op) {
        return args -> {
            if (args.length != 1)
                throw new RuntimeException("Ошибка: отсутствует аргумент");
            return doubleToNumber.apply(op.applyAsDouble(args[0].getNumber()));
        };
    }

    /** Get binary function */
    private static Function getBinaryFunctionConvert(DoubleBinaryOperator op) {
        return args -> {
            if (args.length != 2)
                throw new RuntimeException("Ошибка: необходимо два аргумента");
            return doubleToNumber.apply(op.applyAsDouble(args[0].getNumber(), args[1].getNumber()));
        };
    }
}
