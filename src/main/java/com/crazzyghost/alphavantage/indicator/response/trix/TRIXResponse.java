package com.crazzyghost.alphavantage.indicator.response.trix;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the 1-day rate of change of a triple exponentially smoothed
 * moving average ({@code TRIX}), an oscillator that filters out short-term
 * price noise by rating the momentum of a triple-smoothed EMA.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.trix.TRIXResponse}
 */
@Deprecated
public class TRIXResponse extends PeriodicSeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed TRIX values
     * @param metaData       the parsed response metadata
     */
    private TRIXResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private TRIXResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link TRIXResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static TRIXResponse of(Map<String, Object> stringObjectMap){
        Parser<TRIXResponse> parser = new TRIXResponseParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link TRIXResponse}.
     */
    public static class TRIXResponseParser extends PeriodicSeriesParser<TRIXResponse> {

        @Override
        public TRIXResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new TRIXResponse(indicatorUnits, metaData);
        }

        @Override
        public TRIXResponse get(String errorMessage) {
            return new TRIXResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "TRIX";
        }
    }
}
