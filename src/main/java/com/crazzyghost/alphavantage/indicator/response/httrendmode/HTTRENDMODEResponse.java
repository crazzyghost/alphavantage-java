package com.crazzyghost.alphavantage.indicator.response.httrendmode;

import com.crazzyghost.alphavantage.indicator.response.SeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the Hilbert transform trend vs cycle mode
 * ({@code HT_TRENDMODE}), which classifies the price series as currently
 * trending or cycling.
 * <p>
 * The API nests this indicator's value under the JSON key {@code TRENDMODE}
 * rather than the function code {@code HT_TRENDMODE}, unlike its Hilbert
 * transform siblings.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.httrendmode.HTTRENDMODEResponse}
 */
@Deprecated
public class HTTRENDMODEResponse extends SeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed HT_TRENDMODE values
     * @param metaData       the parsed response metadata
     */
    private HTTRENDMODEResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private HTTRENDMODEResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into an {@link HTTRENDMODEResponse}.
     *
     * @param data the raw parsed JSON response
     * @return the parsed response
     */
    public static HTTRENDMODEResponse of(Map<String, Object> data){
        Parser<HTTRENDMODEResponse> parser = new HTTRENDMODEParser();
        return parser.parse(data);
    }

    /**
     * Parser for {@link HTTRENDMODEResponse}.
     */
    public static class HTTRENDMODEParser extends SeriesParser<HTTRENDMODEResponse> {

        @Override
        public HTTRENDMODEResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new HTTRENDMODEResponse(indicatorUnits, metaData);
        }

        @Override
        public HTTRENDMODEResponse get(String error) {
            return new HTTRENDMODEResponse(error);
        }

        @Override
        public String getIndicatorKey() {
            return "TRENDMODE";
        }
    }
}
