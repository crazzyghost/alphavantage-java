/*
 *
 * Copyright (c) 2020 Sylvester Sefa-Yeboah
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.crazzyghost.alphavantage.technicalindicator.response.midpoint;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the midpoint ({@code MIDPOINT}), the mean of a price
 * series' highest and lowest values over a rolling time period.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 */
public class MIDPOINTResponse extends PeriodicSeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed MIDPOINT values
     * @param metaData       the parsed response metadata
     */
    private MIDPOINTResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private MIDPOINTResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link MIDPOINTResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static MIDPOINTResponse of(Map<String, Object> stringObjectMap) {
        Parser<MIDPOINTResponse> parser = new MIDPOINTResponseParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link MIDPOINTResponse}.
     */
    public static class MIDPOINTResponseParser extends PeriodicSeriesParser<MIDPOINTResponse> {

        @Override
        public MIDPOINTResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new MIDPOINTResponse(indicatorUnits, metaData);
        }

        @Override
        public MIDPOINTResponse get(String errorMessage) {
            return new MIDPOINTResponse(errorMessage);
        }

        @Override
        protected String getTechnicalIndicatorKey() {
            return "MIDPOINT";
        }
    }
}
