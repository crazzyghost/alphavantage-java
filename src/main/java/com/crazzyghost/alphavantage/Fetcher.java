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
 * A fetch operation can either fail or succeed
 *
 * @since 1.0.0
 * @author Sylvester Sefa-Yeboah
 */
public interface Fetcher {

    /** Perform a fetch operation */
    void fetch();

    /**
     * Callback when the fetch operation succeeds
     *
     * @param <V> the type of the response of the fetch operation
     */
    interface SuccessCallback<V> {
        /**
         * Call this method with a response when the fetch operation is successful
         *
         * @param response response object
         */
        void onSuccess(V response);
    }

    /** Callback when the fetch operation fails */
    interface FailureCallback {
        /**
         * Call this method with an exception when the fetch operation fails
         *
         * @param ex exception
         */
        void onFailure(AlphaVantageException ex);
    }
}
