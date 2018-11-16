package com.crazzyghost.alphavantage;

import com.crazzyghost.alphavantage.exchangerate.ExchangeRate;
import com.crazzyghost.alphavantage.forex.Forex;
import com.crazzyghost.alphavantage.timeseries.TimeSeries;

public class AlphaVantage {

    private static AlphaVantage INSTANCE;
    private Config config;
    private TimeSeries timeSeries;
    private Forex forex;
    private ExchangeRate exchangeRate;

    private AlphaVantage(Config config){
         this.config = config;
    }

    public static void init(Config config){
        INSTANCE = new AlphaVantage(config);
    }

    public static AlphaVantage api(){
        if(INSTANCE == null){
            throw new AlphaVantageException("Call AlphaVantage.init");
        }
        return INSTANCE;
    }
    
    public TimeSeries timeSeries(){
        if(timeSeries == null){
            timeSeries = new TimeSeries(config);
        }
        return timeSeries;
    }

    public Forex forex(){
        if(forex == null){
            forex = new Forex(config);
        }
        return forex;
    }


    public ExchangeRate exchangeRate() {
        if(exchangeRate == null){
            exchangeRate = new ExchangeRate(config);
        }
        return exchangeRate;
    }
}
