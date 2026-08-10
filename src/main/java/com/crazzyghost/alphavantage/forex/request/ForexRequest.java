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
package com.crazzyghost.alphavantage.forex.request;

import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.OutputSize;

/**
 * The parameters every foreign exchange time series request carries: the currency pair
 * being quoted, and the format the API should answer in.
 * <p>
 * One subclass exists per sampling cadence — {@link IntraDayRequest},
 * {@link DailyRequest}, {@link WeeklyRequest} and {@link MonthlyRequest} — each pinning
 * its own endpoint function and adding whatever extra parameters that cadence accepts.
 * <p>
 * The field names here are snake_case on purpose.
 * {@link com.crazzyghost.alphavantage.UrlExtractor} builds the query string by
 * lowercasing each non-null field name, and the endpoints expect {@code from_symbol}
 * and {@code to_symbol}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public abstract class ForexRequest{

    /** The base currency of the pair, sent as the {@code from_symbol} parameter. */
    protected String from_symbol;

    /** The quote currency of the pair, sent as the {@code to_symbol} parameter. */
    protected String to_symbol;

    /** The format the API replies in, sent as the {@code datatype} parameter. */
    protected DataType dataType;

    /**
     * Copies the shared parameters out of a builder.
     *
     * @param builder the builder holding the parameters to copy
     */
    protected ForexRequest(Builder<?> builder) {
        this.to_symbol = builder.toSymbol;
        this.from_symbol = builder.fromSymbol;
        this.dataType = builder.dataType;
    }


    /**
     * Collects the parameters shared by every cadence.
     * <p>
     * Each setter returns the concrete subclass builder rather than this base type, so
     * that setting a shared parameter part-way through a chain does not cut off access
     * to the cadence-specific setters that follow it.
     *
     * @param <T> the concrete builder type the shared setters return
     */
    public abstract static class Builder <T extends Builder<?>>{

        private String fromSymbol;
        private String toSymbol;
        private DataType dataType = DataType.JSON;

        /**
         * Sets the base currency of the pair — the one whose price is being quoted.
         *
         * @param fromSymbol the base currency code, for example {@code EUR}
         * @return this builder, for method chaining
         */
        public T fromSymbol(String fromSymbol){
            this.fromSymbol = fromSymbol;
            return (T) this;
        }

        /**
         * Sets the quote currency of the pair — the one the base currency is priced in.
         *
         * @param fromSymbol the quote currency code, for example {@code USD}. The
         *                   parameter name is a misnomer left over from the base
         *                   currency setter; the value is stored as the to-symbol.
         * @return this builder, for method chaining
         */
        public T toSymbol(String fromSymbol){
            this.toSymbol =fromSymbol;
            return (T) this;
        }

        /**
         * Sets the format the API replies in. Defaults to {@link DataType#JSON}, which
         * is what this library's response parsers read.
         *
         * @param dataType the response format
         * @return this builder, for method chaining
         */
        public T dataType(DataType dataType){
            this.dataType = dataType;
            return (T) this;
        }

        /**
         * Assembles the parameters set so far into a request for this builder's cadence.
         *
         * @return a request carrying this builder's parameters
         */
        public abstract ForexRequest build();

    }
}
