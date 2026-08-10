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
package com.crazzyghost.alphavantage.technicalindicator.response.htphasor;

/**
 * A single date's Hilbert transform phasor reading — the phase and
 * quadrature components the Hilbert transform decomposes the price series
 * into.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public class HTPHASORIndicatorUnit {

    /** The date this reading applies to. */
    private String date;

    /** The in-phase ({@code PHASE}) component of the Hilbert transform. */
    private double phaseValue;

    /** The quadrature ({@code QUADRATURE}) component of the Hilbert transform. */
    private double quadratureValue;

    /**
     * Creates a unit.
     *
     * @param date  the date this reading applies to
     * @param leadSine the in-phase ({@code PHASE}) component
     * @param sine     the quadrature ({@code QUADRATURE}) component
     */
    public HTPHASORIndicatorUnit(String date, double leadSine, double sine) {
        this.date = date;
        this.phaseValue = leadSine;
        this.quadratureValue = sine;
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
     * Returns the in-phase component of the Hilbert transform.
     *
     * @return the phase value
     */
    public double getPhaseValue() {
        return phaseValue;
    }

    /**
     * Returns the quadrature component of the Hilbert transform.
     *
     * @return the quadrature value
     */
    public double getQuadratureValue() {
        return quadratureValue;
    }

    @Override
    public String toString() {
        return "HTPHASORIndicatorUnit {date=" + date + ", phaseValue=" + phaseValue + ", quadratureValue="
                + quadratureValue
                + "}";
    }

}
