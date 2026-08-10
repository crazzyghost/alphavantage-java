package com.crazzyghost.alphavantage.indicator.response.rsi;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the relative strength index ({@code RSI}), a momentum
 * oscillator that measures the speed and magnitude of recent price
 * movement on a 0-100 scale to flag overbought or oversold conditions.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.rsi.RSIResponse}
 */
@Deprecated
public class RSIResponse extends PeriodicSeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed RSI values
     * @param metaData       the parsed response metadata
     */
    private RSIResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private RSIResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into an {@link RSIResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static RSIResponse of(Map<String, Object> stringObjectMap){
        Parser<RSIResponse> parser = new RSIResponseParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link RSIResponse}.
     */
    public static class RSIResponseParser extends PeriodicSeriesParser<RSIResponse> {

        @Override
        public RSIResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new RSIResponse(indicatorUnits, metaData);
        }

        @Override
        public RSIResponse get(String errorMessage) {
            return new RSIResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "RSI";
        }
    }
}
