package com.crazzyghost.alphavantage.technicalindicator.response.obv;

import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class OBVResponse extends SimpleTechnicalIndicatorResponse {

    private OBVResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private OBVResponse(String errorMessage) {
        super(errorMessage);
    }

    public static SimpleTechnicalIndicatorResponse of(Map<String, Object> stringObjectMap) {
        Parser<OBVResponse> parser = new OBVParser();
        return parser.parse(stringObjectMap);
    }

    public static class OBVParser extends SimpleTechnicalIndicatorParser<OBVResponse> {

        @Override
        public OBVResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new OBVResponse(indicatorUnits, metaData);
        }

        @Override
        public OBVResponse get(String error) {
            return new OBVResponse(error);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "OBV";
        }
    }
}
