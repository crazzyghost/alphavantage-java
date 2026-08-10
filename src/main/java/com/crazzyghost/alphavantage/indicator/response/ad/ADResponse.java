package com.crazzyghost.alphavantage.indicator.response.ad;

import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the Chaikin accumulation/distribution line ({@code AD}), a
 * cumulative volume-based indicator that tracks the flow of money into and
 * out of a security using its close relative to its high-low range.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.ad.ADResponse}
 */
@Deprecated
public class ADResponse extends SimpleIndicatorResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed AD values
     * @param metaData       the parsed response metadata
     */
    private ADResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private ADResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into an {@link ADResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static SimpleIndicatorResponse of(Map<String, Object> stringObjectMap) {
        Parser<ADResponse> parser = new ADParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link ADResponse}.
     */
    public static class ADParser extends SimpleIndicatorParser<ADResponse>{

        @Override
        public ADResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new ADResponse(indicatorUnits, metaData);
        }

        @Override
        public ADResponse get(String error) {
            return new ADResponse(error);
        }

        @Override
        public String getIndicatorKey() {
            return "Chaikin A/D";
        }
    }
}
