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

/**
 * The Moshi-backed layer that turns a raw Alpha Vantage JSON reply into a typed
 * response object.
 * <p>
 * {@link com.crazzyghost.alphavantage.parser.Parser} is the base type every
 * response's parser extends, with {@link com.crazzyghost.alphavantage.parser.DefaultParser}
 * and {@link com.crazzyghost.alphavantage.parser.SimpleParser} covering its two
 * input shapes. {@link com.crazzyghost.alphavantage.parser.NoneableDouble} and
 * {@link com.crazzyghost.alphavantage.parser.NoneableLong} — backed by
 * {@link com.crazzyghost.alphavantage.parser.NoneableDoubleAdapter} and
 * {@link com.crazzyghost.alphavantage.parser.NoneableLongAdapter} — mark fields
 * whose value Alpha Vantage may report as the literal string {@code "None"},
 * distinguishing that explicitly-absent case from an ordinary number or a JSON
 * {@code null}.
 */
package com.crazzyghost.alphavantage.parser;
