package com.crazzyghost.alphavantage.indicator.response.kama;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the Kaufman adaptive moving average ({@code KAMA}), a moving
 * average that automatically adjusts its own smoothing speed based on
 * recent price volatility and trendiness.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.kama.KAMAResponse}
 */
@Deprecated
public class KAMAResponse extends PeriodicSeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed KAMA values
     * @param metaData       the parsed response metadata
     */
    private KAMAResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private KAMAResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link KAMAResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static KAMAResponse of(Map<String, Object> stringObjectMap){
        Parser<KAMAResponse> parser = new KAMAResponseParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link KAMAResponse}.
     */
    public static class KAMAResponseParser extends PeriodicSeriesParser<KAMAResponse> {

        @Override
        public KAMAResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new KAMAResponse(indicatorUnits, metaData);
        }

        @Override
        public KAMAResponse get(String errorMessage) {
            return new KAMAResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "KAMA";
        }
    }
}
