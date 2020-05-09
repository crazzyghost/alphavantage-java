package com.crazzyghost.alphavantage;

/**
 * Defines an interface for pulling data from the API source.
 * A fetch operation can either fail or succeed
 * @since 1.0.0
 * @author crazzyghost
 */
public interface Fetcher{

    /**
     * Perform a fetch operation
     */
    void fetch();

    /**
     * Callback when the fetch operation succeeds
     * @param <V> the type of the reponse of the fetch operation
     */
    interface SuccessCallback<V>{
        /**
         * Call this method with a response when the fetch operation is successful
         * @param response response object
         */
        void onSuccess(V response);
    }

    /**
     * Callback when the fetch operation fails
     */
    interface FailureCallback{
        /**
         * Call this method with an exception when the fetch operation fails
         * @param ex exception 
         */
        void onFailure(AlphaVantageException ex);
    }
}
