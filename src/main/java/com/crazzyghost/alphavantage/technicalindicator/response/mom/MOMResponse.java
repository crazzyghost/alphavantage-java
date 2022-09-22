package com.crazzyghost.alphavantage.technicalindicator.response.mom;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class MOMResponse extends PeriodicSeriesResponse {

    private MOMResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private MOMResponse(String errorMessage) {
        super(errorMessage);
    }

    public static MOMResponse of(Map<String, Object> stringObjectMap) {
        Parser<MOMResponse> parser = new MOMResponseParser();
        return parser.parse(stringObjectMap);
    }

    public static class MOMResponseParser extends PeriodicSeriesParser<MOMResponse> {

        @Override
        public MOMResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new MOMResponse(indicatorUnits, metaData);
        }

        @Override
        public MOMResponse get(String errorMessage) {
            return new MOMResponse(errorMessage);
        }

        @Override
        protected String getTechnicalIndicatorKey() {
            return "MOM";
        }
    }
}
