package com.crazzyghost.alphavantage.technicalindicator.response.mfi;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class MFIResponse extends PeriodicResponse {

    private MFIResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private MFIResponse(String errorMessage) {
        super(errorMessage);
    }

    public static MFIResponse of(Map<String, Object> stringObjectMap) {
        Parser<MFIResponse> parser = new MFIParser();
        return parser.parse(stringObjectMap);
    }

    public static class MFIParser extends PeriodicParser<MFIResponse> {

        @Override
        public MFIResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new MFIResponse(indicatorUnits, metaData);
        }

        @Override
        public MFIResponse get(String errorMessage) {
            return new MFIResponse(errorMessage);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "MFI";
        }
    }
}
