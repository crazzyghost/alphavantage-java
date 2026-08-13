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
package com.crazzyghost.alphavantage;

/**
 * Central post-parse dispatch shared by every fetch proxy.
 *
 * <p>Given a parsed response and the caller's callbacks, it reports an API-level error to the
 * failure callback and then hands the response to the success callback.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.9.0
 */
public final class ResponseDispatcher {

    private ResponseDispatcher() {}

    /**
     * Reports an errored response to {@code failureCallback}, then hands the response to {@code
     * successCallback}.
     *
     * <p>A response carrying a non-null error message invokes {@code failureCallback} and then
     * falls through to {@code successCallback} with that same response; the success callback is not
     * skipped. A {@code null} response skips the error check and is still forwarded to {@code
     * successCallback}. Either callback may be {@code null}.
     *
     * @param <R> the response type
     * @param response the parsed response, possibly {@code null}
     * @param successCallback the success handler, or {@code null}
     * @param failureCallback the failure handler, or {@code null}
     */
    @SuppressWarnings("unchecked")
    public static <R extends Response> void dispatch(
            R response,
            Fetcher.SuccessCallback<?> successCallback,
            Fetcher.FailureCallback failureCallback) {

        if (response != null && response.getErrorMessage() != null && failureCallback != null) {
            failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if (successCallback != null) {
            ((Fetcher.SuccessCallback<R>) successCallback).onSuccess(response);
        }
    }
}
