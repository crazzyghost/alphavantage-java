package com.crazzyghost.alphavantage;


import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Interval;

public class Main {



    public static void main(String[] args){


        Config config = Config.builder()
                            .key("M77PQNYAVBG0MB5N")
                            .timeOut(5)
                            .build();

        AlphaVantage.init(config);


        AlphaVantage.api()
                .timeSeries()
                .monthly()
                .onFailure(System.out::println)
                .adjusted()
                .onSuccess(System.out::println)
                .forSymbol("AAPL")
                .fetch();

    }


}
