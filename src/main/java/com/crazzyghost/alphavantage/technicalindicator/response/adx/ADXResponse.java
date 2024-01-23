package com.crazzyghost.alphavantage.technicalindicator.response.adx;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class ADXResponse extends PeriodicResponse {

    private ADXResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private ADXResponse(String errorMessage) {
        super(errorMessage);
    }

    public static ADXResponse of(Map<String, Object> stringObjectMap) {
        Parser<ADXResponse> parser = new ADXParser();
        return parser.parse(stringObjectMap);
    }

    public static class ADXParser extends PeriodicParser<ADXResponse> {

        @Override
        public ADXResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new ADXResponse(indicatorUnits, metaData);
        }

        @Override
        public ADXResponse get(String errorMessage) {
            return new ADXResponse(errorMessage);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "ADX";
        }
    }
}
