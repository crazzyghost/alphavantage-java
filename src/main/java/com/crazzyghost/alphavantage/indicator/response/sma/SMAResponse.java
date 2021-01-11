package com.crazzyghost.alphavantage.indicator.response.sma;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class SMAResponse extends PeriodicSeriesResponse {

    private SMAResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private SMAResponse(String errorMessage) {
        super(errorMessage);
    }

    public static SMAResponse of(Map<String, Object> stringObjectMap){
        Parser<SMAResponse> parser = new SMAResponseParser();
        return parser.parse(stringObjectMap);
    }

    public static class SMAResponseParser extends PeriodicSeriesParser<SMAResponse> {

        @Override
        public SMAResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new SMAResponse(indicatorUnits, metaData);
        }

        @Override
        public SMAResponse get(String errorMessage) {
            return new SMAResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "SMA";
        }
    }
}
