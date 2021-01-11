package com.crazzyghost.alphavantage.indicator.response.ad;

import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class ADResponse extends SimpleIndicatorResponse {

    private ADResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private ADResponse(String errorMessage) {
        super(errorMessage);
    }

    public static SimpleIndicatorResponse of(Map<String, Object> stringObjectMap) {
        Parser<ADResponse> parser = new ADParser();
        return parser.parse(stringObjectMap);
    }

    public static class ADParser extends SimpleIndicatorParser<ADResponse>{

        @Override
        public ADResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new ADResponse(indicatorUnits, metaData);
        }

        @Override
        public ADResponse get(String error) {
            return new ADResponse(error);
        }

        @Override
        public String getIndicatorKey() {
            return "Chaikin A/D";
        }
    }
}
