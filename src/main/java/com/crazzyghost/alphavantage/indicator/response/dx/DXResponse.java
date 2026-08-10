package com.crazzyghost.alphavantage.indicator.response.dx;

import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the directional movement index ({@code DX}), the normalized
 * difference between the plus and minus directional indicators that
 * {@code ADX} smooths into a trend-strength reading.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.dx.DXResponse}
 */
@Deprecated
public class DXResponse extends PeriodicResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed DX values
     * @param metaData       the parsed response metadata
     */
    private DXResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private DXResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link DXResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static DXResponse of(Map<String, Object> stringObjectMap){
        Parser<DXResponse> parser = new DXParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link DXResponse}.
     */
    public static class DXParser extends PeriodicParser<DXResponse> {

        @Override
        public DXResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new DXResponse(indicatorUnits, metaData);
        }

        @Override
        public DXResponse get(String errorMessage) {
            return new DXResponse(errorMessage);
        }

        @Override
        public String getIndicatorKey() {
            return "DX";
        }
    }
}
