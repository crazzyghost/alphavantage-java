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
package com.crazzyghost.alphavantage.technicalindicator.response.trima;

import com.crazzyghost.alphavantage.technicalindicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the triangular moving average ({@code TRIMA}), a moving
 * average of a price series that double-smooths by averaging an SMA of an
 * SMA, weighting middle values in the period most heavily.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.5.0
 */
public class TRIMAResponse extends PeriodicSeriesResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed TRIMA values
     * @param metaData       the parsed response metadata
     */
    private TRIMAResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private TRIMAResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link TRIMAResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static TRIMAResponse of(Map<String, Object> stringObjectMap) {
        Parser<TRIMAResponse> parser = new TRIMAResponseParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link TRIMAResponse}.
     */
    public static class TRIMAResponseParser extends PeriodicSeriesParser<TRIMAResponse> {

        @Override
        public TRIMAResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new TRIMAResponse(indicatorUnits, metaData);
        }

        @Override
        public TRIMAResponse get(String errorMessage) {
            return new TRIMAResponse(errorMessage);
        }

        @Override
        protected String getTechnicalIndicatorKey() {
            return "TRIMA";
        }
    }
}
