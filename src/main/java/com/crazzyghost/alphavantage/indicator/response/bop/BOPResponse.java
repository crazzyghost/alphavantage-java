package com.crazzyghost.alphavantage.indicator.response.bop;

import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class BOPResponse extends SimpleIndicatorResponse {

    private BOPResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private BOPResponse(String errorMessage) {
        super(errorMessage);
    }

    public static SimpleIndicatorResponse of(Map<String, Object> stringObjectMap) {
        Parser<BOPResponse> parser = new BOPParser();
        return parser.parse(stringObjectMap);
    }

    public static class BOPParser extends SimpleIndicatorParser<BOPResponse>{

        @Override
        public BOPResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new BOPResponse(indicatorUnits, metaData);
        }

        @Override
        public BOPResponse get(String error) {
            return new BOPResponse(error);
        }

        @Override
        public String getIndicatorKey() {
            return "BOP";
        }
    }
}
