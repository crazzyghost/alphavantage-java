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
 * Fluent request builders for Alpha Vantage's stock time series endpoints.
 * <p>
 * {@link com.crazzyghost.alphavantage.timeseries.request.TimeSeriesRequest} holds
 * the ticker, endpoint function and response format every request shares. Four
 * subclasses —
 * {@link com.crazzyghost.alphavantage.timeseries.request.IntraDayRequest},
 * {@link com.crazzyghost.alphavantage.timeseries.request.DailyRequest},
 * {@link com.crazzyghost.alphavantage.timeseries.request.WeeklyRequest} and
 * {@link com.crazzyghost.alphavantage.timeseries.request.MonthlyRequest} — sample a
 * ticker's price history at a fixed cadence, while
 * {@link com.crazzyghost.alphavantage.timeseries.request.QuoteRequest} and
 * {@link com.crazzyghost.alphavantage.timeseries.request.RealtimeBulkQuoteRequest}
 * ask for a single current snapshot instead, for one ticker and for many
 * respectively. Reached through
 * {@link com.crazzyghost.alphavantage.timeseries.TimeSeries}.
 */
package com.crazzyghost.alphavantage.timeseries.request;
