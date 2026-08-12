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
package com.crazzyghost.alphavantage.economicindicator.response;

import com.crazzyghost.alphavantage.parser.Parser;
import com.squareup.moshi.Json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * An economic indicator series: the indicator's name, the interval and unit its
 * values are reported in, and the observations themselves as a list of
 * {@link EconomicIndicatorUnit}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 */
public class EconomicIndicatorResponse {
    @Json(name = "name")
    private String name;
    @Json(name = "interval")
    private String interval;
    @Json(name = "unit")
    private String unit;
    @Json(name = "data")
    private List<EconomicIndicatorUnit> data;
    private final String errorMessage;

    private EconomicIndicatorResponse(String name, String interval, String unit, List<EconomicIndicatorUnit> data) {
        this.name = name;
        this.interval = interval;
        this.unit = unit;
        this.data = data;
        this.errorMessage = null;
    }

    private EconomicIndicatorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the name of this economic indicator, for example {@code "Real Gross Domestic Product"}.
     *
     * @return the indicator's display name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the interval this series is reported at, for example {@code "quarterly"}.
     *
     * @return the reporting interval
     */
    public String getInterval() {
        return interval;
    }

    /**
     * Gets the unit this series' values are reported in, for example {@code "billions of dollars"}.
     *
     * @return the value unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Gets the observations in this series, ordered as returned by the API.
     *
     * @return the list of date/value observations
     */
    public List<EconomicIndicatorUnit> getData() {
        return data;
    }

    /**
     * Gets the error message returned by the API, if the request failed.
     *
     * @return the error message, or {@code null} if the request succeeded
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Parses a raw JSON map into an {@link EconomicIndicatorResponse}.
     *
     * @param stringObjectMap the decoded JSON response body
     * @return the parsed response, or an error response if parsing fails
     */
    public static EconomicIndicatorResponse of(Map<String, Object> stringObjectMap) {
        Parser<EconomicIndicatorResponse> parser = new EconomicIndicatorResponse.EconomicIndicatorParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Reads an {@link EconomicIndicatorResponse} out of a decoded JSON map.
     */
    public static class EconomicIndicatorParser extends Parser<EconomicIndicatorResponse> {

        /**
         * Wraps a parse failure in an {@link EconomicIndicatorResponse} carrying the
         * error message instead of throwing.
         *
         * @param error the error message to carry
         * @return a response whose {@link #getErrorMessage()} returns {@code error}
         */
        @Override
        public EconomicIndicatorResponse onParseError(String error) {
            return new EconomicIndicatorResponse(error);
        }

        /**
         * Reads the indicator's name, interval, unit, and observations out of a
         * decoded JSON map.
         *
         * @param data the decoded JSON response body
         * @return the parsed response, or an error response if the map does not
         *         match the expected shape
         */
        @Override
        @SuppressWarnings("unchecked")
        public EconomicIndicatorResponse parse(Map<String, Object> data) {
            List<String> keys = new ArrayList<>(data.keySet());
            try {
                if (keys.isEmpty()) {
                    return onParseError("Empty JSON response returned by the API.");
                }

                if (Objects.nonNull(data.getOrDefault("Information", null))) {
                    throw new ClassCastException();
                }

                String name = String.valueOf(data.getOrDefault("name", ""));
                String interval = String.valueOf(data.getOrDefault("interval", ""));;
                String unit = String.valueOf(data.getOrDefault("unit", ""));;
                List<EconomicIndicatorUnit> unitList = Parser.parseJSONList(data.get("data"),
                        EconomicIndicatorUnit.class);
                return new EconomicIndicatorResponse(name, interval, unit, unitList);
            }catch (ClassCastException | IndexOutOfBoundsException e) {
                return onParseError(data.get(keys.get(0)).toString());
            }
        }
    }
}
