package com.crazzyghost.alphavantage.technicalindicator.response.httrendline;

import com.crazzyghost.alphavantage.technicalindicator.response.SeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class HTTRENDLINEResponse extends SeriesResponse {

    private HTTRENDLINEResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private HTTRENDLINEResponse(String errorMessage) {
        super(errorMessage);
    }

    public static HTTRENDLINEResponse of(Map<String, Object> data) {
        Parser<HTTRENDLINEResponse> parser = new HTTRENDLINEParser();
        return parser.parse(data);
    }

    public static class HTTRENDLINEParser extends SeriesParser<HTTRENDLINEResponse> {

        @Override
        public HTTRENDLINEResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new HTTRENDLINEResponse(indicatorUnits, metaData);
        }

        @Override
        public HTTRENDLINEResponse get(String error) {
            return new HTTRENDLINEResponse(error);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "HT_TRENDLINE";
        }
    }
}
