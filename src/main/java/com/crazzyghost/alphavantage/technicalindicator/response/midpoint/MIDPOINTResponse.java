package com.crazzyghost.alphavantage.technicalindicator.response.midpoint;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class MIDPOINTResponse extends PeriodicSeriesResponse {

    private MIDPOINTResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private MIDPOINTResponse(String errorMessage) {
        super(errorMessage);
    }

    public static MIDPOINTResponse of(Map<String, Object> stringObjectMap) {
        Parser<MIDPOINTResponse> parser = new MIDPOINTResponseParser();
        return parser.parse(stringObjectMap);
    }

    public static class MIDPOINTResponseParser extends PeriodicSeriesParser<MIDPOINTResponse> {

        @Override
        public MIDPOINTResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new MIDPOINTResponse(indicatorUnits, metaData);
        }

        @Override
        public MIDPOINTResponse get(String errorMessage) {
            return new MIDPOINTResponse(errorMessage);
        }

        @Override
        protected String getTechnicalIndicatorKey() {
            return "MIDPOINT";
        }
    }
}
