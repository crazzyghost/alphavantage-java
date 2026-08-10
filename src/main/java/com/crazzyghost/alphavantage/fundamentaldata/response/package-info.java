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
 * The deserialized responses for Alpha Vantage's fundamental data endpoints —
 * SEC-report-derived financial statements and a company profile.
 * <p>
 * {@link com.crazzyghost.alphavantage.fundamentaldata.response.BalanceSheetResponse},
 * {@link com.crazzyghost.alphavantage.fundamentaldata.response.CashFlowResponse} and
 * {@link com.crazzyghost.alphavantage.fundamentaldata.response.IncomeStatementResponse}
 * each hold a company's annual and quarterly
 * {@link com.crazzyghost.alphavantage.fundamentaldata.response.BalanceSheet},
 * {@link com.crazzyghost.alphavantage.fundamentaldata.response.CashFlow} or
 * {@link com.crazzyghost.alphavantage.fundamentaldata.response.IncomeStatement}
 * filings, as raw, as-reported figures rather than values Alpha Vantage normalizes
 * or scales.
 * {@link com.crazzyghost.alphavantage.fundamentaldata.response.EarningsResponse}
 * holds {@link com.crazzyghost.alphavantage.fundamentaldata.response.AnnualEarning}
 * and {@link com.crazzyghost.alphavantage.fundamentaldata.response.QuarterlyEarning}
 * earnings-per-share history instead, and
 * {@link com.crazzyghost.alphavantage.fundamentaldata.response.CompanyOverviewResponse}
 * wraps a single
 * {@link com.crazzyghost.alphavantage.fundamentaldata.response.CompanyOverview}
 * profile record.
 */
package com.crazzyghost.alphavantage.fundamentaldata.response;
