package com.crazzyghost.alphavantage.technicalindicator.response.plusdi;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class PLUSDIResponse extends PeriodicResponse {

    private PLUSDIResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private PLUSDIResponse(String errorMessage) {
        super(errorMessage);
    }

    public static PLUSDIResponse of(Map<String, Object> stringObjectMap) {
        Parser<PLUSDIResponse> parser = new PLUSDIParser();
        return parser.parse(stringObjectMap);
    }

    public static class PLUSDIParser extends PeriodicParser<PLUSDIResponse> {

        @Override
        public PLUSDIResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new PLUSDIResponse(indicatorUnits, metaData);
        }

        @Override
        public PLUSDIResponse get(String errorMessage) {
            return new PLUSDIResponse(errorMessage);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "PLUS_DI";
        }
    }
}
