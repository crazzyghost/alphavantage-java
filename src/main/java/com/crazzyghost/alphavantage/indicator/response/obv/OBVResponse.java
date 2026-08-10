package com.crazzyghost.alphavantage.indicator.response.obv;

import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for on balance volume ({@code OBV}), a cumulative running total
 * of traded volume that adds a period's volume when price closes up and
 * subtracts it when price closes down.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.obv.OBVResponse}
 */
@Deprecated
public class OBVResponse extends SimpleIndicatorResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed OBV values
     * @param metaData       the parsed response metadata
     */
    private OBVResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private OBVResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into an {@link OBVResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static SimpleIndicatorResponse of(Map<String, Object> stringObjectMap) {
        Parser<OBVResponse> parser = new OBVParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link OBVResponse}.
     */
    public static class OBVParser extends SimpleIndicatorParser<OBVResponse>{

        @Override
        public OBVResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new OBVResponse(indicatorUnits, metaData);
        }

        @Override
        public OBVResponse get(String error) {
            return new OBVResponse(error);
        }

        @Override
        public String getIndicatorKey() {
            return "OBV";
        }
    }
}
