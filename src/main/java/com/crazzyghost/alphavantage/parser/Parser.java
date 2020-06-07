package com.crazzyghost.alphavantage.parser;

import java.util.Map;

/**
 * @author crazzyghost
 * @since 1.4.0
 * @param <T> Response Type
 * 
 * */
public abstract class Parser<T> {
    public abstract T onParseError(String error);
    public abstract T parse(Map<String, Object> object);
}