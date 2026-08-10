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
package com.crazzyghost.alphavantage.technicalindicator.response.htdcphase;

import com.crazzyghost.alphavantage.technicalindicator.response.SeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the Hilbert transform dominant cycle phase
 * ({@code HT_DCPHASE}), which reports the price series' position, in
 * degrees, within its current dominant market cycle.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 */
public class HTDCPHASEResponse extends SeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed HT_DCPHASE values
     * @param metaData       the parsed response metadata
     */
    private HTDCPHASEResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private HTDCPHASEResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into an {@link HTDCPHASEResponse}.
     *
     * @param data the raw parsed JSON response
     * @return the parsed response
     */
    public static HTDCPHASEResponse of(Map<String, Object> data) {
        Parser<HTDCPHASEResponse> parser = new HTDCPHASEParser();
        return parser.parse(data);
    }

    /**
     * Parser for {@link HTDCPHASEResponse}.
     */
    public static class HTDCPHASEParser extends SeriesParser<HTDCPHASEResponse> {

        @Override
        public HTDCPHASEResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new HTDCPHASEResponse(indicatorUnits, metaData);
        }

        @Override
        public HTDCPHASEResponse get(String error) {
            return new HTDCPHASEResponse(error);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "HT_DCPHASE";
        }
    }
}
