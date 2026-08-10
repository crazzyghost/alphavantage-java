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
package com.crazzyghost.alphavantage.technicalindicator.response.stochf;

/**
 * A single date's stochastic fast reading — the unsmoothed %K and lightly
 * smoothed %D lines.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class STOCHFIndicatorUnit {

    /** The date this reading applies to. */
    private String date;

    /** The fast %K line: the raw stochastic value, unsmoothed. */
    private double fastKValue;

    /** The fast %D line: fast %K smoothed by {@code fastDPeriod}. */
    private double fastDValue;

    /**
     * Creates a unit.
     *
     * @param date  the date this reading applies to
     * @param fastK the fast %K value
     * @param fastD the fast %D value
     */
    public STOCHFIndicatorUnit(String date, double fastK, double fastD) {
        this.date = date;
        this.fastKValue = fastK;
        this.fastDValue = fastD;
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
     * Returns the fast %K value.
     *
     * @return the raw stochastic value, unsmoothed
     */
    public double getFastKValue() {
        return fastKValue;
    }

    /**
     * Returns the fast %D value.
     *
     * @return the fast %K smoothed by the fast %D period
     */
    public double getFastDValue() {
        return fastDValue;
    }

    @Override
    public String toString() {
        return "STOCHFIndicatorUnit {date=" + date + ", fastKValue=" + fastKValue + ", fastDValue=" + fastDValue + "}";
    }

}
