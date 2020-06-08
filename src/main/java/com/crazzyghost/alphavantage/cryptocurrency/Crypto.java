package com.crazzyghost.alphavantage.cryptocurrency;

import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.Fetcher;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.cryptocurrency.request.CryptoRequest;
import com.crazzyghost.alphavantage.cryptocurrency.request.DigitalCurrencyRequest;
import com.crazzyghost.alphavantage.cryptocurrency.request.RatingRequest;
import com.crazzyghost.alphavantage.cryptocurrency.response.CryptoResponse;
import com.crazzyghost.alphavantage.cryptocurrency.response.RatingResponse;
import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parser.Parser;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.Map;

/**
 * Access to Crypto Currency Data
 * @author crazzyghost
 * @since 1.0.0
 */

public class Crypto implements Fetcher {

    private Config config;
    private CryptoRequest.Builder<?> builder;
    private Fetcher.SuccessCallback<?> successCallback;
    private Fetcher.FailureCallback failureCallback;

    public Crypto(Config config){
        this.config = config;
    }

    /**
     * Access daily crypto currency data
     * @return {@link DailyRequestProxy} instance
     */
    public DailyRequestProxy daily(){
        return new DailyRequestProxy();
    }

    /**
     * Access weekly crypto currency data
     * @return {@link WeeklyRequestProxy} instance
     */
    public WeeklyRequestProxy weekly(){
        return new WeeklyRequestProxy();
    }

    /**
     * Access monthly crypto currency data
     * @return {@link MonthlyRequestProxy} instance
     */
    public MonthlyRequestProxy monthly(){
        return new MonthlyRequestProxy();
    }

    /**
     * Access crypto currency health index data
     * @return {@link RatingRequestProxy} instance
     */
    public RatingRequestProxy rating(){
        return new RatingRequestProxy();
    }

    /**
     * Fetch Crypto Currency data 
     * @see Fetcher#fetch()
     */
    @Override
    public void fetch() {

        Config.checkNotNullOrKeyEmpty(config);
        
        config.getOkHttpClient().newCall(UrlExtractor.extract(builder.build(), config.getKey())).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(failureCallback != null) failureCallback.onFailure(new AlphaVantageException());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    try(ResponseBody body = response.body()){
                        parseCryptoResponse(Parser.parseJSON(body.string()));
                    }
                }else{
                    if(failureCallback != null) failureCallback.onFailure(new AlphaVantageException());
                }
            }
        });
    }

    /**
     * parse a JSON response to a {@link CryptoResponse} or {@link RatingResponse} object
     * @param data parsed JSON response
     */
    private void parseCryptoResponse(Map<String, Object> data){

        switch(builder.function){
            case CRYPTO_RATING:
                parseRatingResponse(data);
                break;
            case DIGITAL_CURRENCY_DAILY:
            case DIGITAL_CURRENCY_MONTHLY:
            case DIGITAL_CURRENCY_WEEKLY:
                parseDigitalCurrencyResponse(data);
            default:
                break;
        }
    }


    /**
     * Parse Digital Currency Data
     * @param data parsed JSON data
     */
    @SuppressWarnings("unchecked")
    private void parseDigitalCurrencyResponse(Map<String, Object> data){
        CryptoResponse response = CryptoResponse.of(data, ((DigitalCurrencyRequest.Builder)builder).getMarket());
        if(response.getErrorMessage() != null && failureCallback != null) failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        if(successCallback != null) ((Fetcher.SuccessCallback<CryptoResponse>)successCallback).onSuccess(response);
    }

    /**
     * Parse Health Index Data
     * @param data parsed JSON data
     */
    @SuppressWarnings("unchecked")
    private void parseRatingResponse(Map<String, Object> data){
        RatingResponse response = RatingResponse.of(data);
        if(response.getErrorMessage() != null && failureCallback != null) failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        if(successCallback != null) ((Fetcher.SuccessCallback<RatingResponse>)successCallback).onSuccess(response);
    }
    



     /**
     * An abstract proxy for building requests. Adds the functionality of adding callbacks and a terminal method for 
     * fetching data.
     * @param <T> A Concrete {@link RequestProxy} Implementation
     */
    @SuppressWarnings("unchecked")
    public abstract class RequestProxy<T extends RequestProxy<?>> {

        protected CryptoRequest.Builder<?> builder;

        private RequestProxy(){
        }

        public T forSymbol(String symbol){
            this.builder.symbol(symbol);
            return (T)this;
        }

        public T onSuccess(SuccessCallback<?> callback){
            Crypto.this.successCallback = callback;
            return (T)this;
        }

        public T onFailure(FailureCallback callback){
            Crypto.this.failureCallback = callback;
            return (T)this;
        }

        public void fetch() {
            Crypto.this.builder = this.builder;
            Crypto.this.fetch();
        }
    }

    /**
     * Proxy for building a {@link DailyRequest}
     */
    public class DailyRequestProxy extends RequestProxy<DailyRequestProxy>{
        public DailyRequestProxy(){
            super();
            builder = new DigitalCurrencyRequest.Builder();
            builder = builder.function(Function.DIGITAL_CURRENCY_DAILY);
        }
 
        public DailyRequestProxy market(String market){
            ((DigitalCurrencyRequest.Builder)builder).market(market);
            return this;
        }

    }

    /**
     * Proxy for building a {@link WeeklyRequest}
     */
    public class WeeklyRequestProxy extends RequestProxy<WeeklyRequestProxy>{
        public WeeklyRequestProxy(){
            builder = new DigitalCurrencyRequest.Builder();
            builder = builder.function(Function.DIGITAL_CURRENCY_WEEKLY);
        }

        public WeeklyRequestProxy market(String market){
            ((DigitalCurrencyRequest.Builder)builder).market(market);
            return this;
        }

    }

    /**
     * Proxy for building a {@link MonthlyRequest}
     */
    public class MonthlyRequestProxy extends RequestProxy<MonthlyRequestProxy>{
        public MonthlyRequestProxy(){
            builder = new DigitalCurrencyRequest.Builder();
            builder = builder.function(Function.DIGITAL_CURRENCY_MONTHLY);
        }

        public MonthlyRequestProxy market(String market){
            ((DigitalCurrencyRequest.Builder)builder).market(market);
            return this;
        }

    }

    /**
     * Proxy for building a {@link RatingRequest}
     */
    public class RatingRequestProxy extends RequestProxy<RatingRequestProxy> {
        public  RatingRequestProxy(){
            builder = new RatingRequest.Builder();
        }
    }
}