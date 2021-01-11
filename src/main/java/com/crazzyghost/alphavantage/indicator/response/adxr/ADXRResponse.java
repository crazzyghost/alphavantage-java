package com.crazzyghost.alphavantage.indicator.response.adxr;

import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class ADXRResponse extends PeriodicResponse {

    private ADXRResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private ADXRResponse(String errorMessage) {
        super(errorMessage);
    }

    public static ADXRResponse of(Map<String, Object> stringObjectMap){
        Parser<ADXRResponse> parser = new ADXRParser();
        return parser.parse(stringObjectMap);
    }

    public static class ADXRParser extends PeriodicParser<ADXRResponse> {

        @Override
        public ADXRResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new ADXRResponse(indicatorUnits, metaData);
        }

        @Override
        public ADXRResponse get(String errorMessage) {
            return new ADXRResponse(errorMessage);
        }

        @Override
        public String getIndicatorKey() {
            return "ADXR";
        }
    }
}
