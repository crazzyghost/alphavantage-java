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
package com.crazzyghost.alphavantage.technicalindicator.response.ppo;

import com.crazzyghost.alphavantage.technicalindicator.response.PriceOscillatorResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.technicalindicator.response.roc.ROCResponse;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the percentage price oscillator ({@code PPO}), the
 * difference between a fast and slow moving average of a price series
 * expressed as a percentage of the slow average, making it comparable
 * across instruments at different price levels unlike {@code APO}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 */
public class PPOResponse extends PriceOscillatorResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed PPO values
     * @param metaData       the parsed response metadata
     */
    private PPOResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Parses a raw API response into a {@link PPOResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static PPOResponse of(Map<String, Object> stringObjectMap) {
        Parser<PPOResponse> parser = new PPOParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private PPOResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parser for {@link PPOResponse}.
     */
    public static class PPOParser extends PriceOscillatorParser<PPOResponse> {
        @Override
        public PPOResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new PPOResponse(indicatorUnits, metaData);
        }

        @Override
        public PPOResponse get(String error) {
            return new PPOResponse(error);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "PPO";
        }
    }
}
