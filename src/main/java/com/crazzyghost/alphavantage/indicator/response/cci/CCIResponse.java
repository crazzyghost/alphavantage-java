package com.crazzyghost.alphavantage.indicator.response.cci;

import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the commodity channel index ({@code CCI}), which measures
 * how far a typical price has moved from its statistical mean over a
 * rolling time period, expressed in standard deviations.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.cci.CCIResponse}
 */
@Deprecated
public class CCIResponse extends PeriodicResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed CCI values
     * @param metaData       the parsed response metadata
     */
    private CCIResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private CCIResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link CCIResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static CCIResponse of(Map<String, Object> stringObjectMap){
        Parser<CCIResponse> parser = new CCIParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link CCIResponse}.
     */
    public static class CCIParser extends PeriodicParser<CCIResponse> {

        @Override
        public CCIResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new CCIResponse(indicatorUnits, metaData);
        }

        @Override
        public CCIResponse get(String errorMessage) {
            return new CCIResponse(errorMessage);
        }

        @Override
        public String getIndicatorKey() {
            return "CCI";
        }
    }
}
