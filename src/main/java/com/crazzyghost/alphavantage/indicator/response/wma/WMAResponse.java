package com.crazzyghost.alphavantage.indicator.response.wma;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the weighted moving average ({@code WMA}), a moving average
 * of a price series that assigns linearly increasing weight to more recent
 * data points.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.wma.WMAResponse}
 */
@Deprecated
public class WMAResponse extends PeriodicSeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed WMA values
     * @param metaData       the parsed response metadata
     */
    private WMAResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private WMAResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link WMAResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static WMAResponse of(Map<String, Object> stringObjectMap){
        Parser<WMAResponse> parser = new WMAResponseParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link WMAResponse}.
     */
    public static class WMAResponseParser extends PeriodicSeriesParser<WMAResponse> {

        @Override
        public WMAResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new WMAResponse(indicatorUnits, metaData);
        }

        @Override
        public WMAResponse get(String errorMessage) {
            return new WMAResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "WMA";
        }
    }
}
