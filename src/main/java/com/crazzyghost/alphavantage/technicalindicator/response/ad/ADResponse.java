package com.crazzyghost.alphavantage.technicalindicator.response.ad;

import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class ADResponse extends SimpleTechnicalIndicatorResponse {

    private ADResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private ADResponse(String errorMessage) {
        super(errorMessage);
    }

    public static SimpleTechnicalIndicatorResponse of(Map<String, Object> stringObjectMap) {
        Parser<ADResponse> parser = new ADParser();
        return parser.parse(stringObjectMap);
    }

    public static class ADParser extends SimpleTechnicalIndicatorParser<ADResponse> {

        @Override
        public ADResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new ADResponse(indicatorUnits, metaData);
        }

        @Override
        public ADResponse get(String error) {
            return new ADResponse(error);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "Chaikin A/D";
        }
    }
}
