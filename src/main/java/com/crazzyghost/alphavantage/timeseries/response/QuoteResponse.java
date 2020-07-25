package com.crazzyghost.alphavantage.timeseries.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.parser.Parser;

public class QuoteResponse {
    private String symbol;
    private double open;
    private double high;
    private double low;
    private double price;
    private double volume;
    private String latestTradingDay;
    private double previousClose;
    private double change;
    private double changePercent;

    private String errorMessage;

    public QuoteResponse(
        String symbol,
        double open,
        double high,
        double low,
        double price,
        double volume,
        String latestTradingDay,
        double previousClose,
        double change,
        double changePercent
    ) {
        this.symbol = symbol;
        this.open = open;
        this.high = high;
        this.low = low;
        this.price = price;
        this.volume = volume;
        this.latestTradingDay = latestTradingDay;
        this.previousClose = previousClose;
        this.change = change;
        this.changePercent = changePercent;
    }


    public String getSymbol() {
        return this.symbol;
    }

    public double getOpen() {
        return this.open;
    }

    public double getHigh() {
        return this.high;
    }

    public double getLow() {
        return this.low;
    }

    public double getPrice() {
        return this.price;
    }

    public double getVolume() {
        return this.volume;
    }

    public String getLatestTradingDay() {
        return this.latestTradingDay;
    }

    public double getPreviousClose() {
        return this.previousClose;
    }

    public double getChange() {
        return this.change;
    }

    public double getChangePercent() {
        return this.changePercent;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }


    public QuoteResponse(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public static QuoteResponse of(Map<String, Object> stringObjectMap){
        Parser<QuoteResponse> parser = new QuoteParser();
        return parser.parse(stringObjectMap);
    }

    public static class QuoteParser extends Parser<QuoteResponse>{

        @SuppressWarnings("unchecked")
        @Override
        public QuoteResponse parse(Map<String, Object> stringObjectMap){
            List<String> keys = new ArrayList<>(stringObjectMap.keySet());
            if (keys.isEmpty()) {
                return onParseError("Empty JSON returned by the API, the symbol might not be supported.");
            } else {

                Map<String, String> data;
                try {
                    data = (Map<String, String>) stringObjectMap.get(keys.get(0));
                } catch (ClassCastException e) {
                    return onParseError((String) stringObjectMap.get(keys.get(0)));
                }

                String changePercentage = data.get("10. change percent");
                changePercentage = changePercentage.substring(0, changePercentage.length() - 1);
                return new QuoteResponse(
                        data.get("01. symbol"),
                        Double.parseDouble(data.get("02. open")),
                        Double.parseDouble(data.get("03. high")),
                        Double.parseDouble(data.get("04. low")),
                        Double.parseDouble(data.get("05. price")),
                        Double.parseDouble(data.get("06. volume")),
                        data.get("07. latest trading day"),
                        Double.parseDouble(data.get("08. previous close")),
                        Double.parseDouble(data.get("09. change")),
                        Double.parseDouble(changePercentage)
                );
            }
        }

        @Override
        public QuoteResponse onParseError(String error) {
            return new QuoteResponse(error);
        }
    }
}
