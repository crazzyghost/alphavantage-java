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

import com.squareup.moshi.JsonQualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Marks a {@link Long} field as carrying Alpha Vantage's numeric-or-{@code "None"}
 * convention, routing its deserialization through {@link NoneableLongAdapter}
 * instead of Moshi's default {@code Long} handling.
 * <p>
 * Several Alpha Vantage endpoints report a numeric field that hasn't been computed
 * yet — for example a company overview's market capitalization before the company
 * has reported financials — as the literal JSON string {@code "None"}, rather than
 * omitting the key or sending JSON {@code null}. Moshi cannot coerce that string into
 * a {@code Long} on its own, so a field holding such a value fails to parse unless
 * something intercepts it; a plain {@code Optional<Long>} field wouldn't help
 * either, since the problem is the incoming string, not the field's nullability.
 * Annotating the field with this qualifier instead redirects its deserialization to
 * {@link NoneableLongAdapter}, which distinguishes Alpha Vantage's explicit
 * {@code "None"} placeholder from an ordinary numeric value by producing
 * {@code null} for the former.
 * <p>
 * This annotation carries no data of its own; it is a Moshi {@link JsonQualifier}
 * used purely to select the adapter.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 */
@Retention(RetentionPolicy.RUNTIME)
@JsonQualifier
public @interface NoneableLong { }
