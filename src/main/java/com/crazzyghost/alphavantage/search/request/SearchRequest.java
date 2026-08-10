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
package com.crazzyghost.alphavantage.search.request;

import com.crazzyghost.alphavantage.parameters.Function;

/**
 * The endpoint parameters of a {@code SYMBOL_SEARCH} call: the fixed function name,
 * and the keyword to match against the symbols and names Alpha Vantage covers.
 * <p>
 * Instances are immutable and built through {@link Builder}. Each non-null field
 * becomes one query-string parameter when
 * {@link com.crazzyghost.alphavantage.UrlExtractor} reads the request reflectively.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.8.0
 */
public class SearchRequest {
    private final Function function;
    private final String keywords;

    private SearchRequest(Builder builder) {
        this.function = builder.function;
        this.keywords = builder.keywords;
    }

    /**
     * Collects the parameters of a symbol search and assembles them into a
     * {@link SearchRequest}.
     * <p>
     * The function is pinned to {@link Function#SYMBOL_SEARCH} on construction, so
     * the keyword is the only parameter a caller supplies.
     */
    public static class Builder {
        private final Function function;
        private String keywords;

        /**
         * Creates a builder for the {@code SYMBOL_SEARCH} endpoint.
         */
        public Builder() {
            this.function = Function.SYMBOL_SEARCH;
        }

        /**
         * Sets the text to search for. Alpha Vantage matches it against both ticker
         * symbols and instrument names, so either {@code TSCO} or {@code Tesco} finds
         * the same company.
         *
         * @param keywords the free-text search term
         * @return this builder, for method chaining
         */
        public Builder keywords(String keywords) {
            this.keywords = keywords;
            return this;
        }

        /**
         * Assembles the parameters set so far into a request.
         *
         * @return a new request carrying this builder's parameters
         */
        public SearchRequest build() {
            return new SearchRequest(this);
        }
    }
}
