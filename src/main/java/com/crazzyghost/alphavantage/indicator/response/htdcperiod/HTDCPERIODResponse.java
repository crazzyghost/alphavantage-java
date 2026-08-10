package com.crazzyghost.alphavantage.indicator.response.htdcperiod;

import com.crazzyghost.alphavantage.indicator.response.SeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the Hilbert transform dominant cycle period
 * ({@code HT_DCPERIOD}), which estimates the length, in bars, of the price
 * series' current dominant market cycle.
 * <p>
 * The API nests this indicator's value under the JSON key {@code DCPERIOD}
 * rather than the function code {@code HT_DCPERIOD}, unlike its Hilbert
 * transform siblings.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.htdcperiod.HTDCPERIODResponse}
 */
@Deprecated
public class HTDCPERIODResponse extends SeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed HT_DCPERIOD values
     * @param metaData       the parsed response metadata
     */
    private HTDCPERIODResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private HTDCPERIODResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into an {@link HTDCPERIODResponse}.
     *
     * @param data the raw parsed JSON response
     * @return the parsed response
     */
    public static HTDCPERIODResponse of(Map<String, Object> data){
        Parser<HTDCPERIODResponse> parser = new HTDCPERIODParser();
        return parser.parse(data);
    }

    /**
     * Parser for {@link HTDCPERIODResponse}.
     */
    public static class HTDCPERIODParser extends SeriesParser<HTDCPERIODResponse> {

        @Override
        public HTDCPERIODResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new HTDCPERIODResponse(indicatorUnits, metaData);
        }

        @Override
        public HTDCPERIODResponse get(String error) {
            return new HTDCPERIODResponse(error);
        }

        @Override
        public String getIndicatorKey() {
            return "DCPERIOD";
        }
    }
}
