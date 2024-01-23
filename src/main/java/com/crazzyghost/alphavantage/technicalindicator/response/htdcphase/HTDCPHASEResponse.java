package com.crazzyghost.alphavantage.technicalindicator.response.htdcphase;

import com.crazzyghost.alphavantage.technicalindicator.response.SeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class HTDCPHASEResponse extends SeriesResponse {

    private HTDCPHASEResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private HTDCPHASEResponse(String errorMessage) {
        super(errorMessage);
    }

    public static HTDCPHASEResponse of(Map<String, Object> data) {
        Parser<HTDCPHASEResponse> parser = new HTDCPHASEParser();
        return parser.parse(data);
    }

    public static class HTDCPHASEParser extends SeriesParser<HTDCPHASEResponse> {

        @Override
        public HTDCPHASEResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new HTDCPHASEResponse(indicatorUnits, metaData);
        }

        @Override
        public HTDCPHASEResponse get(String error) {
            return new HTDCPHASEResponse(error);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "HT_DCPHASE";
        }
    }
}
