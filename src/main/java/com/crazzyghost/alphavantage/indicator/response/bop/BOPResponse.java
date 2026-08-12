package com.crazzyghost.alphavantage.indicator.response.bop;

import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the balance of power ({@code BOP}), a momentum indicator that
 * measures the strength of buyers versus sellers by comparing a period's
 * close-to-open move against its full high-low range.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.bop.BOPResponse}
 */
@Deprecated
public class BOPResponse extends SimpleIndicatorResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed BOP values
     * @param metaData       the parsed response metadata
     */
    private BOPResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private BOPResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link BOPResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static SimpleIndicatorResponse of(Map<String, Object> stringObjectMap) {
        Parser<BOPResponse> parser = new BOPParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link BOPResponse}.
     */
    public static class BOPParser extends SimpleIndicatorParser<BOPResponse>{

        @Override
        public BOPResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new BOPResponse(indicatorUnits, metaData);
        }

        @Override
        public BOPResponse get(String error) {
            return new BOPResponse(error);
        }

        @Override
        public String getIndicatorKey() {
            return "BOP";
        }
    }
}
