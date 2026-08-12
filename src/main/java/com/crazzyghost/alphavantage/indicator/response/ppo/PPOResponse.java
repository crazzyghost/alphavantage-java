package com.crazzyghost.alphavantage.indicator.response.ppo;

import com.crazzyghost.alphavantage.indicator.response.PriceOscillatorResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.indicator.response.roc.ROCResponse;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the percentage price oscillator ({@code PPO}), the
 * difference between a fast and slow moving average of a price series
 * expressed as a percentage of the slow average, making it comparable
 * across instruments at different price levels unlike {@code APO}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.ppo.PPOResponse}
 */
@Deprecated
public class PPOResponse extends PriceOscillatorResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed PPO values
     * @param metaData       the parsed response metadata
     */
    private PPOResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Parses a raw API response into a {@link PPOResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static PPOResponse of(Map<String, Object> stringObjectMap){
        Parser<PPOResponse> parser = new PPOParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private PPOResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parser for {@link PPOResponse}.
     */
    public static class PPOParser extends PriceOscillatorParser<PPOResponse> {
        @Override
        public PPOResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new PPOResponse(indicatorUnits, metaData);
        }

        @Override
        public PPOResponse get(String error) {
            return new PPOResponse(error);
        }

        @Override
        public String getIndicatorKey() {
            return "PPO";
        }
    }
}
