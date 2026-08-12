package com.crazzyghost.alphavantage.indicator.response.dema;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the double exponential moving average ({@code DEMA}), which
 * combines a single and a double EMA of a price series to reduce the lag a
 * plain EMA carries.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.dema.DEMAResponse}
 */
@Deprecated
public class DEMAResponse extends PeriodicSeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed DEMA values
     * @param metaData       the parsed response metadata
     */
    private DEMAResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private DEMAResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link DEMAResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static DEMAResponse of(Map<String, Object> stringObjectMap){
        Parser<DEMAResponse> parser = new DMAResponseParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link DEMAResponse}.
     */
    public static class DMAResponseParser extends PeriodicSeriesParser<DEMAResponse> {

        @Override
        public DEMAResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new DEMAResponse(indicatorUnits, metaData);
        }

        @Override
        public DEMAResponse get(String errorMessage) {
            return new DEMAResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "DEMA";
        }
    }
}
