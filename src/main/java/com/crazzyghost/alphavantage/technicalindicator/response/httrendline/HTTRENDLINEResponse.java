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
package com.crazzyghost.alphavantage.technicalindicator.response.httrendline;

import com.crazzyghost.alphavantage.technicalindicator.response.SeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the Hilbert transform instantaneous trendline
 * ({@code HT_TRENDLINE}), a smoothed moving average derived from the
 * Hilbert transform that adapts faster to trend changes than a fixed-period
 * moving average.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 */
public class HTTRENDLINEResponse extends SeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed HT_TRENDLINE values
     * @param metaData       the parsed response metadata
     */
    private HTTRENDLINEResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private HTTRENDLINEResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into an {@link HTTRENDLINEResponse}.
     *
     * @param data the raw parsed JSON response
     * @return the parsed response
     */
    public static HTTRENDLINEResponse of(Map<String, Object> data) {
        Parser<HTTRENDLINEResponse> parser = new HTTRENDLINEParser();
        return parser.parse(data);
    }

    /**
     * Parser for {@link HTTRENDLINEResponse}.
     */
    public static class HTTRENDLINEParser extends SeriesParser<HTTRENDLINEResponse> {

        @Override
        public HTTRENDLINEResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new HTTRENDLINEResponse(indicatorUnits, metaData);
        }

        @Override
        public HTTRENDLINEResponse get(String error) {
            return new HTTRENDLINEResponse(error);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "HT_TRENDLINE";
        }
    }
}
