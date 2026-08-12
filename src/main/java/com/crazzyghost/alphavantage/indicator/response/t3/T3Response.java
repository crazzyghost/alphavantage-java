package com.crazzyghost.alphavantage.indicator.response.t3;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for T3, Tillson's triple exponential moving average
 * ({@code T3}), a smoothed moving average built from a chain of six EMAs
 * that responds to trend changes faster than a plain triple EMA.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.t3.T3Response}
 */
@Deprecated
public class T3Response extends PeriodicSeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed T3 values
     * @param metaData       the parsed response metadata
     */
    private T3Response(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private T3Response(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link T3Response}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static T3Response of(Map<String, Object> stringObjectMap){
        Parser<T3Response> parser = new T3ResponseParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link T3Response}.
     */
    public static class T3ResponseParser extends PeriodicSeriesParser<T3Response> {

        @Override
        public T3Response get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new T3Response(indicatorUnits, metaData);
        }

        @Override
        public T3Response get(String errorMessage) {
            return new T3Response(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "T3";
        }
    }
}
