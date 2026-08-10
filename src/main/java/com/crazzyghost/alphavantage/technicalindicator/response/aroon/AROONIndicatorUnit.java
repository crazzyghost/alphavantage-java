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
package com.crazzyghost.alphavantage.technicalindicator.response.aroon;

/**
 * A single date's Aroon reading — the Aroon-Up and Aroon-Down lines, each
 * measuring the number of periods (as a percentage of the look-back window)
 * since the most recent high or low.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class AROONIndicatorUnit {

    /** The date this reading applies to. */
    private String date;

    /** The Aroon-Up value: how recently price made a new high, as a percentage of the time period. */
    private double aroonUp;

    /** The Aroon-Down value: how recently price made a new low, as a percentage of the time period. */
    private double aroonDown;

    /**
     * Creates a unit.
     *
     * @param date      the date this reading applies to
     * @param aroonUp   the Aroon-Up value
     * @param aroonDown the Aroon-Down value
     */
    public AROONIndicatorUnit(String date, double aroonUp, double aroonDown) {
        this.date = date;
        this.aroonUp = aroonUp;
        this.aroonDown = aroonDown;
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
     * Returns the Aroon-Up value.
     *
     * @return how recently price made a new high, as a percentage of the time period
     */
    public double getAroonUpValue() {
        return aroonUp;
    }

    /**
     * Returns the Aroon-Down value.
     *
     * @return how recently price made a new low, as a percentage of the time period
     */
    public double getAroonDownValue() {
        return aroonDown;
    }

    @Override
    public String toString() {
        return "AROONTechnicalIndicatorUnit {date=" + date + ", aroonUp=" + aroonUp + ", aroonDown=" + aroonDown + "}";
    }

}
