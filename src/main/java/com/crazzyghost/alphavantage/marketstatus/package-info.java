/*
 *
 * Copyright (c) 2025 Sylvester Sefa-Yeboah
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
 * Access to Alpha Vantage's global market status endpoint, reached through
 * {@link com.crazzyghost.alphavantage.AlphaVantage#marketStatus()}.
 * <p>
 * {@link com.crazzyghost.alphavantage.marketstatus.MarketStatus} takes no request
 * parameters beyond the fixed {@code MARKET_STATUS} function, since the endpoint
 * reports every market Alpha Vantage tracks rather than data scoped to a symbol.
 * Its parameters live in
 * {@link com.crazzyghost.alphavantage.marketstatus.request}, and the parsed result
 * in {@link com.crazzyghost.alphavantage.marketstatus.response}.
 */
package com.crazzyghost.alphavantage.marketstatus;
