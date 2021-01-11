package com.crazzyghost.alphavantage.indicator.response.kama;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class KAMAResponse extends PeriodicSeriesResponse {

    private KAMAResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private KAMAResponse(String errorMessage) {
        super(errorMessage);
    }

    public static KAMAResponse of(Map<String, Object> stringObjectMap){
        Parser<KAMAResponse> parser = new KAMAResponseParser();
        return parser.parse(stringObjectMap);
    }

    public static class KAMAResponseParser extends PeriodicSeriesParser<KAMAResponse> {

        @Override
        public KAMAResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new KAMAResponse(indicatorUnits, metaData);
        }

        @Override
        public KAMAResponse get(String errorMessage) {
            return new KAMAResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "KAMA";
        }
    }
}
