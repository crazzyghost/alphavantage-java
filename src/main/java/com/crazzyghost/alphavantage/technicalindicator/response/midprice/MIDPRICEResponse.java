package com.crazzyghost.alphavantage.technicalindicator.response.midprice;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

public class MIDPRICEResponse extends PeriodicResponse {

    private MIDPRICEResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    private MIDPRICEResponse(String errorMessage) {
        super(errorMessage);
    }

    public static MIDPRICEResponse of(Map<String, Object> stringObjectMap) {
        Parser<MIDPRICEResponse> parser = new MIDPRICEParser();
        return parser.parse(stringObjectMap);
    }

    public static class MIDPRICEParser extends PeriodicParser<MIDPRICEResponse> {

        @Override
        public MIDPRICEResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new MIDPRICEResponse(indicatorUnits, metaData);
        }

        @Override
        public MIDPRICEResponse get(String errorMessage) {
            return new MIDPRICEResponse(errorMessage);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "MIDPRICE";
        }
    }
}
