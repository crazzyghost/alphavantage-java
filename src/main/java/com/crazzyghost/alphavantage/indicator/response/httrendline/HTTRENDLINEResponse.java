package com.crazzyghost.alphavantage.indicator.response.httrendline;

import com.crazzyghost.alphavantage.indicator.response.SeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the Hilbert transform instantaneous trendline
 * ({@code HT_TRENDLINE}), a smoothed moving average derived from the
 * Hilbert transform that adapts faster to trend changes than a fixed-period
 * moving average.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.httrendline.HTTRENDLINEResponse}
 */
@Deprecated
public class HTTRENDLINEResponse extends SeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed HT_TRENDLINE values
     * @param metaData       the parsed response metadata
     */
    private HTTRENDLINEResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private HTTRENDLINEResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into an {@link HTTRENDLINEResponse}.
     *
     * @param data the raw parsed JSON response
     * @return the parsed response
     */
    public static HTTRENDLINEResponse of(Map<String, Object> data){
        Parser<HTTRENDLINEResponse> parser = new HTTRENDLINEParser();
        return parser.parse(data);
    }

    /**
     * Parser for {@link HTTRENDLINEResponse}.
     */
    public static class HTTRENDLINEParser extends SeriesParser<HTTRENDLINEResponse> {

        @Override
        public HTTRENDLINEResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new HTTRENDLINEResponse(indicatorUnits, metaData);
        }

        @Override
        public HTTRENDLINEResponse get(String error) {
            return new HTTRENDLINEResponse(error);
        }

        @Override
        public String getIndicatorKey() {
            return "HT_TRENDLINE";
        }
    }
}
