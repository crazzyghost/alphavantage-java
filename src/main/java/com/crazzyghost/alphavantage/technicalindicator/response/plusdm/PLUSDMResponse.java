package com.crazzyghost.alphavantage.technicalindicator.response.plusdm;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class PLUSDMResponse extends PeriodicResponse {

    private PLUSDMResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private PLUSDMResponse(String errorMessage) {
        super(errorMessage);
    }

    public static PLUSDMResponse of(Map<String, Object> stringObjectMap) {
        Parser<PLUSDMResponse> parser = new PLUSDMParser();
        return parser.parse(stringObjectMap);
    }

    public static class PLUSDMParser extends PeriodicParser<PLUSDMResponse> {

        @Override
        public PLUSDMResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new PLUSDMResponse(indicatorUnits, metaData);
        }

        @Override
        public PLUSDMResponse get(String errorMessage) {
            return new PLUSDMResponse(errorMessage);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "PLUS_DM";
        }
    }
}
