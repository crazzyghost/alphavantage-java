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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A {@link Parser} for the metadata-plus-time-series reply shape most Alpha Vantage
 * endpoints use, where the first top-level key holds the metadata and the second
 * holds the data keyed by timestamp.
 * <p>
 * Subclasses receive those two maps already separated, so they only map fields.
 *
 * @author Sylvester Sefa-Yeboah
 * @param <T> the response type produced by this parser
 * @since 1.4.0
 */
public abstract class DefaultParser<T> extends Parser<T> {

    public abstract T parse(Map<String, String> metaData, Map<String, Map<String, String>> data);

    @Override
    @SuppressWarnings("unchecked")
    public T parse(Map<String, Object> object){
        List<String> keys = new ArrayList<>(object.keySet());

        if (keys.isEmpty()) {
            return onParseError("Empty JSON returned by the API, the symbol might not be supported.");
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
