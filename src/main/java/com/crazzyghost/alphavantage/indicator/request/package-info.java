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
 * Fluent request builders for Alpha Vantage's technical indicator endpoints.
 * <p>
 * {@link com.crazzyghost.alphavantage.indicator.request.IndicatorRequest} holds the
 * function, symbol, interval and data type every indicator shares. Concrete
 * subclasses add whatever further parameters their indicator requires:
 * {@link com.crazzyghost.alphavantage.indicator.request.PeriodicRequest} and
 * {@link com.crazzyghost.alphavantage.indicator.request.PeriodicSeriesRequest} add a
 * time period, and
 * {@link com.crazzyghost.alphavantage.indicator.request.SeriesRequest} adds a price
 * series field, covering most indicators between them, while
 * {@link com.crazzyghost.alphavantage.indicator.request.SimpleIndicatorRequest}
 * needs neither and bespoke multi-parameter indicators such as
 * {@link com.crazzyghost.alphavantage.indicator.request.BBANDSRequest},
 * {@link com.crazzyghost.alphavantage.indicator.request.MACDRequest} and
 * {@link com.crazzyghost.alphavantage.indicator.request.STOCHRequest} each define
 * their own. Reached through
 * {@link com.crazzyghost.alphavantage.indicator.Indicator}.
 *
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.request}.
 */
@Deprecated
package com.crazzyghost.alphavantage.indicator.request;
