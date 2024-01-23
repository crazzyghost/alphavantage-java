package com.crazzyghost.alphavantage.technicalindicator.response.natr;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class NATRResponse extends PeriodicResponse {

    private NATRResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private NATRResponse(String errorMessage) {
        super(errorMessage);
    }

    public static NATRResponse of(Map<String, Object> stringObjectMap) {
        Parser<NATRResponse> parser = new NATRParser();
        return parser.parse(stringObjectMap);
    }

    public static class NATRParser extends PeriodicParser<NATRResponse> {

        @Override
        public NATRResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new NATRResponse(indicatorUnits, metaData);
        }

        @Override
        public NATRResponse get(String errorMessage) {
            return new NATRResponse(errorMessage);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "NATR";
        }
    }
}
