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
 * Fluent request builders for Alpha Vantage's foreign exchange endpoints, one per
 * cadence.
 * <p>
 * {@link com.crazzyghost.alphavantage.forex.request.ForexRequest} holds the
 * currency pair and response format every cadence shares;
 * {@link com.crazzyghost.alphavantage.forex.request.IntraDayRequest},
 * {@link com.crazzyghost.alphavantage.forex.request.DailyRequest},
 * {@link com.crazzyghost.alphavantage.forex.request.WeeklyRequest} and
 * {@link com.crazzyghost.alphavantage.forex.request.MonthlyRequest} each pin their
 * own endpoint function and add whatever extra parameters that cadence accepts.
 * Reached through {@link com.crazzyghost.alphavantage.forex.Forex}.
 */
package com.crazzyghost.alphavantage.forex.request;
