package com.crazzyghost.alphavantage.indicator.response.trange;

import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class TRANGEResponse extends SimpleIndicatorResponse {

    private TRANGEResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private TRANGEResponse(String errorMessage) {
        super(errorMessage);
    }

    public static SimpleIndicatorResponse of(Map<String, Object> stringObjectMap) {
        Parser<TRANGEResponse> parser = new TRANGEParser();
        return parser.parse(stringObjectMap);
    }

    public static class TRANGEParser extends SimpleIndicatorParser<TRANGEResponse>{

        @Override
        public TRANGEResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new TRANGEResponse(indicatorUnits, metaData);
        }

        @Override
        public TRANGEResponse get(String error) {
            return new TRANGEResponse(error);
        }

        @Override
        public String getIndicatorKey() {
            return "TRANGE";
        }
    }
}
