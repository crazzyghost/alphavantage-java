package com.crazzyghost.alphavantage.parser;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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
        Moshi moshi = new Moshi.Builder()
                .add(new NoneableDoubleAdapter())
                .add(new NoneableLongAdapter())
                .build();
        Type type = Types.newParameterizedType(Map.class, String.class, Object.class);
        JsonAdapter<Map<String, Object>> adapter = moshi.adapter(type);
        return adapter.fromJson(responseBody);
    }

    public static <U> U parseJSON(String responseBody, Class<U> c) throws IOException {
        if(responseBody == null) throw new IllegalArgumentException();
        Moshi moshi = new Moshi.Builder()
                .add(new NoneableDoubleAdapter())
                .add(new NoneableLongAdapter())
                .build();
        Type type = Types.getRawType(c);
        JsonAdapter<U> adapter = moshi.adapter(type);
        return adapter.fromJson(responseBody);
    }

    public static <U> List<U> parseJSONList(Object object, Class<U> klass) {
        if(object == null) throw new IllegalArgumentException();
        Moshi moshi = new Moshi.Builder()
                .add(new NoneableDoubleAdapter())
                .add(new NoneableLongAdapter())
                .build();
        Type type = Types.newParameterizedType(List.class, klass);
        JsonAdapter<List<U>> adapter = moshi.adapter(type);
        return adapter.fromJsonValue(object);
    }

    public static String toJSON(Map<String, Object> data) throws IOException {
        if(data == null) throw new IllegalArgumentException();
        Moshi moshi = new Moshi.Builder()
                .add(new NoneableDoubleAdapter())
                .add(new NoneableLongAdapter())
                .build();
        Type type = Types.newParameterizedType(Map.class, String.class, Object.class);
        return moshi.adapter(type).toJson(data);
    }

    public static <N extends Number> N getNumberFromString(String s, Function<String, N> parser) {
        N result = null;
        try {
            result = parser.apply(s);
        } catch (NumberFormatException ex) {
        }

        return result;
    }
}