package com.crazzyghost.alphavantage.indicator.response.roc;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the rate of change ({@code ROC}), the percentage change
 * between a price series' current value and its value a fixed number of
 * periods earlier.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.roc.ROCResponse}
 */
@Deprecated
public class ROCResponse extends PeriodicSeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed ROC values
     * @param metaData       the parsed response metadata
     */
    private ROCResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private ROCResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link ROCResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static ROCResponse of(Map<String, Object> stringObjectMap){
        Parser<ROCResponse> parser = new ROCResponseParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link ROCResponse}.
     */
    public static class ROCResponseParser extends PeriodicSeriesParser<ROCResponse> {

        @Override
        public ROCResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new ROCResponse(indicatorUnits, metaData);
        }

        @Override
        public ROCResponse get(String errorMessage) {
            return new ROCResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "ROC";
        }
    }
}
