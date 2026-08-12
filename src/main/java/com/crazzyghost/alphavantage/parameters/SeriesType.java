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
package com.crazzyghost.alphavantage.parameters;

/**
 * The {@code series_type} API parameter, selecting which price field of a time
 * series an indicator is calculated against.
 * <p>
 * Accepted by request builders in the {@code technicalindicator}/
 * {@code indicator} packages whose indicators are computed from a single price
 * series, such as {@code MACDRequest}, {@code MAMARequest}, and
 * {@code BBANDSRequest}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public enum SeriesType {

    /** The opening price. Serializes to the wire value {@code "open"}. */
    OPEN("open"),

    /** The highest price. Serializes to the wire value {@code "high"}. */
    HIGH("high"),

    /** The closing price. Serializes to the wire value {@code "close"}. */
    CLOSE("close"),

    /** The lowest price. Serializes to the wire value {@code "low"}. */
    LOW("low");

    private final String seriesType;

    SeriesType(String seriesType){
        this.seriesType = seriesType;
    }

    @Override
    public String toString() {
        return seriesType;
    }
}
