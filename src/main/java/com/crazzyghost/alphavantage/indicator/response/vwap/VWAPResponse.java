package com.crazzyghost.alphavantage.indicator.response.vwap;

import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the volume weighted average price ({@code VWAP}) for
 * intraday time series, the average price of a security weighted by the
 * volume traded at each price level during the trading day.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.vwap.VWAPResponse}
 */
@Deprecated
public class VWAPResponse extends SimpleIndicatorResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed VWAP values
     * @param metaData       the parsed response metadata
     */
    private VWAPResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private VWAPResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link VWAPResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static SimpleIndicatorResponse of(Map<String, Object> stringObjectMap) {
        Parser<VWAPResponse> parser = new VWAPParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link VWAPResponse}.
     */
    public static class VWAPParser extends SimpleIndicatorParser<VWAPResponse>{

        @Override
        public VWAPResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new VWAPResponse(indicatorUnits, metaData);
        }

        @Override
        public VWAPResponse get(String error) {
            return new VWAPResponse(error);
        }

        @Override
        public String getIndicatorKey() {
            return "VWAP";
        }
    }
}
