/*
 *
 * Copyright (c) 2026 Sylvester Sefa-Yeboah
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

import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.parameters.DataType;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Resolves the {@link ParserDelegate} a request should be parsed with, based on the
 * {@link DataType} the request declares.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.9.0
 */
public final class ParserDelegates {

    private static final ParserDelegate<Map<String, Object>> JSON = Parser::parseJSON;
    private static final ParserDelegate<String> CSV = response -> response;

    private ParserDelegates() {}

    /**
     * Returns the {@link ParserDelegate} for {@code request}: the CSV delegate if the request
     * declares {@link DataType#CSV}, the JSON delegate otherwise.
     *
     * @param <T> the response type the returned delegate produces
     * @param request the endpoint request object
     * @return a delegate for parsing that request's response body
     */
    @SuppressWarnings("unchecked")
    public static <T> ParserDelegate<T> delegateFor(Object request) {
        return resolveDataType(request) == DataType.CSV
                ? (ParserDelegate<T>) CSV
                : (ParserDelegate<T>) JSON;
    }

    /**
     * Finds the {@link DataType} declared by {@code request}, searching its declared fields and
     * those of each superclass, and defaulting to {@link DataType#JSON} when no non-null
     * {@code DataType} field is found.
     *
     * @param request the endpoint request object
     * @return the request's declared data type, or {@link DataType#JSON} if none is set
     * @throws AlphaVantageException if a {@code DataType} field is found but cannot be read
     */
    private static DataType resolveDataType(Object request) {
        Class<?> cls = request.getClass();
        while (cls != null) {
            for (Field field : cls.getDeclaredFields()) {
                if (field.getType() == DataType.class) {
                    field.setAccessible(true);
                    try {
                        Object value = field.get(request);
                        if (value != null) return (DataType) value;
                    } catch (IllegalAccessException e) {
                        throw new AlphaVantageException(e.getMessage());
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        return DataType.JSON;
    }
}
