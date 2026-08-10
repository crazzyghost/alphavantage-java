package com.crazzyghost.alphavantage.indicator.response.ema;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the exponential moving average ({@code EMA}), a moving
 * average of a price series that weights recent data points more heavily
 * than a simple moving average does.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.ema.EMAResponse}
 */
@Deprecated
public class EMAResponse extends PeriodicSeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed EMA values
     * @param metaData       the parsed response metadata
     */
    private EMAResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private EMAResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into an {@link EMAResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static EMAResponse of(Map<String, Object> stringObjectMap){
        Parser<EMAResponse> parser = new EMAResponseParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link EMAResponse}.
     */
    public static class EMAResponseParser extends PeriodicSeriesParser<EMAResponse> {

        @Override
        public EMAResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new EMAResponse(indicatorUnits, metaData);
        }

        @Override
        public EMAResponse get(String errorMessage) {
            return new EMAResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "EMA";
        }
    }
}
