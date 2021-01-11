package com.crazzyghost.alphavantage.indicator.response.adx;

import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class ADXResponse extends PeriodicResponse {

    private ADXResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private ADXResponse(String errorMessage) {
        super(errorMessage);
    }

    public static ADXResponse of(Map<String, Object> stringObjectMap){
        Parser<ADXResponse> parser = new ADXParser();
        return parser.parse(stringObjectMap);
    }

    public static class ADXParser extends PeriodicParser<ADXResponse> {

        @Override
        public ADXResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new ADXResponse(indicatorUnits, metaData);
        }

        @Override
        public ADXResponse get(String errorMessage) {
            return new ADXResponse(errorMessage);
        }

        @Override
        public String getIndicatorKey() {
            return "ADX";
        }
    }
}
