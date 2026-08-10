/*
 *
 * Copyright (c) 2025 Sylvester Sefa-Yeboah
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
package com.crazzyghost.alphavantage.timeseries.request;

import com.crazzyghost.alphavantage.parameters.Function;

/**
 * A request to {@code REALTIME_BULK_QUOTES}, which returns a current snapshot for many
 * tickers in a single call, up to a hundred of them.
 * <p>
 * It answers the same question as {@link QuoteRequest} — what is this trading at now —
 * but amortises one round trip and one rate-limit charge across a whole watchlist
 * instead of paying both per ticker. It is a premium endpoint, and its snapshots carry
 * extended-hours figures that {@code GLOBAL_QUOTE} does not.
 * <p>
 * Because the endpoint takes its tickers as one comma-joined parameter,
 * {@link Builder#forSymbol(String)} accumulates rather than replaces — the one place in
 * this package where calling a setter twice keeps both values. See that method for what
 * this means for callers used to the inherited behaviour.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.8.0
 */
public class RealtimeBulkQuoteRequest extends TimeSeriesRequest {
    private RealtimeBulkQuoteRequest(Builder builder) {
        super(builder);
    }

    /**
     * Collects the tickers to quote, joining them into the single comma-separated
     * {@code symbol} parameter the endpoint expects.
     */
    public static class Builder extends TimeSeriesRequest.Builder<RealtimeBulkQuoteRequest.Builder> {
        private final StringBuilder symbolBuilder = new StringBuilder();

        /**
         * Creates a builder pinned to {@link Function#REALTIME_BULK_QUOTES}, with no
         * tickers collected yet.
         */
        public Builder() {
            this.function(Function.REALTIME_BULK_QUOTES);
        }

        /**
         * Adds a ticker to the set being quoted, keeping any added before it.
         * <p>
         * This overrides the inherited setter, which replaces the ticker rather than
         * accumulating, so a chain written the way a single-ticker request would be
         * written quotes every ticker named rather than only the last. Call it once per
         * ticker, and at least once — a builder with no tickers cannot be built.
         *
         * @param symbol the ticker symbol to add, for example {@code IBM}
         * @return this builder, for method chaining
         */
        public Builder forSymbol(String symbol) {
            symbolBuilder.append(symbol).append(",");
            return this;
        }

        /**
         * Joins the tickers collected so far into one comma-separated parameter and
         * assembles them into a bulk quote request.
         *
         * @return a request for the collected tickers' latest quotes
         */
        @Override
        public RealtimeBulkQuoteRequest build() {
            symbolBuilder.deleteCharAt(symbolBuilder.length() - 1);
            this.symbol = symbolBuilder.toString();

            return new RealtimeBulkQuoteRequest(this);
        }
    }
}
