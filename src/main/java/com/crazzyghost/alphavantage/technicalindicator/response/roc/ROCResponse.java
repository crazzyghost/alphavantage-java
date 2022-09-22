package com.crazzyghost.alphavantage.technicalindicator.response.roc;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class ROCResponse extends PeriodicSeriesResponse {

    private ROCResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private ROCResponse(String errorMessage) {
        super(errorMessage);
    }

    public static ROCResponse of(Map<String, Object> stringObjectMap) {
        Parser<ROCResponse> parser = new ROCResponseParser();
        return parser.parse(stringObjectMap);
    }

    public static class ROCResponseParser extends PeriodicSeriesParser<ROCResponse> {

        @Override
        public ROCResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new ROCResponse(indicatorUnits, metaData);
        }

        @Override
        public ROCResponse get(String errorMessage) {
            return new ROCResponse(errorMessage);
        }

        @Override
        protected String getTechnicalIndicatorKey() {
            return "ROC";
        }
    }
}
