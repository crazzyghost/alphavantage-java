package com.crazzyghost.alphavantage.technicalindicator.response.httrendmode;

import com.crazzyghost.alphavantage.technicalindicator.response.SeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class HTTRENDMODEResponse extends SeriesResponse {

    private HTTRENDMODEResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private HTTRENDMODEResponse(String errorMessage) {
        super(errorMessage);
    }

    public static HTTRENDMODEResponse of(Map<String, Object> data) {
        Parser<HTTRENDMODEResponse> parser = new HTTRENDMODEParser();
        return parser.parse(data);
    }

    public static class HTTRENDMODEParser extends SeriesParser<HTTRENDMODEResponse> {

        @Override
        public HTTRENDMODEResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new HTTRENDMODEResponse(indicatorUnits, metaData);
        }

        @Override
        public HTTRENDMODEResponse get(String error) {
            return new HTTRENDMODEResponse(error);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "TRENDMODE";
        }
    }
}
