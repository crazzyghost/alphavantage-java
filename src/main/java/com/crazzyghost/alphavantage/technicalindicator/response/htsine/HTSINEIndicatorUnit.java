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
package com.crazzyghost.alphavantage.technicalindicator.response.htsine;

/**
 * A single date's Hilbert transform sine wave reading — the Sine and Lead
 * Sine lines used to spot cycle turning points.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class HTSINEIndicatorUnit {

    /** The date this reading applies to. */
    private String date;

    /** The Lead Sine line: the Sine line advanced to anticipate the next cycle turn. */
    private double leadSineValue;

    /** The Sine line: the sine of the price series' dominant cycle phase. */
    private double sineValue;

    /**
     * Creates a unit.
     *
     * @param date     the date this reading applies to
     * @param leadSine the Lead Sine value
     * @param sine     the Sine value
     */
    public HTSINEIndicatorUnit(String date, double leadSine, double sine) {
        this.date = date;
        this.leadSineValue = leadSine;
        this.sineValue = sine;
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
     * Returns the Lead Sine value.
     *
     * @return the Sine line advanced to anticipate the next cycle turn
     */
    public double getLeadSineValue() {
        return leadSineValue;
    }

    /**
     * Returns the Sine value.
     *
     * @return the sine of the price series' dominant cycle phase
     */
    public double getSineValue() {
        return sineValue;
    }

    @Override
    public String toString() {
        return "HTSINEIndicatorUnit {date=" + date + ", leadSineValue=" + leadSineValue + ", sineValue=" + sineValue
                + "}";
    }

}
