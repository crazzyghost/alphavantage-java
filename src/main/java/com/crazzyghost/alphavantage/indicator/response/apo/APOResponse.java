package com.crazzyghost.alphavantage.indicator.response.apo;

import com.crazzyghost.alphavantage.indicator.response.PriceOscillatorResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.indicator.response.ppo.PPOResponse;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the absolute price oscillator ({@code APO}), the absolute
 * difference between a fast and slow moving average of a price series,
 * expressed in price units rather than as the percentage {@link PPOResponse
 * PPO} uses.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.apo.APOResponse}
 */
@Deprecated
public class APOResponse extends PriceOscillatorResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed APO values
     * @param metaData       the parsed response metadata
     */
    private APOResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private APOResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into an {@link APOResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static APOResponse of(Map<String, Object> stringObjectMap){
        Parser<APOResponse> parser = new APOParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link APOResponse}.
     */
    public static class APOParser extends PriceOscillatorParser<APOResponse> {
        @Override
        public APOResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new APOResponse(indicatorUnits, metaData);
        }

        @Override
        public APOResponse get(String error) {
            return new APOResponse(error);
        }

        @Override
        public String getIndicatorKey() {
            return "APO";
        }
    }
}
