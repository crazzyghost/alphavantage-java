package com.crazzyghost.alphavantage.indicator.response.mom;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for momentum ({@code MOM}), the raw difference between a price
 * series' current value and its value a fixed number of periods earlier.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.mom.MOMResponse}
 */
@Deprecated
public class MOMResponse extends PeriodicSeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed MOM values
     * @param metaData       the parsed response metadata
     */
    private MOMResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private MOMResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link MOMResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static MOMResponse of(Map<String, Object> stringObjectMap){
        Parser<MOMResponse> parser = new MOMResponseParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link MOMResponse}.
     */
    public static class MOMResponseParser extends PeriodicSeriesParser<MOMResponse> {

        @Override
        public MOMResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new MOMResponse(indicatorUnits, metaData);
        }

        @Override
        public MOMResponse get(String errorMessage) {
            return new MOMResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "MOM";
        }
    }
}
