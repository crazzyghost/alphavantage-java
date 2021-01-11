package com.crazzyghost.alphavantage.indicator.response.dx;

import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class DXResponse extends PeriodicResponse {

    private DXResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private DXResponse(String errorMessage) {
        super(errorMessage);
    }

    public static DXResponse of(Map<String, Object> stringObjectMap){
        Parser<DXResponse> parser = new DXParser();
        return parser.parse(stringObjectMap);
    }

    public static class DXParser extends PeriodicParser<DXResponse> {

        @Override
        public DXResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new DXResponse(indicatorUnits, metaData);
        }

        @Override
        public DXResponse get(String errorMessage) {
            return new DXResponse(errorMessage);
        }

        @Override
        public String getIndicatorKey() {
            return "DX";
        }
    }
}
