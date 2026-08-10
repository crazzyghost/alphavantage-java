package com.crazzyghost.alphavantage.indicator.response.trange;

import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the true range ({@code TRANGE}), the greatest of a period's
 * high-low range, its high versus the prior close, and its low versus the
 * prior close — the single-period building block {@code ATR} smooths.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.trange.TRANGEResponse}
 */
@Deprecated
public class TRANGEResponse extends SimpleIndicatorResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed TRANGE values
     * @param metaData       the parsed response metadata
     */
    private TRANGEResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private TRANGEResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link TRANGEResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static SimpleIndicatorResponse of(Map<String, Object> stringObjectMap) {
        Parser<TRANGEResponse> parser = new TRANGEParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link TRANGEResponse}.
     */
    public static class TRANGEParser extends SimpleIndicatorParser<TRANGEResponse>{

        @Override
        public TRANGEResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new TRANGEResponse(indicatorUnits, metaData);
        }

        @Override
        public TRANGEResponse get(String error) {
            return new TRANGEResponse(error);
        }

        @Override
        public String getIndicatorKey() {
            return "TRANGE";
        }
    }
}
