package com.crazzyghost.alphavantage.technicalindicator.response.wma;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class WMAResponse extends PeriodicSeriesResponse {

    private WMAResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private WMAResponse(String errorMessage) {
        super(errorMessage);
    }

    public static WMAResponse of(Map<String, Object> stringObjectMap) {
        Parser<WMAResponse> parser = new WMAResponseParser();
        return parser.parse(stringObjectMap);
    }

    public static class WMAResponseParser extends PeriodicSeriesParser<WMAResponse> {

        @Override
        public WMAResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new WMAResponse(indicatorUnits, metaData);
        }

        @Override
        public WMAResponse get(String errorMessage) {
            return new WMAResponse(errorMessage);
        }

        @Override
        protected String getTechnicalIndicatorKey() {
            return "WMA";
        }
    }
}
