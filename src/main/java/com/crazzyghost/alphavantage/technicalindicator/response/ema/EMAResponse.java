package com.crazzyghost.alphavantage.technicalindicator.response.ema;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class EMAResponse extends PeriodicSeriesResponse {

    private EMAResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private EMAResponse(String errorMessage) {
        super(errorMessage);
    }

    public static EMAResponse of(Map<String, Object> stringObjectMap) {
        Parser<EMAResponse> parser = new EMAResponseParser();
        return parser.parse(stringObjectMap);
    }

    public static class EMAResponseParser extends PeriodicSeriesParser<EMAResponse> {

        @Override
        public EMAResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new EMAResponse(indicatorUnits, metaData);
        }

        @Override
        public EMAResponse get(String errorMessage) {
            return new EMAResponse(errorMessage);
        }

        @Override
        protected String getTechnicalIndicatorKey() {
            return "EMA";
        }
    }
}
