package com.crazzyghost.alphavantage.technicalindicator.response.sma;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class SMAResponse extends PeriodicSeriesResponse {

    private SMAResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private SMAResponse(String errorMessage) {
        super(errorMessage);
    }

    public static SMAResponse of(Map<String, Object> stringObjectMap) {
        Parser<SMAResponse> parser = new SMAResponseParser();
        return parser.parse(stringObjectMap);
    }

    public static class SMAResponseParser extends PeriodicSeriesParser<SMAResponse> {

        @Override
        public SMAResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new SMAResponse(indicatorUnits, metaData);
        }

        @Override
        public SMAResponse get(String errorMessage) {
            return new SMAResponse(errorMessage);
        }

        @Override
        protected String getTechnicalIndicatorKey() {
            return "SMA";
        }
    }
}
