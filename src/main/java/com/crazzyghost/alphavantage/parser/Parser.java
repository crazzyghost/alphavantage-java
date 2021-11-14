package com.crazzyghost.alphavantage.parser;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

/**
 * @author crazzyghost
 * @since 1.4.0
 * @param <T> Response Type
 * 
 * */
public abstract class Parser<T> {
    public abstract T onParseError(String error);
    public abstract T parse(Map<String, Object> object);

    public static Map<String, Object> parseJSON(String responseBody) throws IOException {
        if(responseBody == null) throw new IllegalArgumentException();
        Moshi moshi = new Moshi.Builder().build();
        Type type = Types.newParameterizedType(Map.class, String.class, Object.class);
        JsonAdapter<Map<String, Object>> adapter = moshi.adapter(type);
        return adapter.fromJson(responseBody);
    }
}