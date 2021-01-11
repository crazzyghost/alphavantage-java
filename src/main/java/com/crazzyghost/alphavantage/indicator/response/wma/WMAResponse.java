package com.crazzyghost.alphavantage.indicator.response.wma;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class WMAResponse extends PeriodicSeriesResponse {

    private WMAResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private WMAResponse(String errorMessage) {
        super(errorMessage);
    }

    public static WMAResponse of(Map<String, Object> stringObjectMap){
        Parser<WMAResponse> parser = new WMAResponseParser();
        return parser.parse(stringObjectMap);
    }

    public static class WMAResponseParser extends PeriodicSeriesParser<WMAResponse> {

        @Override
        public WMAResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new WMAResponse(indicatorUnits, metaData);
        }

        @Override
        public WMAResponse get(String errorMessage) {
            return new WMAResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "WMA";
        }
    }
}
