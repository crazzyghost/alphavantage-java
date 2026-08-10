package com.crazzyghost.alphavantage.indicator.response.minusdi;

import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the minus directional indicator ({@code MINUS_DI}), which
 * measures downward price movement pressure and, together with
 * {@code PLUS_DI}, forms the basis of {@code ADX}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.minusdi.MINUSDIResponse}
 */
@Deprecated
public class MINUSDIResponse extends PeriodicResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed MINUS_DI values
     * @param metaData       the parsed response metadata
     */
    private MINUSDIResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private MINUSDIResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link MINUSDIResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static MINUSDIResponse of(Map<String, Object> stringObjectMap){
        Parser<MINUSDIResponse> parser = new MINUSDIParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link MINUSDIResponse}.
     */
    public static class MINUSDIParser extends PeriodicParser<MINUSDIResponse> {

        @Override
        public MINUSDIResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new MINUSDIResponse(indicatorUnits, metaData);
        }

        @Override
        public MINUSDIResponse get(String errorMessage) {
            return new MINUSDIResponse(errorMessage);
        }

        @Override
        public String getIndicatorKey() {
            return "MINUS_DI";
        }
    }
}
