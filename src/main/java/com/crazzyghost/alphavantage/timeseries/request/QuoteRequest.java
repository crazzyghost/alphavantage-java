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
package com.crazzyghost.alphavantage.timeseries.request;

import com.crazzyghost.alphavantage.parameters.Function;

/**
 * A request to {@code GLOBAL_QUOTE}, which returns the latest price and trading day for
 * a single ticker rather than a series.
 * <p>
 * This is the cheapest way to answer "what is it trading at now": one ticker, one
 * snapshot, no history. Where the cadence requests answer with a list of bars, this one
 * answers with a single
 * {@link com.crazzyghost.alphavantage.timeseries.response.QuoteResponse} holding the
 * most recent trading day's open, high, low, price and volume, plus the change against
 * the previous close.
 * <p>
 * For the same snapshot across many tickers in one call, use
 * {@link RealtimeBulkQuoteRequest} instead of issuing a quote request per ticker.
 * <p>
 * The builder adds nothing of its own: the ticker and data type inherited from
 * {@link TimeSeriesRequest.Builder} are the endpoint's only parameters.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.3.0
 */
public class QuoteRequest extends TimeSeriesRequest {

    private QuoteRequest(Builder builder) {
        super(builder);
    }

    /**
     * Assembles a single-ticker quote request from the ticker and data type inherited
     * from {@link TimeSeriesRequest.Builder}.
     */
    public static class Builder extends TimeSeriesRequest.Builder<Builder>{
        /**
         * Creates a builder pinned to {@link Function#GLOBAL_QUOTE}.
         */
        public Builder(){
            this.function(Function.GLOBAL_QUOTE);
        }

        /**
         * Assembles the ticker set so far into a quote request.
         *
         * @return a request for the configured ticker's latest quote
         */
        @Override
        public QuoteRequest build(){
            return new QuoteRequest(this);
        }
    }



}
