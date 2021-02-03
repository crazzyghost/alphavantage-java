package com.crazzyghost.alphavantage.parser;

import java.util.Map;

public abstract class SimpleParser<T, U> extends Parser<T> {

    @Override
    final public T parse(Map<String, Object> object) { return null; }

    public abstract T parse(U data);

}
