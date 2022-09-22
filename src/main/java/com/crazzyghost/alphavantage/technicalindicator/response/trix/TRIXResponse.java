package com.crazzyghost.alphavantage.technicalindicator.response.trix;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class TRIXResponse extends PeriodicSeriesResponse {

    private TRIXResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private TRIXResponse(String errorMessage) {
        super(errorMessage);
    }

    public static TRIXResponse of(Map<String, Object> stringObjectMap) {
        Parser<TRIXResponse> parser = new TRIXResponseParser();
        return parser.parse(stringObjectMap);
    }

    public static class TRIXResponseParser extends PeriodicSeriesParser<TRIXResponse> {

        @Override
        public TRIXResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new TRIXResponse(indicatorUnits, metaData);
        }

        @Override
        public TRIXResponse get(String errorMessage) {
            return new TRIXResponse(errorMessage);
        }

        @Override
        protected String getTechnicalIndicatorKey() {
            return "TRIX";
        }
    }
}
