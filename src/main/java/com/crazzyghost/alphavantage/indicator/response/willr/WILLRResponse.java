package com.crazzyghost.alphavantage.indicator.response.willr;

import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for Williams' %R ({@code WILLR}), a momentum oscillator that
 * compares a period's close to its recent high-low range, similar to
 * {@code STOCH}'s %K but scaled and inverted.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.willr.WILLRResponse}
 */
@Deprecated
public class WILLRResponse extends PeriodicResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed WILLR values
     * @param metaData       the parsed response metadata
     */
    private WILLRResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private WILLRResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link WILLRResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static WILLRResponse of(Map<String, Object> stringObjectMap){
        Parser<WILLRResponse> parser = new WILLRParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link WILLRResponse}.
     */
    public static class WILLRParser extends PeriodicParser<WILLRResponse> {

        @Override
        public WILLRResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new WILLRResponse(indicatorUnits, metaData);
        }

        @Override
        public WILLRResponse get(String errorMessage) {
            return new WILLRResponse(errorMessage);
        }

        @Override
        public String getIndicatorKey() {
            return "WILLR";
        }
    }
}
