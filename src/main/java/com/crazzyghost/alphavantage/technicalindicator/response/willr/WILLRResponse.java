package com.crazzyghost.alphavantage.technicalindicator.response.willr;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class WILLRResponse extends PeriodicResponse {

    private WILLRResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private WILLRResponse(String errorMessage) {
        super(errorMessage);
    }

    public static WILLRResponse of(Map<String, Object> stringObjectMap) {
        Parser<WILLRResponse> parser = new WILLRParser();
        return parser.parse(stringObjectMap);
    }

    public static class WILLRParser extends PeriodicParser<WILLRResponse> {

        @Override
        public WILLRResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new WILLRResponse(indicatorUnits, metaData);
        }

        @Override
        public WILLRResponse get(String errorMessage) {
            return new WILLRResponse(errorMessage);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "WILLR";
        }
    }
}
