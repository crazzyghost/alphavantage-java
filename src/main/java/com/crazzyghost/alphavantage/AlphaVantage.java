package com.crazzyghost.alphavantage;

import com.crazzyghost.alphavantage.cryptocurrency.Crypto;
import com.crazzyghost.alphavantage.exchangerate.ExchangeRate;
import com.crazzyghost.alphavantage.forex.Forex;
import com.crazzyghost.alphavantage.indicator.Indicator;
import com.crazzyghost.alphavantage.sector.Sector;
import com.crazzyghost.alphavantage.timeseries.TimeSeries;

/**
 * Client interface of library. The API is accessed through this class. Exposes a singleton instance for interaction
 * @since 1.0.0
 * @author crazzyghost
 */
public class AlphaVantage {

    private static AlphaVantage INSTANCE;
    private Config config;

    private AlphaVantage(){
    }

    /**
     * Initialize the client with a {@link Config} instance
     * @param config
     */
    public void init(Config config){
        this.config = config;
    }

    /**
     * Access the client interface
     * @return Singleton instance of {@link AlphaVantage}
     */
    public static AlphaVantage api(){
        if(INSTANCE == null){
            INSTANCE = new AlphaVantage();
        }
        return INSTANCE;
    }
    
    /**
     * Access to Time Series Data. All requests associated with Stock Time Series is accessed through this method. 
     * @return A {@link TimeSeries} instance for access to Time Series Data
     */
    public TimeSeries timeSeries(){
        return new TimeSeries(config);
    }

    /**
     * Access to Foreign Exchange Data. All requests associated with Foreing Exchange (FX) is accessed through this method. 
     * @return A {@link Forex} instance for access to FX data
     */
    public Forex forex(){
        return new Forex(config);
    }


    /**
     * Access to Digital/Physical Exchange Rates.
     * @return An {@link ExchangeRate} instance for access to Exchange Rate Data
     */
    public ExchangeRate exchangeRate() {
        return new ExchangeRate(config);
    }


    /**
     * Access to Digital Currencies.
     * @return A {@link Crypto} instance for access to Digital Currency Data
     */
    public Crypto crypto(){
        return new Crypto(config);
    }

    /**
     * Access to Technical Indicators.
     * @return A {@link Indicator} instance for access to Technical Indicator Data
     */
    public Indicator indicator(){
        return new Indicator(config);
    }

    /**
     * Access to Sector Performances.
     * @return A {@link Sector} instance for access to Sector Performance Data
     */
    public Sector sector(){
        return new Sector(config);
    }
}
