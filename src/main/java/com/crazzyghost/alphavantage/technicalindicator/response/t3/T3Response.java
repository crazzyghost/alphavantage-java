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
package com.crazzyghost.alphavantage.technicalindicator.response.t3;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for T3, Tillson's triple exponential moving average
 * ({@code T3}), a smoothed moving average built from a chain of six EMAs
 * that responds to trend changes faster than a plain triple EMA.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 */
public class T3Response extends PeriodicSeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed T3 values
     * @param metaData       the parsed response metadata
     */
    private T3Response(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private T3Response(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link T3Response}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static T3Response of(Map<String, Object> stringObjectMap) {
        Parser<T3Response> parser = new T3ResponseParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link T3Response}.
     */
    public static class T3ResponseParser extends PeriodicSeriesParser<T3Response> {

        @Override
        public T3Response get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new T3Response(indicatorUnits, metaData);
        }

        @Override
        public T3Response get(String errorMessage) {
            return new T3Response(errorMessage);
        }

        @Override
        protected String getTechnicalIndicatorKey() {
            return "T3";
        }
    }
}
