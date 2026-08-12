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
 * Access to Alpha Vantage's digital and physical currency exchange rate endpoint,
 * reached through {@link com.crazzyghost.alphavantage.AlphaVantage#exchangeRate()}.
 * <p>
 * Unlike the other domain packages, this one has no separate {@code request}/
 * {@code response} split: {@link com.crazzyghost.alphavantage.exchangerate.ExchangeRate}
 * collects the currency pair via {@code fromCurrency(String)} and
 * {@code toCurrency(String)},
 * {@link com.crazzyghost.alphavantage.exchangerate.ExchangeRateRequest} carries the
 * built {@code CURRENCY_EXCHANGE_RATE} parameters, and
 * {@link com.crazzyghost.alphavantage.exchangerate.ExchangeRateResponse} holds the
 * quoted rate together with the bid/ask prices, where the API has an order book for
 * the pair.
 */
package com.crazzyghost.alphavantage.exchangerate;
