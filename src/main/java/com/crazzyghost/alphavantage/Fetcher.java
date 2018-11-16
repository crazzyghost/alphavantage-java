package com.crazzyghost.alphavantage;

public interface Fetcher{

    void fetch();

    interface SuccessCallback<V>{
        void onSuccess(V response);
    }

    interface FailureCallback{
        void onFailure(AlphaVantageException ex);
    }
}
