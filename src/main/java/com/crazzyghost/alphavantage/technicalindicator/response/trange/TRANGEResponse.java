package com.crazzyghost.alphavantage.technicalindicator.response.trange;

import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class TRANGEResponse extends SimpleTechnicalIndicatorResponse {

    private TRANGEResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private TRANGEResponse(String errorMessage) {
        super(errorMessage);
    }

    public static SimpleTechnicalIndicatorResponse of(Map<String, Object> stringObjectMap) {
        Parser<TRANGEResponse> parser = new TRANGEParser();
        return parser.parse(stringObjectMap);
    }

    public static class TRANGEParser extends SimpleTechnicalIndicatorParser<TRANGEResponse> {

        @Override
        public TRANGEResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new TRANGEResponse(indicatorUnits, metaData);
        }

        @Override
        public TRANGEResponse get(String error) {
            return new TRANGEResponse(error);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "TRANGE";
        }
    }
}
