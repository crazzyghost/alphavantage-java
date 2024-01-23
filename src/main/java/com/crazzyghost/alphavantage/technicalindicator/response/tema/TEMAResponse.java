package com.crazzyghost.alphavantage.technicalindicator.response.tema;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class TEMAResponse extends PeriodicSeriesResponse {

    private TEMAResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private TEMAResponse(String errorMessage) {
        super(errorMessage);
    }

    public static TEMAResponse of(Map<String, Object> stringObjectMap) {
        Parser<TEMAResponse> parser = new TEMAResponseParser();
        return parser.parse(stringObjectMap);
    }

    public static class TEMAResponseParser extends PeriodicSeriesParser<TEMAResponse> {

        @Override
        public TEMAResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new TEMAResponse(indicatorUnits, metaData);
        }

        @Override
        public TEMAResponse get(String errorMessage) {
            return new TEMAResponse(errorMessage);
        }

        @Override
        protected String getTechnicalIndicatorKey() {
            return "TEMA";
        }
    }
}
