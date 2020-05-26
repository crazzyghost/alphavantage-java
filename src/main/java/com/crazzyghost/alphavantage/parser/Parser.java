package com.crazzyghost.alphavantage.parser;

import java.util.Map;

/**
 * @param <T> Response Type
 * */
public abstract class Parser<T> {
    public abstract T onParseError(String error);
    public abstract T parse(Map<String, Object> object);
}