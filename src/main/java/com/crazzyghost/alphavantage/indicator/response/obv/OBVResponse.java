package com.crazzyghost.alphavantage.indicator.response.obv;

import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class OBVResponse extends SimpleIndicatorResponse {

    private OBVResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private OBVResponse(String errorMessage) {
        super(errorMessage);
    }

    public static SimpleIndicatorResponse of(Map<String, Object> stringObjectMap) {
        Parser<OBVResponse> parser = new OBVParser();
        return parser.parse(stringObjectMap);
    }

    public static class OBVParser extends SimpleIndicatorParser<OBVResponse>{

        @Override
        public OBVResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new OBVResponse(indicatorUnits, metaData);
        }

        @Override
        public OBVResponse get(String error) {
            return new OBVResponse(error);
        }

        @Override
        public String getIndicatorKey() {
            return "OBV";
        }
    }
}
