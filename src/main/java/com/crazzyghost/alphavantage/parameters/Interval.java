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
 * The {@code interval} API parameter, specifying the time interval between two
 * consecutive data points for a time series or indicator request.
 * <p>
 * Accepted by request builders across the {@code timeseries}, {@code forex},
 * {@code cryptocurrency}, {@code technicalindicator}/{@code indicator}, and
 * {@code economicindicator} packages. Not every constant is valid for every
 * request: for example, {@code TREASURY_YIELD} requests only accept
 * {@link #DAILY}, {@link #WEEKLY}, or {@link #MONTHLY}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.1
 */
public enum Interval {

    /** A one-minute interval. Serializes to the wire value {@code "1min"}. */
    ONE_MIN("1min"),

    /** A five-minute interval. Serializes to the wire value {@code "5min"}. */
    FIVE_MIN("5min") ,

    /** A fifteen-minute interval. Serializes to the wire value {@code "15min"}. */
    FIFTEEN_MIN("15min"),

    /** A thirty-minute interval. Serializes to the wire value {@code "30min"}. */
    THIRTY_MIN("30min"),

    /** A sixty-minute interval. Serializes to the wire value {@code "60min"}. */
    SIXTY_MIN("60min"),

    /** A daily interval. Serializes to the wire value {@code "daily"}. */
    DAILY("daily"),

    /** A weekly interval. Serializes to the wire value {@code "weekly"}. */
    WEEKLY("weekly"),

    /** A monthly interval. Serializes to the wire value {@code "monthly"}. */
    MONTHLY("monthly"),

    /** A quarterly interval, spanning three months. Serializes to the wire value {@code "quarterly"}. */
    QUARTERLY("quarterly"),

    /** A semiannual interval, spanning six months. Serializes to the wire value {@code "semiannual"}. */
    SEMI_ANNUAL("semiannual"),

    /** An annual interval. Serializes to the wire value {@code "annual"}. */
    ANNUAL("annual");

    private final String interval;

    Interval(String interval){
        this.interval = interval;
    }


    @Override
    public String toString() {
        return this.interval;
    }
}
