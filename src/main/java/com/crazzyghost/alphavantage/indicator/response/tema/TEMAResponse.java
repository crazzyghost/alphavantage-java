package com.crazzyghost.alphavantage.indicator.response.tema;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the triple exponential moving average ({@code TEMA}), which
 * combines a single, double, and triple EMA of a price series to reduce lag
 * further than {@code DEMA} does.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.tema.TEMAResponse}
 */
@Deprecated
public class TEMAResponse extends PeriodicSeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed TEMA values
     * @param metaData       the parsed response metadata
     */
    private TEMAResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private TEMAResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link TEMAResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static TEMAResponse of(Map<String, Object> stringObjectMap){
        Parser<TEMAResponse> parser = new TEMAResponseParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link TEMAResponse}.
     */
    public static class TEMAResponseParser extends PeriodicSeriesParser<TEMAResponse> {

        @Override
        public TEMAResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new TEMAResponse(indicatorUnits, metaData);
        }

        @Override
        public TEMAResponse get(String errorMessage) {
            return new TEMAResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "TEMA";
        }
    }
}
