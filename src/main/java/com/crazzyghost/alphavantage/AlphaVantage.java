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
package com.crazzyghost.alphavantage;

import com.crazzyghost.alphavantage.cryptocurrency.Crypto;
import com.crazzyghost.alphavantage.economicindicator.EconomicIndicator;
import com.crazzyghost.alphavantage.exchangerate.ExchangeRate;
import com.crazzyghost.alphavantage.forex.Forex;
import com.crazzyghost.alphavantage.fundamentaldata.FundamentalData;
import com.crazzyghost.alphavantage.indicator.Indicator;
import com.crazzyghost.alphavantage.marketstatus.MarketStatus;
import com.crazzyghost.alphavantage.sector.Sector;
import com.crazzyghost.alphavantage.technicalindicator.TechnicalIndicator;
import com.crazzyghost.alphavantage.timeseries.TimeSeries;

/**
 * Client interface of library.
 * The API is accessed through this class.
 * Exposes a singleton instance for interaction
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public class AlphaVantage {

    private static AlphaVantage INSTANCE;
    private Config config;

    private AlphaVantage() {
    }

    /**
     * Initialize the client with a {@link Config} instance
     */
    public void init(Config config) {
        this.config = config;
    }

    /**
     * Access the client interface
     *
     * @return Singleton instance of {@link AlphaVantage}
     */
    public static AlphaVantage api() {
        if (INSTANCE == null) {
            INSTANCE = new AlphaVantage();
        }
        return INSTANCE;
    }

    /**
     * Access to Time Series Data. All requests associated with Stock Time Series is accessed through this method.
     *
     * @return A {@link TimeSeries} instance for access to Time Series Data
     */
    public TimeSeries timeSeries() {
        return new TimeSeries(config);
    }

    /**
     * Access to Foreign Exchange Data. All requests associated with Foreign Exchange (FX) is accessed through this method.
     *
     * @return A {@link Forex} instance for access to FX data
     */
    public Forex forex() {
        return new Forex(config);
    }


    /**
     * Access to Digital/Physical Exchange Rates.
     *
     * @return An {@link ExchangeRate} instance for access to Exchange Rate Data
     */
    public ExchangeRate exchangeRate() {
        return new ExchangeRate(config);
    }


    /**
     * Access to Digital Currencies.
     *
     * @return A {@link Crypto} instance for access to Digital Currency Data
     */
    public Crypto crypto() {
        return new Crypto(config);
    }

    /**
     * @return A {@link Indicator} instance for access to Technical Indicator Data
     * @deprecated <p>use {@link AlphaVantage#technicalIndicator()} instead </p>
     * <p>
     * Access to Technical Indicators.
     */
    @Deprecated
    public Indicator indicator() {
        return new Indicator(config);
    }

    /**
     * Access to Technical Indicators.
     *
     * @return A {@link TechnicalIndicator} instance for access to Technical
     * Indicator Data
     */
    public TechnicalIndicator technicalIndicator() {
        return new TechnicalIndicator(config);
    }

    /**
     * Access to Sector Performances.
     *
     * @return A {@link Sector} instance for access to Sector Performance Data
     */
    public Sector sector() {
        return new Sector(config);
    }

    /**
     * Access to Fundamental Data.
     *
     * @return A {@link FundamentalData} instance for access to Fundamental Data
     */
    public FundamentalData fundamentalData() {
        return new FundamentalData(config);
    }

    /**
     * Access to Economic Indicators.
     *
     * @return A {@link EconomicIndicator} instance for access to Economic Indicators
     */
    public EconomicIndicator economicIndicator() {
        return new EconomicIndicator(config);
    }

    /**
     * Access to Global Market Status.
     *
     * @return A {@link MarketStatus} instance for access to Global Market Status
     */
    public MarketStatus marketStatus() {
        return new MarketStatus(config);
    }


}
