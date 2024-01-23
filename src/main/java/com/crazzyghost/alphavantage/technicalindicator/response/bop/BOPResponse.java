package com.crazzyghost.alphavantage.technicalindicator.response.bop;

import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class BOPResponse extends SimpleTechnicalIndicatorResponse {

    private BOPResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private BOPResponse(String errorMessage) {
        super(errorMessage);
    }

    public static SimpleTechnicalIndicatorResponse of(Map<String, Object> stringObjectMap) {
        Parser<BOPResponse> parser = new BOPParser();
        return parser.parse(stringObjectMap);
    }

    public static class BOPParser extends SimpleTechnicalIndicatorParser<BOPResponse> {

        @Override
        public BOPResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new BOPResponse(indicatorUnits, metaData);
        }

        @Override
        public BOPResponse get(String error) {
            return new BOPResponse(error);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "BOP";
        }
    }
}
