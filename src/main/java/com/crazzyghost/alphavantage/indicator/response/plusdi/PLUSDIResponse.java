package com.crazzyghost.alphavantage.indicator.response.plusdi;

import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the plus directional indicator ({@code PLUS_DI}), which
 * measures upward price movement pressure and, together with
 * {@code MINUS_DI}, forms the basis of {@code ADX}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.plusdi.PLUSDIResponse}
 */
@Deprecated
public class PLUSDIResponse extends PeriodicResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed PLUS_DI values
     * @param metaData       the parsed response metadata
     */
    private PLUSDIResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private PLUSDIResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link PLUSDIResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static PLUSDIResponse of(Map<String, Object> stringObjectMap){
        Parser<PLUSDIResponse> parser = new PLUSDIParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link PLUSDIResponse}.
     */
    public static class PLUSDIParser extends PeriodicParser<PLUSDIResponse> {

        @Override
        public PLUSDIResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new PLUSDIResponse(indicatorUnits, metaData);
        }

        @Override
        public PLUSDIResponse get(String errorMessage) {
            return new PLUSDIResponse(errorMessage);
        }

        @Override
        public String getIndicatorKey() {
            return "PLUS_DI";
        }
    }
}
