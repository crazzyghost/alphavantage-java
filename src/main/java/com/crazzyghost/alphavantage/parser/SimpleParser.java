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

import java.util.Map;

/**
 * A {@link Parser} counterpart to {@link DefaultParser} for subclasses that parse a
 * pre-shaped input of type {@code U} directly, rather than receiving the
 * metadata-plus-time-series split {@code DefaultParser} performs.
 * <p>
 * {@link #parse(Map)} is not implemented here: it always returns {@code null}, since
 * this class' parsing entry point is {@link #parse(Object) parse(U)}.
 *
 * @author Sylvester Sefa-Yeboah
 * @param <T> the response type produced by this parser
 * @param <U> the pre-shaped input type this parser consumes
 * @since 1.7.0
 */
public abstract class SimpleParser<T, U> extends Parser<T> {

    /**
     * Always returns {@code null}. {@code SimpleParser} does not parse the raw
     * decoded-JSON map directly; use {@link #parse(Object) parse(U)} instead.
     *
     * @param object the decoded JSON body, ignored
     * @return always {@code null}
     */
    @Override
    final public T parse(Map<String, Object> object) { return null; }

    /**
     * Parses a pre-shaped input into a response object.
     *
     * @param data the pre-shaped input to parse
     * @return the parsed response
     */
    public abstract T parse(U data);

}
