package com.crazzyghost.alphavantage.indicator.response.roc;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class ROCResponse extends PeriodicSeriesResponse {

    private ROCResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private ROCResponse(String errorMessage) {
        super(errorMessage);
    }

    public static ROCResponse of(Map<String, Object> stringObjectMap){
        Parser<ROCResponse> parser = new ROCResponseParser();
        return parser.parse(stringObjectMap);
    }

    public static class ROCResponseParser extends PeriodicSeriesParser<ROCResponse> {

        @Override
        public ROCResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new ROCResponse(indicatorUnits, metaData);
        }

        @Override
        public ROCResponse get(String errorMessage) {
            return new ROCResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "ROC";
        }
    }
}
