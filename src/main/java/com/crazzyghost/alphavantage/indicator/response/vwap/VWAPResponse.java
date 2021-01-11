package com.crazzyghost.alphavantage.indicator.response.vwap;

import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class VWAPResponse extends SimpleIndicatorResponse {

    private VWAPResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private VWAPResponse(String errorMessage) {
        super(errorMessage);
    }

    public static SimpleIndicatorResponse of(Map<String, Object> stringObjectMap) {
        Parser<VWAPResponse> parser = new VWAPParser();
        return parser.parse(stringObjectMap);
    }

    public static class VWAPParser extends SimpleIndicatorParser<VWAPResponse>{

        @Override
        public VWAPResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new VWAPResponse(indicatorUnits, metaData);
        }

        @Override
        public VWAPResponse get(String error) {
            return new VWAPResponse(error);
        }

        @Override
        public String getIndicatorKey() {
            return "VWAP";
        }
    }
}
