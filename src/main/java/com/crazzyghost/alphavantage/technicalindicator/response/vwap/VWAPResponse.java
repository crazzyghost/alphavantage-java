package com.crazzyghost.alphavantage.technicalindicator.response.vwap;

import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class VWAPResponse extends SimpleTechnicalIndicatorResponse {

    private VWAPResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private VWAPResponse(String errorMessage) {
        super(errorMessage);
    }

    public static SimpleTechnicalIndicatorResponse of(Map<String, Object> stringObjectMap) {
        Parser<VWAPResponse> parser = new VWAPParser();
        return parser.parse(stringObjectMap);
    }

    public static class VWAPParser extends SimpleTechnicalIndicatorParser<VWAPResponse> {

        @Override
        public VWAPResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new VWAPResponse(indicatorUnits, metaData);
        }

        @Override
        public VWAPResponse get(String error) {
            return new VWAPResponse(error);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "VWAP";
        }
    }
}
