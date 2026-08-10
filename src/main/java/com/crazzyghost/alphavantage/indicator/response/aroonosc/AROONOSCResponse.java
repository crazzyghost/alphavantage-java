package com.crazzyghost.alphavantage.indicator.response.aroonosc;

import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the Aroon oscillator ({@code AROONOSC}), the difference
 * between the Aroon-Up and Aroon-Down lines, condensing the two-line
 * {@code AROON} indicator into a single trend-direction reading.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.aroonosc.AROONOSCResponse}
 */
@Deprecated
public class AROONOSCResponse extends PeriodicResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed AROONOSC values
     * @param metaData       the parsed response metadata
     */
    private AROONOSCResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private AROONOSCResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into an {@link AROONOSCResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static AROONOSCResponse of(Map<String, Object> stringObjectMap){
        Parser<AROONOSCResponse> parser = new AROONOSCParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link AROONOSCResponse}.
     */
    public static class AROONOSCParser extends PeriodicParser<AROONOSCResponse> {

        @Override
        public AROONOSCResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new AROONOSCResponse(indicatorUnits, metaData);
        }

        @Override
        public AROONOSCResponse get(String errorMessage) {
            return new AROONOSCResponse(errorMessage);
        }

        @Override
        public String getIndicatorKey() {
            return "AROONOSC";
        }
    }
}
