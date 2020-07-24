package com.crazzyghost.alphavantage.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author crazzyghost
 * @since 1.4.0
 * @param <T> Response Type
 *
 * */
public abstract class DefaultParser<T> extends Parser<T> {

    public abstract T parse(Map<String, String> metaData, Map<String, Map<String, String>> data);

    @Override
    @SuppressWarnings("unchecked")
    public T parse(Map<String, Object> object){
        List<String> keys = new ArrayList<>(object.keySet());

        if (keys.isEmpty()) {
            return onParseError("Empty JSON returned by the API.");
        } else {
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
}
