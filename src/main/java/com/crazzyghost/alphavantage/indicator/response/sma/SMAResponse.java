package com.crazzyghost.alphavantage.indicator.response.sma;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the simple moving average ({@code SMA}), the unweighted mean
 * of a price series over a rolling time period.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.sma.SMAResponse}
 */
@Deprecated
public class SMAResponse extends PeriodicSeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed SMA values
     * @param metaData       the parsed response metadata
     */
    private SMAResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private SMAResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into an {@link SMAResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static SMAResponse of(Map<String, Object> stringObjectMap){
        Parser<SMAResponse> parser = new SMAResponseParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link SMAResponse}.
     */
    public static class SMAResponseParser extends PeriodicSeriesParser<SMAResponse> {

        @Override
        public SMAResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new SMAResponse(indicatorUnits, metaData);
        }

        @Override
        public SMAResponse get(String errorMessage) {
            return new SMAResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "SMA";
        }
    }
}
