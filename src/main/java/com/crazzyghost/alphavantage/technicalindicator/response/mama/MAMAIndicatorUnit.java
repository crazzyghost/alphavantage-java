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
package com.crazzyghost.alphavantage.technicalindicator.response.mama;

/**
 * A single date's MESA adaptive moving average reading — the MAMA value and
 * its slower-following companion, FAMA.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class MAMAIndicatorUnit {

    /** The date this reading applies to. */
    private String date;

    /** The following adaptive moving average (FAMA), which trails MAMA to help confirm crossovers. */
    private double famaValue;

    /** The MESA adaptive moving average (MAMA) value. */
    private double mamaValue;

    /**
     * Creates a unit.
     *
     * @param date the date this reading applies to
     * @param fama the FAMA value
     * @param mama the MAMA value
     */
    public MAMAIndicatorUnit(String date, double fama, double mama) {
        this.date = date;
        this.famaValue = fama;
        this.mamaValue = mama;
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
     * Returns the FAMA value.
     *
     * @return the following adaptive moving average
     */
    public double getFamaValue() {
        return famaValue;
    }

    /**
     * Returns the MAMA value.
     *
     * @return the MESA adaptive moving average
     */
    public double getMamaValue() {
        return mamaValue;
    }

    @Override
    public String toString() {
        return "MAMAIndicatorUnit {date=" + date + ", famaValue=" + famaValue + ", mamaValue=" + mamaValue + "}";
    }

}
