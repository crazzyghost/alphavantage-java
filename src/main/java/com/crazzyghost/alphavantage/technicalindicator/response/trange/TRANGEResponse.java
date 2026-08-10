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
package com.crazzyghost.alphavantage.technicalindicator.response.trange;

import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.Parser;

import java.util.List;
import java.util.Map;

/**
 * Response for the true range ({@code TRANGE}), the greatest of a period's
 * high-low range, its high versus the prior close, and its low versus the
 * prior close — the single-period building block {@code ATR} smooths.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 */
public class TRANGEResponse extends SimpleTechnicalIndicatorResponse {

    /**
     * Creates a successful response.
     *
     * @param indicatorUnits the parsed TRANGE values
     * @param metaData       the parsed response metadata
     */
    private TRANGEResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        super(indicatorUnits, metaData);
    }

    /**
     * Creates a failed response.
     *
     * @param errorMessage the API's error message
     */
    private TRANGEResponse(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Parses a raw API response into a {@link TRANGEResponse}.
     *
     * @param stringObjectMap the raw parsed JSON response
     * @return the parsed response
     */
    public static SimpleTechnicalIndicatorResponse of(Map<String, Object> stringObjectMap) {
        Parser<TRANGEResponse> parser = new TRANGEParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Parser for {@link TRANGEResponse}.
     */
    public static class TRANGEParser extends SimpleTechnicalIndicatorParser<TRANGEResponse> {

        @Override
        public TRANGEResponse get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
            return new TRANGEResponse(indicatorUnits, metaData);
        }

        @Override
        public TRANGEResponse get(String error) {
            return new TRANGEResponse(error);
        }

        @Override
        public String getTechnicalIndicatorKey() {
            return "TRANGE";
        }
    }
}
