package com.crazzyghost.alphavantage.indicator.response.rsi;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class RSIResponse extends PeriodicSeriesResponse {

    private RSIResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private RSIResponse(String errorMessage) {
        super(errorMessage);
    }

    public static RSIResponse of(Map<String, Object> stringObjectMap){
        Parser<RSIResponse> parser = new RSIResponseParser();
        return parser.parse(stringObjectMap);
    }

    public static class RSIResponseParser extends PeriodicSeriesParser<RSIResponse> {

        @Override
        public RSIResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new RSIResponse(indicatorUnits, metaData);
        }

        @Override
        public RSIResponse get(String errorMessage) {
            return new RSIResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "RSI";
        }
    }
}
