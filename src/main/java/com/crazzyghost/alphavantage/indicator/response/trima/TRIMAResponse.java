package com.crazzyghost.alphavantage.indicator.response.trima;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the triangular moving average ({@code TRIMA}), a moving
 * average of a price series that double-smooths by averaging an SMA of an
 * SMA, weighting middle values in the period most heavily.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.trima.TRIMAResponse}
 */
@Deprecated
public class TRIMAResponse extends PeriodicSeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed TRIMA values
     * @param metaData       the parsed response metadata
     */
    private TRIMAResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private TRIMAResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link TRIMAResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static TRIMAResponse of(Map<String, Object> stringObjectMap){
        Parser<TRIMAResponse> parser = new TRIMAResponseParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link TRIMAResponse}.
     */
    public static class TRIMAResponseParser extends PeriodicSeriesParser<TRIMAResponse> {

        @Override
        public TRIMAResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new TRIMAResponse(indicatorUnits, metaData);
        }

        @Override
        public TRIMAResponse get(String errorMessage) {
            return new TRIMAResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "TRIMA";
        }
    }
}
