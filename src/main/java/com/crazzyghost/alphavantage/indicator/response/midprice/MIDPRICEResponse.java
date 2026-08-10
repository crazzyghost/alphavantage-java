package com.crazzyghost.alphavantage.indicator.response.midprice;

import com.crazzyghost.alphavantage.indicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the midprice ({@code MIDPRICE}), the mean of an
 * instrument's highest high and lowest low over a rolling time period.
 * <p>
 * Unlike {@code MIDPOINT}, which is computed from a single chosen price
 * series, {@code MIDPRICE} is always computed from the instrument's high
 * and low, so it has no {@code series_type} parameter.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.midprice.MIDPRICEResponse}
 */
@Deprecated
public class MIDPRICEResponse extends PeriodicResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed MIDPRICE values
     * @param metaData       the parsed response metadata
     */
    private MIDPRICEResponse(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private MIDPRICEResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link MIDPRICEResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static MIDPRICEResponse of(Map<String, Object> stringObjectMap){
        Parser<MIDPRICEResponse> parser = new MIDPRICEParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link MIDPRICEResponse}.
     */
    public static class MIDPRICEParser extends PeriodicParser<MIDPRICEResponse> {

        @Override
        public MIDPRICEResponse get(List<SimpleIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new MIDPRICEResponse(indicatorUnits, metaData);
        }

        @Override
        public MIDPRICEResponse get(String errorMessage) {
            return new MIDPRICEResponse(errorMessage);
        }

        @Override
        public String getIndicatorKey() {
            return "MIDPRICE";
        }
    }
}
