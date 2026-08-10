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
package com.crazzyghost.alphavantage.technicalindicator.response.minusdm;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the minus directional movement ({@code MINUS_DM}), the raw
 * downward price-movement component that {@code MINUS_DI} normalizes into
 * an indicator.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 */
public class MINUSDMResponse extends PeriodicResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed MINUS_DM values
     * @param metaData       the parsed response metadata
     */
    private MINUSDMResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private MINUSDMResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link MINUSDMResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static MINUSDMResponse of(Map<String, Object> stringObjectMap) {
        Parser<MINUSDMResponse> parser = new MINUSDMParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link MINUSDMResponse}.
     */
    public static class MINUSDMParser extends PeriodicParser<MINUSDMResponse> {

        @Override
        public MINUSDMResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new MINUSDMResponse(indicatorUnits, metaData);
        }

        @Override
        public MINUSDMResponse get(String errorMessage) {
            return new MINUSDMResponse(errorMessage);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "MINUS_DM";
        }
    }
}
