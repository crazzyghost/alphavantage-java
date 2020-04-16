package com.crazzyghost.alphavantage.exchangerate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExchangeRateResponse {

    private String fromCurrencyCode;
    private String fromCurrencyName;
    private String toCurrencyCode;
    private String toCurrencyName;
    private Double exchangeRate;
    private String lastRefreshed;
    private String timeZone;

    private String errorMessage;

    private ExchangeRateResponse(
            String fromCurrencyCode,
            String fromCurrencyName,
            String toCurrencyCode,
            String toCurrencyName,
            Double exchangeRate,
            String lastRefreshed,
            String timeZone) {
        this.fromCurrencyCode = fromCurrencyCode;
        this.fromCurrencyName = fromCurrencyName;
        this.toCurrencyCode = toCurrencyCode;
        this.toCurrencyName = toCurrencyName;
        this.exchangeRate = exchangeRate;
        this.lastRefreshed = lastRefreshed;
        this.timeZone = timeZone;
        this.errorMessage = null;
    }

    private ExchangeRateResponse(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public static ExchangeRateResponse of(Map<String, Object> stringObjectMap){
        Parser parser = new Parser();
        return parser.parse(stringObjectMap);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static class Parser {


        ExchangeRateResponse parse(Map<String, Object> stringObjectMap) {

            //get the keys
            List<String> keys = new ArrayList<>(stringObjectMap.keySet());

            Map<String, String> md;

            try{
                md = (Map<String, String>) stringObjectMap.get(keys.get(0));

            }catch (ClassCastException e){
                return new ExchangeRateResponse((String)stringObjectMap.get(keys.get(0)));
            }


            return new ExchangeRateResponse(
                    md.get("1. From_Currency Code"),
                    md.get("2. From_Currency Name"),
                    md.get("3. To_Currency Code"),
                    md.get("4. To_Currency Name"),
                    Double.parseDouble(md.get("5. Exchange Rate")),
                    md.get("6. Last Refreshed"),
                    md.get("7. Time Zone")
            );

        }
    }

    @Override
    public String toString() {
        return "ExchangeRateResponse{" +
                "fromCurrencyCode='" + fromCurrencyCode + '\'' +
                ", fromCurrencyName='" + fromCurrencyName + '\'' +
                ", toCurrencyCode='" + toCurrencyCode + '\'' +
                ", toCurrencyName='" + toCurrencyName + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", lastRefreshed='" + lastRefreshed + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
