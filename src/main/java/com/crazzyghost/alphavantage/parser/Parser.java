/*
 *
 * Copyright (c) 2020 Sylvester Sefa-Yeboah
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
 * Turns the decoded JSON of an Alpha Vantage reply into a response object, and
 * carries the Moshi-backed JSON helpers the concrete parsers share.
 * <p>
 * Subclasses supply the shape-specific mapping in {@link #parse(Map)} and decide
 * what an unparseable reply becomes in {@link #onParseError(String)}.
 *
 * @author Sylvester Sefa-Yeboah
 * @param <T> the response type produced by this parser
 * @since 1.4.0
 */
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