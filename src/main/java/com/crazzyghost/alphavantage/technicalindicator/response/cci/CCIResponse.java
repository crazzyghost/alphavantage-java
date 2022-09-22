package com.crazzyghost.alphavantage.technicalindicator.response.cci;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class CCIResponse extends PeriodicResponse {

    private CCIResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private CCIResponse(String errorMessage) {
        super(errorMessage);
    }

    public static CCIResponse of(Map<String, Object> stringObjectMap) {
        Parser<CCIResponse> parser = new CCIParser();
        return parser.parse(stringObjectMap);
    }

    public static class CCIParser extends PeriodicParser<CCIResponse> {

        @Override
        public CCIResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new CCIResponse(indicatorUnits, metaData);
        }

        @Override
        public CCIResponse get(String errorMessage) {
            return new CCIResponse(errorMessage);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "CCI";
        }
    }
}
