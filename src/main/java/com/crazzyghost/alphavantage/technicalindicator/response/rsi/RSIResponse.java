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
package com.crazzyghost.alphavantage.technicalindicator.response.rsi;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the relative strength index ({@code RSI}), a momentum
 * oscillator that measures the speed and magnitude of recent price
 * movement on a 0-100 scale to flag overbought or oversold conditions.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 */
public class RSIResponse extends PeriodicSeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed RSI values
     * @param metaData       the parsed response metadata
     */
    private RSIResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private RSIResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into an {@link RSIResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static RSIResponse of(Map<String, Object> stringObjectMap) {
        Parser<RSIResponse> parser = new RSIResponseParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link RSIResponse}.
     */
    public static class RSIResponseParser extends PeriodicSeriesParser<RSIResponse> {

        @Override
        public RSIResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new RSIResponse(indicatorUnits, metaData);
        }

        @Override
        public RSIResponse get(String errorMessage) {
            return new RSIResponse(errorMessage);
        }

        @Override
        protected String getTechnicalIndicatorKey() {
            return "RSI";
        }
    }
}
