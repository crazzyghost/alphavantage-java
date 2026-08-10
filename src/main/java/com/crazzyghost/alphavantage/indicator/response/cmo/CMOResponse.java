package com.crazzyghost.alphavantage.indicator.response.cmo;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the Chande momentum oscillator ({@code CMO}), which measures
 * momentum as the difference between a series' summed gains and summed
 * losses over a rolling time period, normalized by their sum.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.cmo.CMOResponse}
 */
@Deprecated
public class CMOResponse extends PeriodicSeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed CMO values
     * @param metaData       the parsed response metadata
     */
    private CMOResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private CMOResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link CMOResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static CMOResponse of(Map<String, Object> stringObjectMap){
        Parser<CMOResponse> parser = new CMOResponseParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link CMOResponse}.
     */
    public static class CMOResponseParser extends PeriodicSeriesParser<CMOResponse> {

        @Override
        public CMOResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new CMOResponse(indicatorUnits, metaData);
        }

        @Override
        public CMOResponse get(String errorMessage) {
            return new CMOResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "CMO";
        }
    }
}
