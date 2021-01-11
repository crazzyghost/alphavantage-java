package com.crazzyghost.alphavantage.indicator.response.trix;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class TRIXResponse extends PeriodicSeriesResponse {

    private TRIXResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private TRIXResponse(String errorMessage) {
        super(errorMessage);
    }

    public static TRIXResponse of(Map<String, Object> stringObjectMap){
        Parser<TRIXResponse> parser = new TRIXResponseParser();
        return parser.parse(stringObjectMap);
    }

    public static class TRIXResponseParser extends PeriodicSeriesParser<TRIXResponse> {

        @Override
        public TRIXResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new TRIXResponse(indicatorUnits, metaData);
        }

        @Override
        public TRIXResponse get(String errorMessage) {
            return new TRIXResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "TRIX";
        }
    }
}
