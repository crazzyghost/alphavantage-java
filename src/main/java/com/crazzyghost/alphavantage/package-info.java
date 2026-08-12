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
 * Entry point for the Alpha Vantage Java client library.
 * <p>
 * A caller builds a {@link com.crazzyghost.alphavantage.Config} with an Alpha
 * Vantage API key, passes it to {@link com.crazzyghost.alphavantage.AlphaVantage#init(Config)},
 * and reaches every endpoint through the shared singleton returned by
 * {@link com.crazzyghost.alphavantage.AlphaVantage#api()}. From there, one facade
 * method per domain — {@code timeSeries()}, {@code forex()}, {@code exchangeRate()},
 * {@code crypto()}, {@code technicalIndicator()},
 * {@code fundamentalData()}, {@code economicIndicator()}, {@code marketStatus()} and
 * {@code search()} — hands off to that domain's subpackage, where a request is built
 * fluently and then fetched. {@code indicator()} also exists but is deprecated in
 * favor of {@code technicalIndicator()}.
 * <p>
 * Every domain facade implements {@link com.crazzyghost.alphavantage.Fetcher},
 * resolves its built request to a URL through {@link com.crazzyghost.alphavantage.UrlExtractor},
 * and reports failure as an {@link com.crazzyghost.alphavantage.AlphaVantageException}.
 */
package com.crazzyghost.alphavantage;
