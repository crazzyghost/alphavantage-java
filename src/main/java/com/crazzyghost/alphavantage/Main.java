package com.crazzyghost.alphavantage;


import com.crazzyghost.alphavantage.exchangerate.ExchangeRateResponse;
import com.crazzyghost.alphavantage.forex.response.ForexResponse;
import com.crazzyghost.alphavantage.forex.response.ForexUnit;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Interval;

import java.util.List;

public class Main {



    public static void main(String[] args){


        Config config = Config.builder()
                            .key("M77PQNYAVBG0MB5N")
                            .timeOut(5)
                            .build();

        AlphaVantage.init(config);


//        AlphaVantage.api()
//                .timeSeries()
//                .intraday()
//                .onFailure(System.out::println)
//                .interval(Interval.ONE_MIN)
//                .onSuccess(System.out::println)
//                .forSymbol("MSFT")
//                .fetch();

//        AlphaVantage.api()
//                .forex()
//                .daily()
//                .fromSymbol("USD")
//                .toSymbol("GHS")
//                .onSuccess(response -> handleSucess(response.getForexUnits()))
//                .fetch();
        AlphaVantage.api()
                .exchangeRate()
                .fromCurrency("USD")
                .toCurrency("GHS")
                .onSuccess(response -> handleSucess(response))
                .fetch();


    }

    static void handleSucess(ExchangeRateResponse e){
        System.out.println(e);
    }

}
