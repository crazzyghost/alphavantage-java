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

package com.crazzyghost.alphavantage.sector.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.parser.Parser;

/**
 * A sector performance response, holding one {@link SectorUnit} per trailing time
 * window keyed by that window's name, alongside the response metadata.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.4.0
 */
public final class SectorResponse {

    private MetaData metaData;
    private Map<String, SectorUnit> sectorUnits;
    private String errorMessage;

    private SectorResponse(MetaData metaData, Map<String, SectorUnit> sectorUnits){
        this.metaData = metaData;
        this.sectorUnits = sectorUnits;
    }

    private SectorResponse(String errorMessage){
        this.errorMessage = errorMessage;
    }

    /**
     * Turns a decoded {@code SECTOR} payload into a {@link SectorResponse}.
     *
     * @param stringObjectMap the response body, already decoded from JSON into a map
     * @return a response holding the parsed sector performance data, or one holding
     *         an error message if the payload was empty or malformed
     */
    public static SectorResponse of(Map<String, Object> stringObjectMap){
        Parser<SectorResponse> parser = new SectorParser();
        return parser.parse(stringObjectMap);
    }

    /**
     * Returns the error message the API returned, if the request failed.
     *
     * @return the error message, or {@code null} if the request succeeded
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Returns the metadata that accompanies this response.
     *
     * @return this response's metadata, or {@code null} if the request failed
     */
    public MetaData getMetaData() {
        return metaData;
    }

    /**
     * Returns sector performance measured against the most recent real-time trade.
     *
     * @return the real-time sector performance, or {@code null} if unavailable
     */
    public SectorUnit getRealTimePerformance(){
        return sectorUnits.getOrDefault("Rank A: Real-Time Performance", null);
    }

    /**
     * Returns sector performance over the trailing one trading day.
     *
     * @return the one-day sector performance, or {@code null} if unavailable
     */
    public SectorUnit getOneDayPerformance(){
        return sectorUnits.getOrDefault("Rank B: 1 Day Performance", null);
    }

    /**
     * Returns sector performance over the trailing five trading days.
     *
     * @return the five-day sector performance, or {@code null} if unavailable
     */
    public SectorUnit getFiveDayPerformance(){
        return sectorUnits.getOrDefault("Rank C: 5 Day Performance", null);
    }

    /**
     * Returns sector performance over the trailing one month.
     *
     * @return the one-month sector performance, or {@code null} if unavailable
     */
    public SectorUnit getOneMonthPerformance(){
        return sectorUnits.getOrDefault("Rank D: 1 Month Performance", null);
    }

    /**
     * Returns sector performance over the trailing three months.
     *
     * @return the three-month sector performance, or {@code null} if unavailable
     */
    public SectorUnit getThreeMonthPerformance(){
        return sectorUnits.getOrDefault("Rank E: 3 Month Performance", null);
    }

    /**
     * Returns sector performance since the start of the calendar year.
     *
     * @return the year-to-date sector performance, or {@code null} if unavailable
     */
    public SectorUnit getYearToDatePerformance(){
        return sectorUnits.getOrDefault("Rank F: Year-to-Date (YTD) Performance", null);
    }

    /**
     * Returns sector performance over the trailing one year.
     *
     * @return the one-year sector performance, or {@code null} if unavailable
     */
    public SectorUnit getOneYearPerformance(){
        return sectorUnits.getOrDefault("Rank G: 1 Year Performance", null);
    }

    /**
     * Returns sector performance over the trailing three years.
     *
     * @return the three-year sector performance, or {@code null} if unavailable
     */
    public SectorUnit getThreeYearPerformance(){
        return sectorUnits.getOrDefault("Rank H: 3 Year Performance", null);
    }

    /**
     * Returns sector performance over the trailing five years.
     *
     * @return the five-year sector performance, or {@code null} if unavailable
     */
    public SectorUnit getFiveYearPerformance(){
        return sectorUnits.getOrDefault("Rank I: 5 Year Performance", null);
    }

    /**
     * Returns sector performance over the trailing ten years.
     *
     * @return the ten-year sector performance, or {@code null} if unavailable
     */
    public SectorUnit getTenYearPerformance(){
        return sectorUnits.getOrDefault("Rank J: 10 Year Performance", null);
    }



    /**
     * Turns a decoded {@code SECTOR} payload into a {@link SectorResponse}.
     * <p>
     * The payload's first key holds the metadata; every key after that names a
     * trailing time window and holds that window's sector performance. An error
     * payload carries a plain message string in place of the metadata object, so
     * the failed cast is itself the signal to read the value as an error message.
     */
    public static class SectorParser extends Parser<SectorResponse> {

        /**
         * Reads the metadata and per-window sector performance into a response.
         *
         * @param stringObjectMap the response body, already decoded from JSON into a map
         * @return a response holding the parsed sector performance data, or one holding
         *         an error message if the payload was empty or carried a message in
         *         place of the metadata object
         */
        @SuppressWarnings("unchecked")
        @Override
        public SectorResponse parse(Map<String, Object> stringObjectMap) {
            List<String> keys = new ArrayList<>(stringObjectMap.keySet());

            if (keys.isEmpty()) {
                return onParseError("Empty JSON returned by the API, the symbol might not be supported.");
            } else {

                try {
                    Map<String, String> metaDataMap = (Map<String, String>) stringObjectMap.get(keys.get(0));

                    MetaData metaData = new MetaData(
                            String.valueOf(metaDataMap.get("Information")),
                            String.valueOf(metaDataMap.get("Last Refreshed"))
                    );

                    keys.remove(0);

                    Map<String, SectorUnit> sectorUnits = new HashMap<>();
                    for (String sectorDescription : keys) {
                        Map<String, String> sectorData = (Map<String, String>) stringObjectMap.get(sectorDescription);
                        SectorUnit sectorUnit = new SectorUnit(
                                sectorData.get("Information Technology"),
                                sectorData.get("Consumer Discretionary"),
                                sectorData.get("Health Care"),
                                sectorData.get("Communication Services"),
                                sectorData.get("Real Estate"),
                                sectorData.get("Utilities"),
                                sectorData.get("Financials"),
                                sectorData.get("Materials"),
                                sectorData.get("Industrials"),
                                sectorData.get("Consumer Staples"),
                                sectorData.get("Energy")
                        );
                        sectorUnits.put(sectorDescription, sectorUnit);
                    }

                    return new SectorResponse(metaData, sectorUnits);

                } catch (ClassCastException e) {
                    return onParseError(stringObjectMap.get(keys.get(0)).toString());
                }
            }

        }

        /**
         * Wraps a parse failure or an API error message in a response.
         *
         * @param error the message describing what went wrong
         * @return a response carrying the message, with no sector performance data set
         */
        @Override
        public SectorResponse onParseError(String error) {
            return new SectorResponse(error);
        }

    }

    /**
     * The metadata that accompanies a {@link SectorResponse}.
     */
    public static final class MetaData {

        private String information;
        private String lastRefreshed;

        /**
         * Creates the metadata for a sector performance response.
         *
         * @param information a description of the data returned
         * @param lastRefreshed the timestamp at which the sector data was last refreshed
         */
        public MetaData(String information, String lastRefreshed) {
            this.information = information;
            this.lastRefreshed = lastRefreshed;
        }

        /**
         * Returns the description of the data returned.
         *
         * @return a description of the data returned
         */
        public String getInformation() {
            return information;
        }

        /**
         * Returns the timestamp at which the sector data was last refreshed.
         *
         * @return the timestamp at which the sector data was last refreshed
         */
        public String getLastRefreshed() {
            return lastRefreshed;
        }

        @Override
        public String toString() {
            return "MetaData {information=" + information + ", lastRefreshed=" + lastRefreshed + "}";
        }

    }

    @Override
    public String toString() {
        return "SectorResponse {errorMessage=" + errorMessage + ", metaData=" + metaData + ", sectorUnits="
                + sectorUnits + "}";
    }
}
