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
package com.crazzyghost.alphavantage.technicalindicator.response.willr;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for Williams' %R ({@code WILLR}), a momentum oscillator that
 * compares a period's close to its recent high-low range, similar to
 * {@code STOCH}'s %K but scaled and inverted.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 */
public class WILLRResponse extends PeriodicResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed WILLR values
     * @param metaData       the parsed response metadata
     */
    private WILLRResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private WILLRResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link WILLRResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static WILLRResponse of(Map<String, Object> stringObjectMap) {
        Parser<WILLRResponse> parser = new WILLRParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link WILLRResponse}.
     */
    public static class WILLRParser extends PeriodicParser<WILLRResponse> {

        @Override
        public WILLRResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new WILLRResponse(indicatorUnits, metaData);
        }

        @Override
        public WILLRResponse get(String errorMessage) {
            return new WILLRResponse(errorMessage);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "WILLR";
        }
    }
}
