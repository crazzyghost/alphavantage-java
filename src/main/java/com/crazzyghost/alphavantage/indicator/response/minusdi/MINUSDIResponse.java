package com.crazzyghost.alphavantage.indicator.response.minusdi;

import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class MINUSDIResponse extends PeriodicResponse {

    private MINUSDIResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private MINUSDIResponse(String errorMessage) {
        super(errorMessage);
    }

    public static MINUSDIResponse of(Map<String, Object> stringObjectMap){
        Parser<MINUSDIResponse> parser = new MINUSDIParser();
        return parser.parse(stringObjectMap);
    }

    public static class MINUSDIParser extends PeriodicParser<MINUSDIResponse> {

        @Override
        public MINUSDIResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new MINUSDIResponse(indicatorUnits, metaData);
        }

        @Override
        public MINUSDIResponse get(String errorMessage) {
            return new MINUSDIResponse(errorMessage);
        }

        @Override
        public String getIndicatorKey() {
            return "MINUS_DI";
        }
    }
}
