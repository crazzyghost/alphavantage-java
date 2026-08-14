/*
 *
 * Copyright (c) 2026 Sylvester Sefa-Yeboah
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
 * The {@code entitlement} API parameter, controlling data freshness for premium
 * Alpha Vantage plans. Gates access to realtime versus fifteen-minute-delayed data.
 * <p>
 * Accepted by {@code timeseries} requests ({@code TIME_SERIES_INTRADAY},
 * {@code TIME_SERIES_DAILY_ADJUSTED}, {@code GLOBAL_QUOTE}) and all technical
 * indicator requests. Requires a premium API key.
 * <p>
 * When unset, the parameter is omitted from the request entirely, preserving
 * backward compatibility with existing calls.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.9.0
 */
public enum Entitlement {

    /**
     * Realtime data. Requires a premium Alpha Vantage plan.
     * Serializes to the wire value {@code "realtime"}.
     */
    REALTIME("realtime"),

    /**
     * Fifteen-minute delayed data. Available on premium Alpha Vantage plans.
     * Serializes to the wire value {@code "delayed"}.
     */
    DELAYED("delayed");

    private final String entitlement;

    Entitlement(String entitlement) {
        this.entitlement = entitlement;
    }

    /**
     * Returns the wire value sent as the {@code entitlement} query parameter.
     *
     * @return the lowercase wire value
     */
    @Override
    public String toString() {
        return entitlement;
    }
}
