package com.crazzyghost.alphavantage.indicator.response.adxr;

import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the average directional movement index rating ({@code ADXR}),
 * the mean of the current and a prior {@code ADX} reading, smoothing ADX
 * further to reduce its sensitivity to sudden trend-strength swings.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.adxr.ADXRResponse}
 */
@Deprecated
public class ADXRResponse extends PeriodicResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed ADXR values
     * @param metaData       the parsed response metadata
     */
    private ADXRResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private ADXRResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into an {@link ADXRResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static ADXRResponse of(Map<String, Object> stringObjectMap){
        Parser<ADXRResponse> parser = new ADXRParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link ADXRResponse}.
     */
    public static class ADXRParser extends PeriodicParser<ADXRResponse> {

        @Override
        public ADXRResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new ADXRResponse(indicatorUnits, metaData);
        }

        @Override
        public ADXRResponse get(String errorMessage) {
            return new ADXRResponse(errorMessage);
        }

        @Override
        public String getIndicatorKey() {
            return "ADXR";
        }
    }
}
