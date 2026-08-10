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

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

/**
 * Moshi (de)serialization adapter for {@link Double} fields annotated
 * {@link NoneableDouble}, registered on every {@code Moshi.Builder} {@link Parser}
 * constructs.
 * <p>
 * {@link #fromJson(String)} parses Alpha Vantage's numeric-or-{@code "None"} string
 * convention; {@link #toJson(Double)} is unimplemented, since this library only reads
 * Alpha Vantage responses and never serializes a {@code Noneable}-typed field back to
 * JSON.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.6.1
 */
public class NoneableDoubleAdapter {

    /**
     * Always throws. This library only reads Alpha Vantage responses; it never
     * serializes a {@link NoneableDouble}-annotated field back to JSON, so this
     * side of the adapter has no implementation.
     *
     * @param l the value that would be serialized
     * @return never returns
     * @throws UnsupportedOperationException always
     */
    @ToJson
    public Double toJson(@NoneableDouble Double l) {
        throw new UnsupportedOperationException();
    }

    /**
     * Parses a JSON string field into a {@code Double}, per Alpha Vantage's
     * numeric-or-{@code "None"} convention: a valid numeric string becomes its
     * {@code Double} value, and any other string — including the literal
     * {@code "None"} Alpha Vantage sends for an unavailable field — becomes
     * {@code null}.
     *
     * @param s the raw JSON string value
     * @return the parsed value, or {@code null} if {@code s} is not a valid number
     */
    @FromJson
    @NoneableDouble
    public Double fromJson(String s) {
        return Parser.getNumberFromString(s, Double::parseDouble);
    }

}
