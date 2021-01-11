package com.crazzyghost.alphavantage.indicator.response.minusdm;

import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class MINUSDMResponse extends PeriodicResponse {

    private MINUSDMResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private MINUSDMResponse(String errorMessage) {
        super(errorMessage);
    }

    public static MINUSDMResponse of(Map<String, Object> stringObjectMap){
        Parser<MINUSDMResponse> parser = new MINUSDMParser();
        return parser.parse(stringObjectMap);
    }

    public static class MINUSDMParser extends PeriodicParser<MINUSDMResponse> {

        @Override
        public MINUSDMResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new MINUSDMResponse(indicatorUnits, metaData);
        }

        @Override
        public MINUSDMResponse get(String errorMessage) {
            return new MINUSDMResponse(errorMessage);
        }

        @Override
        public String getIndicatorKey() {
            return "MINUS_DM";
        }
    }
}
