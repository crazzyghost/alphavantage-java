package com.crazzyghost.alphavantage.indicator.response.htdcphase;

import com.crazzyghost.alphavantage.indicator.response.SeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class HTDCPHASEResponse extends SeriesResponse {

    private HTDCPHASEResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private HTDCPHASEResponse(String errorMessage) {
        super(errorMessage);
    }

    public static HTDCPHASEResponse of(Map<String, Object> data){
        Parser<HTDCPHASEResponse> parser = new HTDCPHASEParser();
        return parser.parse(data);
    }

    public static class HTDCPHASEParser extends SeriesParser<HTDCPHASEResponse> {

        @Override
        public HTDCPHASEResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new HTDCPHASEResponse(indicatorUnits, metaData);
        }

        @Override
        public HTDCPHASEResponse get(String error) {
            return new HTDCPHASEResponse(error);
        }

        @Override
        public String getIndicatorKey() {
            return "HT_DCPHASE";
        }
    }
}
