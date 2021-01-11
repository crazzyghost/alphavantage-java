package com.crazzyghost.alphavantage.indicator.response.mom;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class MOMResponse extends PeriodicSeriesResponse {

    private MOMResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private MOMResponse(String errorMessage) {
        super(errorMessage);
    }

    public static MOMResponse of(Map<String, Object> stringObjectMap){
        Parser<MOMResponse> parser = new MOMResponseParser();
        return parser.parse(stringObjectMap);
    }

    public static class MOMResponseParser extends PeriodicSeriesParser<MOMResponse> {

        @Override
        public MOMResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new MOMResponse(indicatorUnits, metaData);
        }

        @Override
        public MOMResponse get(String errorMessage) {
            return new MOMResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "MOM";
        }
    }
}
