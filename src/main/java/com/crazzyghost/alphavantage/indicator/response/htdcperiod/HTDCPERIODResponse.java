package com.crazzyghost.alphavantage.indicator.response.htdcperiod;

import com.crazzyghost.alphavantage.indicator.response.SeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class HTDCPERIODResponse extends SeriesResponse {

    private HTDCPERIODResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private HTDCPERIODResponse(String errorMessage) {
        super(errorMessage);
    }

    public static HTDCPERIODResponse of(Map<String, Object> data){
        Parser<HTDCPERIODResponse> parser = new HTDCPERIODParser();
        return parser.parse(data);
    }

    public static class HTDCPERIODParser extends SeriesParser<HTDCPERIODResponse> {

        @Override
        public HTDCPERIODResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new HTDCPERIODResponse(indicatorUnits, metaData);
        }

        @Override
        public HTDCPERIODResponse get(String error) {
            return new HTDCPERIODResponse(error);
        }

        @Override
        public String getIndicatorKey() {
            return "DCPERIOD";
        }
    }
}
