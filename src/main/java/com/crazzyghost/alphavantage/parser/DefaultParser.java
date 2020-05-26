package com.crazzyghost.alphavantage.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class DefaultParser<T> extends Parser<T> {

    public abstract T parse(Map<String, String> metaData, Map<String, Map<String, String>> units);

    @Override
    @SuppressWarnings("unchecked")
    public T parse(Map<String, Object> object){
        List<String> keys = new ArrayList<>(object.keySet());

        Map<String, String> metaData;
        Map<String, Map<String, String>> units;

        try{
            metaData = (Map<String, String>) object.get(keys.get(0));
            units = (Map<String, Map<String,String>>) object.get(keys.get(1));
        }catch (ClassCastException ex){
            return onParseError(object.get(keys.get(0)).toString());
        }

        return parse(metaData, units);
    }

}