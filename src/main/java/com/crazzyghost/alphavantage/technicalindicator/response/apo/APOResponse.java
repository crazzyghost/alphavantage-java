package com.crazzyghost.alphavantage.technicalindicator.response.apo;

import com.crazzyghost.alphavantage.technicalindicator.response.PriceOscillatorResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.technicalindicator.response.ppo.PPOResponse;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class APOResponse extends PriceOscillatorResponse {

    private APOResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private APOResponse(String errorMessage) {
        super(errorMessage);
    }

    public static APOResponse of(Map<String, Object> stringObjectMap) {
        Parser<APOResponse> parser = new APOParser();
        return parser.parse(stringObjectMap);
    }

    public static class APOParser extends PriceOscillatorParser<APOResponse> {
        @Override
        public APOResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new APOResponse(indicatorUnits, metaData);
        }

        @Override
        public APOResponse get(String error) {
            return new APOResponse(error);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "APO";
        }
    }
}
