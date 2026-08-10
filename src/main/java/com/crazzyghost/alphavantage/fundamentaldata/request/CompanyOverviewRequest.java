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
package com.crazzyghost.alphavantage.fundamentaldata.request;

import com.crazzyghost.alphavantage.parameters.Function;

/**
 * A request for the {@code OVERVIEW} endpoint, which returns a company profile and
 * its headline valuation, profitability and dividend ratios in a single record.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.6.0
 */
public class CompanyOverviewRequest extends FundamentalDataRequest {

    /**
     * Creates a request from the given builder's current state.
     *
     * @param builder the builder to copy the symbol from
     */
    public CompanyOverviewRequest(Builder builder) {
        super(builder);
    }

    /** Builds a {@link CompanyOverviewRequest}. */
    public static class Builder extends FundamentalDataRequest.Builder<Builder> {

        /** Creates a builder preset to the {@code OVERVIEW} function. */
        public Builder() { this.function(Function.OVERVIEW); }

        /**
         * Builds the company overview request from this builder's current
         * state.
         *
         * @return the built company overview request
         */
        @Override
        public CompanyOverviewRequest build() {
            return new CompanyOverviewRequest(this);
        }
    }
}
