package com.crazzyghost.alphavantage.technicalindicator.response.dx;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class DXResponse extends PeriodicResponse {

    private DXResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private DXResponse(String errorMessage) {
        super(errorMessage);
    }

    public static DXResponse of(Map<String, Object> stringObjectMap) {
        Parser<DXResponse> parser = new DXParser();
        return parser.parse(stringObjectMap);
    }

    public static class DXParser extends PeriodicParser<DXResponse> {

        @Override
        public DXResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new DXResponse(indicatorUnits, metaData);
        }

        @Override
        public DXResponse get(String errorMessage) {
            return new DXResponse(errorMessage);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "DX";
        }
    }
}
