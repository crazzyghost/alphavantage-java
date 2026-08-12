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
package com.crazzyghost.alphavantage.technicalindicator.response;

/**
 * A single date/value pair for any indicator that reports one numeric value
 * per date — every family built on {@link PeriodicResponse}, {@link
 * PeriodicSeriesResponse}, {@link SeriesResponse}, {@link
 * SimpleTechnicalIndicatorResponse}, and {@link PriceOscillatorResponse}
 * reuses this class rather than defining its own unit type, unlike
 * multi-field indicators such as {@code BBANDS} or {@code STOCH}, which
 * define their own {@code *IndicatorUnit} classes.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class SimpleTechnicalIndicatorUnit {

    /** The date this value applies to. */
    String date;

    /** The indicator's value on {@link #date}. */
    Double value;

    /** The JSON key this value was parsed from, used only to identify the indicator in {@link #toString()}. */
    String indicatorKey;

    /**
     * Creates a unit without an indicator key.
     *
     * @param date  the date this value applies to
     * @param value the indicator's value on that date
     */
    public SimpleTechnicalIndicatorUnit(String date, Double value) {
        this.date = date;
        this.value = value;
    }

    /**
     * Creates a unit.
     *
     * @param date         the date this value applies to
     * @param value        the indicator's value on that date
     * @param indicatorKey the JSON key this value was parsed from
     */
    public SimpleTechnicalIndicatorUnit(String date, Double value, String indicatorKey) {
        this(date, value);
        this.indicatorKey = indicatorKey;
    }

    /**
     * Returns the date this value applies to.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the indicator's value on this unit's date.
     *
     * @return the value
     */
    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        String key = indicatorKey == null ? "SimpleTechnicalIndicator" : indicatorKey;
        return key + "Unit {date=" + date + ", value=" + value + "}";
    }

}
