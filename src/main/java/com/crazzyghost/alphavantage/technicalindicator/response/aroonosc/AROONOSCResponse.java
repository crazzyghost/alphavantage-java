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
package com.crazzyghost.alphavantage.technicalindicator.response.aroonosc;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the Aroon oscillator ({@code AROONOSC}), the difference
 * between the Aroon-Up and Aroon-Down lines, condensing the two-line
 * {@code AROON} indicator into a single trend-direction reading.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 */
public class AROONOSCResponse extends PeriodicResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed AROONOSC values
     * @param metaData       the parsed response metadata
     */
    private AROONOSCResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private AROONOSCResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into an {@link AROONOSCResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static AROONOSCResponse of(Map<String, Object> stringObjectMap) {
        Parser<AROONOSCResponse> parser = new AROONOSCParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link AROONOSCResponse}.
     */
    public static class AROONOSCParser extends PeriodicParser<AROONOSCResponse> {

        @Override
        public AROONOSCResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new AROONOSCResponse(indicatorUnits, metaData);
        }

        @Override
        public AROONOSCResponse get(String errorMessage) {
            return new AROONOSCResponse(errorMessage);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "AROONOSC";
        }
    }
}
