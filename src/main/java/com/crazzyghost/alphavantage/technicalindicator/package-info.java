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
 * Access to Alpha Vantage's technical indicator endpoints — moving averages,
 * oscillators, momentum, volatility, cycle and Hilbert transform studies — reached
 * through {@link com.crazzyghost.alphavantage.AlphaVantage#technicalIndicator()}.
 * <p>
 * {@link com.crazzyghost.alphavantage.technicalindicator.TechnicalIndicator}
 * exposes one request proxy per indicator, for example {@code sma()},
 * {@code ema()}, {@code rsi()}, {@code macd()} and {@code bbands()}. Fluent
 * parameters live in
 * {@link com.crazzyghost.alphavantage.technicalindicator.request}, and the parsed
 * results in {@link com.crazzyghost.alphavantage.technicalindicator.response} —
 * this facade supersedes the deprecated
 * {@link com.crazzyghost.alphavantage.indicator.Indicator}.
 */
package com.crazzyghost.alphavantage.technicalindicator;
