package com.crazzyghost.alphavantage.indicator.response.rocr;

import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the rate of change ratio ({@code ROCR}), the ratio between a
 * price series' current value and its value a fixed number of periods
 * earlier — the multiplicative counterpart of {@code ROC}'s percentage
 * form.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.rocr.ROCRResponse}
 */
@Deprecated
public class ROCRResponse extends PeriodicSeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed ROCR values
     * @param metaData       the parsed response metadata
     */
    private ROCRResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private ROCRResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link ROCRResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static ROCRResponse of(Map<String, Object> stringObjectMap){
        Parser<ROCRResponse> parser = new ROCRResponseParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link ROCRResponse}.
     */
    public static class ROCRResponseParser extends PeriodicSeriesParser<ROCRResponse> {

        @Override
        public ROCRResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new ROCRResponse(indicatorUnits, metaData);
        }

        @Override
        public ROCRResponse get(String errorMessage) {
            return new ROCRResponse(errorMessage);
        }

        @Override
        protected String getIndicatorKey() {
            return "ROCR";
        }
    }
}
