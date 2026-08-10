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
package com.crazzyghost.alphavantage.technicalindicator.response.macd;

/**
 * A single date's MACD reading — the MACD line, its signal line, and their
 * difference, the histogram.
 * <p>
 * Shared by both {@link MACDResponse} and {@link MACDEXTResponse}: the two
 * functions differ only in how each component's moving average is
 * calculated, not in the shape of the reading they report.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class MACDIndicatorUnit {

    /** The date this reading applies to. */
    private String date;

    /** The MACD histogram: the MACD line minus its signal line. */
    private double macdHistValue;

    /** The signal line: a moving average of the MACD line. */
    private double macdSignalValue;

    /** The MACD line: the difference between the fast and slow moving averages. */
    private double macdValue;

    /**
     * Creates a unit.
     *
     * @param date            the date this reading applies to
     * @param macdHistValue   the MACD histogram value
     * @param macdSignalValue the signal line value
     * @param macdValue       the MACD line value
     */
    public MACDIndicatorUnit(String date, double macdHistValue, double macdSignalValue, double macdValue) {
        this.date = date;
        this.macdHistValue = macdHistValue;
        this.macdSignalValue = macdSignalValue;
        this.macdValue = macdValue;
    }

    /**
     * Returns the date this reading applies to.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the MACD histogram value.
     *
     * @return the MACD line minus its signal line
     */
    public double getMacdHistValue() {
        return macdHistValue;
    }

    /**
     * Returns the signal line value.
     *
     * @return a moving average of the MACD line
     */
    public double getMacdSignalValue() {
        return macdSignalValue;
    }

    /**
     * Returns the MACD line value.
     *
     * @return the difference between the fast and slow moving averages
     */
    public double getMacdValue() {
        return macdValue;
    }

    @Override
    public String toString() {
        return "MACDIndicatorUnit {date=" + date + ", macdHistValue=" + macdHistValue + ", macdSignalValue="
                + macdSignalValue + ", macdValue=" + macdValue + "}";
    }

}
