/*
 *
 * Copyright (c) 2018 Sylvester Sefa-Yeboah
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
package com.crazzyghost.alphavantage.parameters;

/**
 * The {@code function} API parameter, selecting which Alpha Vantage endpoint a
 * request calls.
 * <p>
 * Every request builder in the library sets one of these constants, typically
 * as a fixed default in the builder's constructor (for example,
 * {@code DailyRequest.Builder} defaults to {@link #TIME_SERIES_DAILY}).
 * {@link #toString()} is not overridden, so each constant serializes to its own
 * declared Java name unchanged (for example, {@link #SMA} serializes to
 * {@code "SMA"}) — this is also Alpha Vantage's own function code for that
 * constant.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.1
 */
public enum Function {
    //Stock Time Series Functions

    /**
     * Intraday time series of an equity's open, high, low, close, and volume,
     * at a selectable interval within the current or most recent trading day.
     */
    TIME_SERIES_INTRADAY,

    /**
     * Full-history intraday time series of an equity, delivered as monthly
     * CSV slices going back up to 20 years.
     */
    TIME_SERIES_INTRADAY_EXTENDED,

    /**
     * Daily time series of an equity's open, high, low, close, and volume,
     * unadjusted for splits or dividends.
     */
    TIME_SERIES_DAILY,

    /**
     * Daily time series of an equity's open, high, low, close, and volume,
     * adjusted for splits and dividends.
     */
    TIME_SERIES_DAILY_ADJUSTED,

    /**
     * Weekly time series of an equity's open, high, low, close, and volume,
     * covering the last trading day of each week.
     */
    TIME_SERIES_WEEKLY,

    /**
     * Weekly time series of an equity's open, high, low, close, and volume,
     * adjusted for splits and dividends.
     */
    TIME_SERIES_WEEKLY_ADJUSTED,

    /**
     * Monthly time series of an equity's open, high, low, close, and volume,
     * covering the last trading day of each month.
     */
    TIME_SERIES_MONTHLY,

    /**
     * Monthly time series of an equity's open, high, low, close, and volume,
     * adjusted for splits and dividends.
     */
    TIME_SERIES_MONTHLY_ADJUSTED,

    /** Latest price and volume snapshot for a single equity. */
    GLOBAL_QUOTE,

    /**
     * Latest price and volume snapshot for up to 100 equities in a single
     * request.
     */
    REALTIME_BULK_QUOTES,

    /**
     * Current market status — open, closed, pre-market, or post-market — of
     * major global trading venues.
     */
    MARKET_STATUS,

    /** Best-matching symbols and market information for a search keyword. */
    SYMBOL_SEARCH,

    //Exchange Rate

    /**
     * Realtime exchange rate for a physical currency pair, a digital currency
     * pair, or a physical/digital currency pair.
     */
    CURRENCY_EXCHANGE_RATE,

    //Forex (FX) Functions

    /**
     * Intraday time series of a forex currency pair's open, high, low, and
     * close, at a selectable interval.
     */
    FX_INTRADAY,

    /** Daily time series of a forex currency pair's open, high, low, and close. */
    FX_DAILY,

    /** Weekly time series of a forex currency pair's open, high, low, and close. */
    FX_WEEKLY,

    /** Monthly time series of a forex currency pair's open, high, low, and close. */
    FX_MONTHLY,

    //Digital Currency Functions

    /**
     * Daily time series of a digital currency traded on a specified market,
     * with open, high, low, close, and volume in both the market currency and
     * USD.
     */
    DIGITAL_CURRENCY_DAILY,

    /**
     * Weekly time series of a digital currency traded on a specified market,
     * with open, high, low, close, and volume in both the market currency and
     * USD.
     */
    DIGITAL_CURRENCY_WEEKLY,

    /**
     * Monthly time series of a digital currency traded on a specified market,
     * with open, high, low, close, and volume in both the market currency and
     * USD.
     */
    DIGITAL_CURRENCY_MONTHLY,

    /** Alpha Vantage's proprietary FCAS rating for a digital currency. */
    CRYPTO_RATING,

    /**
     * Intraday time series of a digital currency traded on a specified
     * market, at a selectable interval.
     */
    CRYPTO_INTRADAY,

    //Technical Indicators

    /** Simple moving average (SMA) values. */
    SMA,

    /** Exponential moving average (EMA) values. */
    EMA,

    /** Weighted moving average (WMA) values. */
    WMA,

    /** Double exponential moving average (DEMA) values. */
    DEMA,

    /** Triple exponential moving average (TEMA) values. */
    TEMA,

    /** Triangular moving average (TRIMA) values. */
    TRIMA,

    /** Kaufman adaptive moving average (KAMA) values. */
    KAMA,

    /**
     * MESA adaptive moving average (MAMA) values, alongside its following
     * adaptive moving average (FAMA).
     */
    MAMA,

    /** Volume weighted average price (VWAP) for intraday time series. */
    VWAP,

    /** T3, Tillson's triple exponential moving average, values. */
    T3,

    /** Moving average convergence / divergence (MACD) values. */
    MACD,

    /**
     * Moving average convergence / divergence (MACD) values, with
     * configurable moving-average types for each of its three components.
     */
    MACDEXT,

    /** Stochastic oscillator (STOCH) values. */
    STOCH,

    /** Stochastic fast (STOCHF) values. */
    STOCHF,

    /** Relative strength index (RSI) values. */
    RSI,

    /** Stochastic relative strength index (STOCHRSI) values. */
    STOCHRSI,

    /** Williams' %R (WILLR) values. */
    WILLR,

    /** Average directional movement index (ADX) values. */
    ADX,

    /** Average directional movement index rating (ADXR) values. */
    ADXR,

    /** Absolute price oscillator (APO) values. */
    APO,

    /** Percentage price oscillator (PPO) values. */
    PPO,

    /** Momentum (MOM) values. */
    MOM,

    /** Balance of power (BOP) values. */
    BOP,

    /** Commodity channel index (CCI) values. */
    CCI,

    /** Chande momentum oscillator (CMO) values. */
    CMO,

    /** Rate of change (ROC) values. */
    ROC,

    /** Rate of change ratio (ROCR) values. */
    ROCR,

    /** Aroon (AROON) values. */
    AROON,

    /** Aroon oscillator (AROONOSC) values. */
    AROONOSC,

    /** Money flow index (MFI) values. */
    MFI,

    /**
     * 1-day rate of change of a triple exponentially smoothed moving average
     * (TRIX) values.
     */
    TRIX,

    /** Ultimate oscillator (ULTOSC) values. */
    ULTOSC,

    /** Directional movement index (DX) values. */
    DX,

    /** Minus directional indicator (MINUS_DI) values. */
    MINUS_DI,

    /** Plus directional indicator (PLUS_DI) values. */
    PLUS_DI,

    /** Minus directional movement (MINUS_DM) values. */
    MINUS_DM,

    /** Plus directional movement (PLUS_DM) values. */
    PLUS_DM,

    /** Bollinger Bands (BBANDS) values. */
    BBANDS,

    /** Midpoint (MIDPOINT) values, the midpoint of a series over a period. */
    MIDPOINT,

    /**
     * Midprice (MIDPRICE) values, the midpoint of a period's high and low.
     */
    MIDPRICE,

    /** Parabolic SAR (SAR) values. */
    SAR,

    /** True range (TRANGE) values. */
    TRANGE,

    /** Average true range (ATR) values. */
    ATR,

    /** Normalized average true range (NATR) values. */
    NATR,

    /** Chaikin A/D line (AD) values. */
    AD,

    /** Chaikin A/D oscillator (ADOSC) values. */
    ADOSC,

    /** On balance volume (OBV) values. */
    OBV,

    /**
     * Hilbert transform, instantaneous trendline (HT_TRENDLINE) values.
     */
    HT_TRENDLINE,

    /** Hilbert transform, sine wave (HT_SINE) values. */
    HT_SINE,

    /** Hilbert transform, trend vs cycle mode (HT_TRENDMODE) values. */
    HT_TRENDMODE,

    /** Hilbert transform, dominant cycle period (HT_DCPERIOD) values. */
    HT_DCPERIOD,

    /** Hilbert transform, dominant cycle phase (HT_DCPHASE) values. */
    HT_DCPHASE,

    /** Hilbert transform, phasor components (HT_PHASOR) values. */
    HT_PHASOR,

    //sector performances

    /**
     * Realtime and historical performance of the S&amp;P 500's constituent
     * sectors across various time ranges.
     */
    SECTOR,

    //Fundamental Data

    /**
     * Company information, financial ratios, and other key metrics for an
     * equity.
     */
    OVERVIEW,

    /** Annual and quarterly income statements for an equity. */
    INCOME_STATEMENT,

    /** Annual and quarterly balance sheets for an equity. */
    BALANCE_SHEET,

    /** Annual and quarterly cash flow statements for an equity. */
    CASH_FLOW,

    /** Annual and quarterly earnings per share (EPS) for an equity. */
    EARNINGS,

    /**
     * List of active or delisted equities and ETFs, as of a given date.
     */
    LISTING_STATUS,

    //Economic Indicators

    /** Annual and quarterly real gross domestic product (GDP) of the United States. */
    REAL_GDP,

    /** Quarterly real GDP per capita of the United States. */
    REAL_GDP_PER_CAPITA,

    /**
     * Daily, weekly, and monthly US Treasury yield for a given maturity.
     */
    TREASURY_YIELD,

    /**
     * Daily, weekly, and monthly federal funds rate (interest rate) of the
     * United States.
     */
    FEDERAL_FUNDS_RATE,

    /** Monthly and semiannual consumer price index (CPI) of the United States. */
    CPI,

    /**
     * Annual inflation rate of the United States, as measured by the
     * consumer price index.
     */
    INFLATION,

    /**
     * Monthly median expected inflation rate over the next 12 months, as
     * measured by the University of Michigan's Surveys of Consumers.
     */
    INFLATION_EXPECTATION,

    /**
     * Monthly consumer sentiment and confidence index of the United States,
     * as measured by the University of Michigan's Surveys of Consumers.
     */
    CONSUMER_SENTIMENT,

    /** Monthly advance retail sales of the United States. */
    RETAIL_SALES,

    /** Monthly manufacturers' new orders for durable goods in the United States. */
    DURABLES,

    /** Monthly unemployment rate of the United States. */
    UNEMPLOYMENT,

    /**
     * Monthly total nonfarm payroll of the United States, a key indicator of
     * overall employment.
     */
    NONFARM_PAYROLL,

}
