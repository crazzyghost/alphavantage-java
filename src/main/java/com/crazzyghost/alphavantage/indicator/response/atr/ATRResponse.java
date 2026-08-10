package com.crazzyghost.alphavantage.indicator.response.atr;

import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the average true range ({@code ATR}), a smoothed moving
 * average of the true range that measures an instrument's volatility
 * without regard to price direction.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.atr.ATRResponse}
 */
@Deprecated
public class ATRResponse extends PeriodicResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed ATR values
     * @param metaData       the parsed response metadata
     */
    private ATRResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private ATRResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into an {@link ATRResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static ATRResponse of(Map<String, Object> stringObjectMap){
        Parser<ATRResponse> parser = new ATRParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link ATRResponse}.
     */
    public static class ATRParser extends PeriodicParser<ATRResponse> {

        @Override
        public ATRResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new ATRResponse(indicatorUnits, metaData);
        }

        @Override
        public ATRResponse get(String errorMessage) {
            return new ATRResponse(errorMessage);
        }

        @Override
        public String getIndicatorKey() {
            return "ATR";
        }
    }
}
