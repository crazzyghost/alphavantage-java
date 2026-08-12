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
package com.crazzyghost.alphavantage;

/**
 * Defines an interface for pulling data from the API source.
 * A fetch operation can either fail or succeed.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public interface Fetcher {

    /** Performs a fetch operation. */
    void fetch();

    /**
     * Callback invoked when the fetch operation succeeds.
     *
     * @param <V> the type of the response of the fetch operation
     */
    interface SuccessCallback<V> {
        /**
         * Called with the parsed response when the fetch operation is successful.
         *
         * @param response the parsed response object
         */
        void onSuccess(V response);
    }

    /** Callback invoked when the fetch operation fails. */
    interface FailureCallback {
        /**
         * Called with the cause when the fetch operation fails.
         *
         * @param ex the exception describing the failure
         */
        void onFailure(AlphaVantageException ex);
    }
}
