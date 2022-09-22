package com.crazzyghost.alphavantage.technicalindicator.response.ppo;

import com.crazzyghost.alphavantage.technicalindicator.response.PriceOscillatorResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.technicalindicator.response.roc.ROCResponse;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class PPOResponse extends PriceOscillatorResponse {

    private PPOResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    public static PPOResponse of(Map<String, Object> stringObjectMap) {
        Parser<PPOResponse> parser = new PPOParser();
        return parser.parse(stringObjectMap);
    }

    private PPOResponse(String errorMessage) {
        super(errorMessage);
    }

    public static class PPOParser extends PriceOscillatorParser<PPOResponse> {
        @Override
        public PPOResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new PPOResponse(indicatorUnits, metaData);
        }

        @Override
        public PPOResponse get(String error) {
            return new PPOResponse(error);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "PPO";
        }
    }
}
