package com.crazzyghost.alphavantage.indicator.response.plusdm;

import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class PLUSDMResponse extends PeriodicResponse {

    private PLUSDMResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private PLUSDMResponse(String errorMessage) {
        super(errorMessage);
    }

    public static PLUSDMResponse of(Map<String, Object> stringObjectMap){
        Parser<PLUSDMResponse> parser = new PLUSDMParser();
        return parser.parse(stringObjectMap);
    }

    public static class PLUSDMParser extends PeriodicParser<PLUSDMResponse> {

        @Override
        public PLUSDMResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new PLUSDMResponse(indicatorUnits, metaData);
        }

        @Override
        public PLUSDMResponse get(String errorMessage) {
            return new PLUSDMResponse(errorMessage);
        }

        @Override
        public String getIndicatorKey() {
            return "PLUS_DM";
        }
    }
}
