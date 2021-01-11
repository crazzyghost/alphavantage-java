package com.crazzyghost.alphavantage.indicator.response.trima;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class TRIMAResponse extends PeriodicSeriesResponse {

    private TRIMAResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private TRIMAResponse(String errorMessage) {
        super(errorMessage);
    }

    public static TRIMAResponse of(Map<String, Object> stringObjectMap){
        Parser<TRIMAResponse> parser = new TRIMAResponseParser();
        return parser.parse(stringObjectMap);
    }

    public static class TRIMAResponseParser extends PeriodicSeriesParser<TRIMAResponse> {

        @Override
        public TRIMAResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new TRIMAResponse(indicatorUnits, metaData);
        }

        @Override
        public TRIMAResponse get(String errorMessage) {
            return new TRIMAResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "TRIMA";
        }
    }
}
