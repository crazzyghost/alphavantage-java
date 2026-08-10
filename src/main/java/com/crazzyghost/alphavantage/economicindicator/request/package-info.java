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
 * Fluent request builders for Alpha Vantage's economic indicator endpoints, one per
 * indicator.
 * <p>
 * {@link com.crazzyghost.alphavantage.economicindicator.request.EconomicIndicatorRequest}
 * holds the function and response format every indicator request shares; each
 * concrete subclass — for example
 * {@link com.crazzyghost.alphavantage.economicindicator.request.TreasuryYieldRequest}
 * or {@link com.crazzyghost.alphavantage.economicindicator.request.CpiRequest} —
 * pins its own function code and adds whatever further parameters that indicator
 * accepts, such as interval or maturity. Reached through
 * {@link com.crazzyghost.alphavantage.economicindicator.EconomicIndicator}.
 */
package com.crazzyghost.alphavantage.economicindicator.request;
