package com.crazzyghost.alphavantage.parser.util;

import java.util.function.Function;


public final class ParserUtil {
    private ParserUtil() {
    }

    public static <T extends Number> T getNumberFromString(String s, Function<String, T> parser) {
        T result = null;
        try {
            result = parser.apply(s);
        } catch (NumberFormatException ex) {
        }
        return result;
    }
}
