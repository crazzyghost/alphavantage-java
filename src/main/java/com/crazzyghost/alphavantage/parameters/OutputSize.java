/*
 *
 * Copyright (c) 2018 Sylvester Sefa-Yeboah
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
 * The {@code outputsize} API parameter, controlling how much historical data a
 * time-series request returns.
 * <p>
 * Accepted by request builders across the {@code timeseries}, {@code forex},
 * and {@code cryptocurrency} packages.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.1
 */
public enum OutputSize {

    /**
     * The 100 most recent data points. Serializes to the wire value
     * {@code "compact"}.
     */
    COMPACT("compact"),

    /**
     * The full-length time series, which can span 20+ years of historical
     * data. Serializes to the wire value {@code "full"}.
     */
    FULL("full");

    private final String outputSize;

    OutputSize(String outputSize){
        this.outputSize = outputSize;
    }

    @Override
    public String toString() {
        return this.outputSize;
    }
}
