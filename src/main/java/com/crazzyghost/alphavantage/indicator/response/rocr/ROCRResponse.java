package com.crazzyghost.alphavantage.indicator.response.rocr;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class ROCRResponse extends PeriodicSeriesResponse {

    private ROCRResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private ROCRResponse(String errorMessage) {
        super(errorMessage);
    }

    public static ROCRResponse of(Map<String, Object> stringObjectMap){
        Parser<ROCRResponse> parser = new ROCRResponseParser();
        return parser.parse(stringObjectMap);
    }

    public static class ROCRResponseParser extends PeriodicSeriesParser<ROCRResponse> {

        @Override
        public ROCRResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new ROCRResponse(indicatorUnits, metaData);
        }

        @Override
        public ROCRResponse get(String errorMessage) {
            return new ROCRResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "ROCR";
        }
    }
}
