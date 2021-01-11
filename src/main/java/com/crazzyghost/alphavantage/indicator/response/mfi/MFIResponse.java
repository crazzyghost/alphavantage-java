package com.crazzyghost.alphavantage.indicator.response.mfi;

import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class MFIResponse extends PeriodicResponse {

    private MFIResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private MFIResponse(String errorMessage) {
        super(errorMessage);
    }

    public static MFIResponse of(Map<String, Object> stringObjectMap){
        Parser<MFIResponse> parser = new MFIParser();
        return parser.parse(stringObjectMap);
    }

    public static class MFIParser extends PeriodicParser<MFIResponse> {

        @Override
        public MFIResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new MFIResponse(indicatorUnits, metaData);
        }

        @Override
        public MFIResponse get(String errorMessage) {
            return new MFIResponse(errorMessage);
        }

        @Override
        public String getIndicatorKey() {
            return "MFI";
        }
    }
}
