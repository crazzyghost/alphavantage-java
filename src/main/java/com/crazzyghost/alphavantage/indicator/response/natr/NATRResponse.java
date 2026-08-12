package com.crazzyghost.alphavantage.indicator.response.natr;

import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the normalized average true range ({@code NATR}), the
 * {@code ATR} expressed as a percentage of closing price, making
 * volatility comparable across instruments at different price levels.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.natr.NATRResponse}
 */
@Deprecated
public class NATRResponse extends PeriodicResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed NATR values
     * @param metaData       the parsed response metadata
     */
    private NATRResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private NATRResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into an {@link NATRResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static NATRResponse of(Map<String, Object> stringObjectMap){
        Parser<NATRResponse> parser = new NATRParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link NATRResponse}.
     */
    public static class NATRParser extends PeriodicParser<NATRResponse> {

        @Override
        public NATRResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new NATRResponse(indicatorUnits, metaData);
        }

        @Override
        public NATRResponse get(String errorMessage) {
            return new NATRResponse(errorMessage);
        }

        @Override
        public String getIndicatorKey() {
            return "NATR";
        }
    }
}
