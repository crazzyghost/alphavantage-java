package com.crazzyghost.alphavantage.indicator.response.tema;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class TEMAResponse extends PeriodicSeriesResponse {

    private TEMAResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private TEMAResponse(String errorMessage) {
        super(errorMessage);
    }

    public static TEMAResponse of(Map<String, Object> stringObjectMap){
        Parser<TEMAResponse> parser = new TEMAResponseParser();
        return parser.parse(stringObjectMap);
    }

    public static class TEMAResponseParser extends PeriodicSeriesParser<TEMAResponse> {

        @Override
        public TEMAResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new TEMAResponse(indicatorUnits, metaData);
        }

        @Override
        public TEMAResponse get(String errorMessage) {
            return new TEMAResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "TEMA";
        }
    }
}
