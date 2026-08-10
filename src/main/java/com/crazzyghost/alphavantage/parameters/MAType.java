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
 * The {@code matype} API parameter, selecting the moving-average calculation an
 * indicator applies (for example, the moving average used for a Bollinger
 * Bands' middle band).
 * <p>
 * Each constant serializes to Alpha Vantage's numeric moving-average-type code
 * as a string, not to its Java name: {@link #toString()} is overridden to
 * return the numeric code. Accepted by request builders in the
 * {@code technicalindicator}/{@code indicator} packages that take one or more
 * moving-average-type parameters, such as {@code BBANDSRequest},
 * {@code MACDEXTRequest}, {@code STOCHRequest}, {@code STOCHFRequest},
 * {@code STOCHRSIRequest}, and {@code PriceOscillatorRequest}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public enum MAType {

    /** Simple moving average. Serializes to the numeric wire code {@code "0"}. */
    SMA(0),

    /** Exponential moving average. Serializes to the numeric wire code {@code "1"}. */
    EMA(1),

    /** Weighted moving average. Serializes to the numeric wire code {@code "2"}. */
    WMA(2),

    /** Double exponential moving average. Serializes to the numeric wire code {@code "3"}. */
    DEMA(3),

    /** Triple exponential moving average. Serializes to the numeric wire code {@code "4"}. */
    TEMA(4),

    /** Triangular moving average. Serializes to the numeric wire code {@code "5"}. */
    TRIMA(5),

    /** T3, Tillson's triple exponential moving average. Serializes to the numeric wire code {@code "6"}. */
    T3(6),

    /** Kaufman adaptive moving average. Serializes to the numeric wire code {@code "7"}. */
    KAMA(7),

    /** MESA adaptive moving average. Serializes to the numeric wire code {@code "8"}. */
    MAMA(8);

    private final int type;

    MAType(int type){
        this.type = type;
    }

    @Override
    public String toString() {
        return "" + this.type;
    }

}
