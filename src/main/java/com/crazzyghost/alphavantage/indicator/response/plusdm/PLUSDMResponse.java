package com.crazzyghost.alphavantage.indicator.response.plusdm;

import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the plus directional movement ({@code PLUS_DM}), the raw
 * upward price-movement component that {@code PLUS_DI} normalizes into an
 * indicator.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.plusdm.PLUSDMResponse}
 */
@Deprecated
public class PLUSDMResponse extends PeriodicResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed PLUS_DM values
     * @param metaData       the parsed response metadata
     */
    private PLUSDMResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private PLUSDMResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link PLUSDMResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static PLUSDMResponse of(Map<String, Object> stringObjectMap){
        Parser<PLUSDMResponse> parser = new PLUSDMParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link PLUSDMResponse}.
     */
    public static class PLUSDMParser extends PeriodicParser<PLUSDMResponse> {

        @Override
        public PLUSDMResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new PLUSDMResponse(indicatorUnits, metaData);
        }

        @Override
        public PLUSDMResponse get(String errorMessage) {
            return new PLUSDMResponse(errorMessage);
        }

        @Override
        public String getIndicatorKey() {
            return "PLUS_DM";
        }
    }
}
