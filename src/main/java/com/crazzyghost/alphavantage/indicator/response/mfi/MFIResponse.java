package com.crazzyghost.alphavantage.indicator.response.mfi;

import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the money flow index ({@code MFI}), a volume-weighted
 * version of RSI that measures buying and selling pressure using both price
 * and traded volume over a rolling time period.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.mfi.MFIResponse}
 */
@Deprecated
public class MFIResponse extends PeriodicResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed MFI values
     * @param metaData       the parsed response metadata
     */
    private MFIResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private MFIResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into an {@link MFIResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static MFIResponse of(Map<String, Object> stringObjectMap){
        Parser<MFIResponse> parser = new MFIParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link MFIResponse}.
     */
    public static class MFIParser extends PeriodicParser<MFIResponse> {

        @Override
        public MFIResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new MFIResponse(indicatorUnits, metaData);
        }

        @Override
        public MFIResponse get(String errorMessage) {
            return new MFIResponse(errorMessage);
        }

        @Override
        public String getIndicatorKey() {
            return "MFI";
        }
    }
}
