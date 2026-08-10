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
package com.crazzyghost.alphavantage.technicalindicator.response.stoch;

/**
 * A single date's stochastic oscillator reading — the smoothed %K and %D
 * lines.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class STOCHIndicatorUnit {

    /** The date this reading applies to. */
    private String date;

    /** The slow %K line: the raw %K smoothed by {@code slowKPeriod}. */
    private double slowKValue;

    /** The slow %D line: slow %K smoothed further by {@code slowDPeriod}. */
    private double slowDValue;

    /**
     * Creates a unit.
     *
     * @param date  the date this reading applies to
     * @param slowK the slow %K value
     * @param slowD the slow %D value
     */
    public STOCHIndicatorUnit(String date, double slowK, double slowD) {
        this.date = date;
        this.slowKValue = slowK;
        this.slowDValue = slowD;
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
     * Returns the slow %K value.
     *
     * @return the raw %K smoothed by the slow %K period
     */
    public double getSlowKValue() {
        return slowKValue;
    }

    /**
     * Returns the slow %D value.
     *
     * @return the slow %K smoothed further by the slow %D period
     */
    public double getSlowDValue() {
        return slowDValue;
    }

    @Override
    public String toString() {
        return "STOCHIndicatorUnit {date=" + date + ", slowKValue=" + slowKValue + ", slowDValue=" + slowDValue + "}";
    }

}
