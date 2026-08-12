package com.crazzyghost.alphavantage.indicator.response.htdcphase;

import com.crazzyghost.alphavantage.indicator.response.SeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the Hilbert transform dominant cycle phase
 * ({@code HT_DCPHASE}), which reports the price series' position, in
 * degrees, within its current dominant market cycle.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.htdcphase.HTDCPHASEResponse}
 */
@Deprecated
public class HTDCPHASEResponse extends SeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed HT_DCPHASE values
     * @param metaData       the parsed response metadata
     */
    private HTDCPHASEResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private HTDCPHASEResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into an {@link HTDCPHASEResponse}.
     *
     * @param data the raw parsed JSON response
     * @return the parsed response
     */
    public static HTDCPHASEResponse of(Map<String, Object> data){
        Parser<HTDCPHASEResponse> parser = new HTDCPHASEParser();
        return parser.parse(data);
    }

    /**
     * Parser for {@link HTDCPHASEResponse}.
     */
    public static class HTDCPHASEParser extends SeriesParser<HTDCPHASEResponse> {

        @Override
        public HTDCPHASEResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new HTDCPHASEResponse(indicatorUnits, metaData);
        }

        @Override
        public HTDCPHASEResponse get(String error) {
            return new HTDCPHASEResponse(error);
        }

        @Override
        public String getIndicatorKey() {
            return "HT_DCPHASE";
        }
    }
}
