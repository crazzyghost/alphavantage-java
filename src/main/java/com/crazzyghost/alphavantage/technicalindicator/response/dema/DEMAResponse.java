package com.crazzyghost.alphavantage.technicalindicator.response.dema;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class DEMAResponse extends PeriodicSeriesResponse {

    private DEMAResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private DEMAResponse(String errorMessage) {
        super(errorMessage);
    }

    public static DEMAResponse of(Map<String, Object> stringObjectMap) {
        Parser<DEMAResponse> parser = new DMAResponseParser();
        return parser.parse(stringObjectMap);
    }

    public static class DMAResponseParser extends PeriodicSeriesParser<DEMAResponse> {

        @Override
        public DEMAResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new DEMAResponse(indicatorUnits, metaData);
        }

        @Override
        public DEMAResponse get(String errorMessage) {
            return new DEMAResponse(errorMessage);
        }

        @Override
        protected String getTechnicalIndicatorKey() {
            return "DEMA";
        }
    }
}
