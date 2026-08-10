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

    /**
     * Produces the value this parser returns in place of a response object when the
     * reply cannot be parsed, for example an empty or unrecognizable JSON body.
     *
     * @param error a description of what went wrong
     * @return the fallback value for a failed parse
     */
    public abstract T onParseError(String error);

    /**
     * Maps the decoded JSON body of an Alpha Vantage reply to a response object.
     *
     * @param object the decoded JSON body, as returned by {@link #parseJSON(String)}
     * @return the parsed response
     */
    public abstract T parse(Map<String, Object> object);

    /**
     * Decodes a raw JSON response body into a generic {@code String}-keyed map,
     * with the {@link NoneableDouble}/{@link NoneableLong} adapters registered so
     * numeric-or-{@code "None"} fields decode correctly.
     *
     * @param responseBody the raw JSON response body
     * @return the decoded JSON, as a map from each top-level key to its value
     * @throws IOException if the response body is not well-formed JSON
     * @throws IllegalArgumentException if {@code responseBody} is {@code null}
     */
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

    /**
     * Decodes a raw JSON response body directly into an instance of {@code c}, with
     * the {@link NoneableDouble}/{@link NoneableLong} adapters registered so
     * numeric-or-{@code "None"} fields decode correctly.
     *
     * @param <U> the type to decode the response body into
     * @param responseBody the raw JSON response body
     * @param c the class to decode the response body into
     * @return the decoded object
     * @throws IOException if the response body is not well-formed JSON
     * @throws IllegalArgumentException if {@code responseBody} is {@code null}
     */
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

    /**
     * Converts an already-decoded JSON value, typically a {@code List} produced by
     * {@link #parseJSON(String)}, into a {@code List} of {@code klass} instances,
     * with the {@link NoneableDouble}/{@link NoneableLong} adapters registered so
     * numeric-or-{@code "None"} fields decode correctly.
     *
     * @param <U> the element type to decode each list entry into
     * @param object the already-decoded JSON value, expected to be a {@code List}
     * @param klass the class to decode each element into
     * @return the decoded list
     * @throws IllegalArgumentException if {@code object} is {@code null}
     */
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

    /**
     * Encodes a {@code String}-keyed map back into a JSON string, with the
     * {@link NoneableDouble}/{@link NoneableLong} adapters registered.
     * <p>
     * Since neither adapter implements its {@code toJson} side (see
     * {@link NoneableDoubleAdapter#toJson(Double)}), {@code data} must not contain a
     * value that would route through one of them.
     *
     * @param data the map to encode
     * @return the JSON-encoded map
     * @throws IOException if the map cannot be encoded
     * @throws IllegalArgumentException if {@code data} is {@code null}
     */
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