package com.crazzyghost.alphavantage.indicator.response.midpoint;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the midpoint ({@code MIDPOINT}), the mean of a price
 * series' highest and lowest values over a rolling time period.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.midpoint.MIDPOINTResponse}
 */
@Deprecated
public class MIDPOINTResponse extends PeriodicSeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed MIDPOINT values
     * @param metaData       the parsed response metadata
     */
    private MIDPOINTResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private MIDPOINTResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link MIDPOINTResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static MIDPOINTResponse of(Map<String, Object> stringObjectMap){
        Parser<MIDPOINTResponse> parser = new MIDPOINTResponseParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link MIDPOINTResponse}.
     */
    public static class MIDPOINTResponseParser extends PeriodicSeriesParser<MIDPOINTResponse> {

        @Override
        public MIDPOINTResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new MIDPOINTResponse(indicatorUnits, metaData);
        }

        @Override
        public MIDPOINTResponse get(String errorMessage) {
            return new MIDPOINTResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "MIDPOINT";
        }
    }
}
