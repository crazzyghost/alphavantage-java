package com.crazzyghost.alphavantage.indicator.response.minusdm;

import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the minus directional movement ({@code MINUS_DM}), the raw
 * downward price-movement component that {@code MINUS_DI} normalizes into
 * an indicator.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.minusdm.MINUSDMResponse}
 */
@Deprecated
public class MINUSDMResponse extends PeriodicResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed MINUS_DM values
     * @param metaData       the parsed response metadata
     */
    private MINUSDMResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private MINUSDMResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link MINUSDMResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static MINUSDMResponse of(Map<String, Object> stringObjectMap){
        Parser<MINUSDMResponse> parser = new MINUSDMParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link MINUSDMResponse}.
     */
    public static class MINUSDMParser extends PeriodicParser<MINUSDMResponse> {

        @Override
        public MINUSDMResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new MINUSDMResponse(indicatorUnits, metaData);
        }

        @Override
        public MINUSDMResponse get(String errorMessage) {
            return new MINUSDMResponse(errorMessage);
        }

        @Override
        public String getIndicatorKey() {
            return "MINUS_DM";
        }
    }
}
