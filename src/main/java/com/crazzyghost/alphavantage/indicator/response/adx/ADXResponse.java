package com.crazzyghost.alphavantage.indicator.response.adx;

import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the average directional movement index ({@code ADX}), a
 * trend-strength indicator derived from the smoothed difference between the
 * plus and minus directional indicators, without regard to trend direction.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.adx.ADXResponse}
 */
@Deprecated
public class ADXResponse extends PeriodicResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed ADX values
     * @param metaData       the parsed response metadata
     */
    private ADXResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private ADXResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into an {@link ADXResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static ADXResponse of(Map<String, Object> stringObjectMap){
        Parser<ADXResponse> parser = new ADXParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link ADXResponse}.
     */
    public static class ADXParser extends PeriodicParser<ADXResponse> {

        @Override
        public ADXResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new ADXResponse(indicatorUnits, metaData);
        }

        @Override
        public ADXResponse get(String errorMessage) {
            return new ADXResponse(errorMessage);
        }

        @Override
        public String getIndicatorKey() {
            return "ADX";
        }
    }
}
