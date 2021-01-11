package com.crazzyghost.alphavantage.indicator.response.midpoint;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class MIDPOINTResponse extends PeriodicSeriesResponse {

    private MIDPOINTResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private MIDPOINTResponse(String errorMessage) {
        super(errorMessage);
    }

    public static MIDPOINTResponse of(Map<String, Object> stringObjectMap){
        Parser<MIDPOINTResponse> parser = new MIDPOINTResponseParser();
        return parser.parse(stringObjectMap);
    }

    public static class MIDPOINTResponseParser extends PeriodicSeriesParser<MIDPOINTResponse> {

        @Override
        public MIDPOINTResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new MIDPOINTResponse(indicatorUnits, metaData);
        }

        @Override
        public MIDPOINTResponse get(String errorMessage) {
            return new MIDPOINTResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "MIDPOINT";
        }
    }
}
