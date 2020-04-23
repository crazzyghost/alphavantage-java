package com.crazzyghost.alphavantage;

import com.crazzyghost.alphavantage.cryptocurrency.Crypto;
import com.crazzyghost.alphavantage.exchangerate.ExchangeRate;
import com.crazzyghost.alphavantage.forex.Forex;
import com.crazzyghost.alphavantage.indicator.Indicator;
import com.crazzyghost.alphavantage.timeseries.TimeSeries;

public class AlphaVantage {

    private static AlphaVantage INSTANCE;
    private Config config;
    private TimeSeries timeSeries;
    private Forex forex;
    private ExchangeRate exchangeRate;
    private Crypto crypto;
    private Indicator indicator;

    private AlphaVantage(){

    }

    public void init(Config config){
        this.config = config;
    }

    public static AlphaVantage api(){
        if(INSTANCE == null){
            INSTANCE = new AlphaVantage();
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


    public Crypto crypto(){
        if(crypto == null){
            crypto = new Crypto(config);
        }
        return crypto;
    }

    public Indicator indicator(){
        if(indicator == null){
            indicator = new Indicator(config);
        }
        return indicator;
    }
}
