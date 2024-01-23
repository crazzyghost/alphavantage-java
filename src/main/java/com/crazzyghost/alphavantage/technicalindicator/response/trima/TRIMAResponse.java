package com.crazzyghost.alphavantage.technicalindicator.response.trima;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class TRIMAResponse extends PeriodicSeriesResponse {

    private TRIMAResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private TRIMAResponse(String errorMessage) {
        super(errorMessage);
    }

    public static TRIMAResponse of(Map<String, Object> stringObjectMap) {
        Parser<TRIMAResponse> parser = new TRIMAResponseParser();
        return parser.parse(stringObjectMap);
    }

    public static class TRIMAResponseParser extends PeriodicSeriesParser<TRIMAResponse> {

        @Override
        public TRIMAResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new TRIMAResponse(indicatorUnits, metaData);
        }

        @Override
        public TRIMAResponse get(String errorMessage) {
            return new TRIMAResponse(errorMessage);
        }

        @Override
        protected String getTechnicalIndicatorKey() {
            return "TRIMA";
        }
    }
}
