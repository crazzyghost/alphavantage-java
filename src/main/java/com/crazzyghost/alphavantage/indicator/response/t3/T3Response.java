package com.crazzyghost.alphavantage.indicator.response.t3;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class T3Response extends PeriodicSeriesResponse {

    private T3Response(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private T3Response(String errorMessage) {
        super(errorMessage);
    }

    public static T3Response of(Map<String, Object> stringObjectMap){
        Parser<T3Response> parser = new T3ResponseParser();
        return parser.parse(stringObjectMap);
    }

    public static class T3ResponseParser extends PeriodicSeriesParser<T3Response> {

        @Override
        public T3Response get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new T3Response(indicatorUnits, metaData);
        }

        @Override
        public T3Response get(String errorMessage) {
            return new T3Response(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "T3";
        }
    }
}
