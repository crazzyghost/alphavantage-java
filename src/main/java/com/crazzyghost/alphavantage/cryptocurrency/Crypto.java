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
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class Crypto implements Fetcher {

    private Config config;
    private CryptoRequest request;
    private CryptoRequest.Builder<?> builder;
    private Fetcher.SuccessCallback<?> successCallback;
    private Fetcher.FailureCallback failureCallback;

    public Crypto(Config config){
        this.config = config;
        this.request = null;
    }

    public DailyRequestProxy daily(){
        return new DailyRequestProxy();
    }

    public WeeklyRequestProxy weekly(){
        return new WeeklyRequestProxy();
    }

    public MonthlyRequestProxy monthly(){
        return new MonthlyRequestProxy();
    }

    public RatingRequestProxy rating(){
        return new RatingRequestProxy();
    }

    @Override
    public void fetch() {

        if(config == null || config.getKey() == null){
            throw new AlphaVantageException("Config not set");
        }
        
        this.request = this.builder.build();

        Request request = new Request.Builder()
                .url(Config.BASE_URL + UrlExtractor.extract(this.request) + config.getKey())
                .build();

        config.getOkHttpClient().newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(failureCallback != null){
                    failureCallback.onFailure(new AlphaVantageException());
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    Moshi moshi = new Moshi.Builder().build();
                    Type type = Types.newParameterizedType(Map.class, String.class, Object.class);
                    JsonAdapter<Map<String,Object>> adapter = moshi.adapter(type);
                    parseCryptoResponse(adapter.fromJson(response.body().string()));
                }else{
                    if(failureCallback != null){
                        failureCallback.onFailure(new AlphaVantageException());
                    }
                }
            }
        });
    }

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


    @SuppressWarnings("unchecked")
    private void parseDigitalCurrencyResponse(Map<String, Object> data){
        CryptoResponse response = CryptoResponse.of(data, ((DigitalCurrencyRequest.Builder)builder).getMarket());
        if(response.getErrorMessage() != null){
            if(failureCallback != null){
                failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
            }
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<CryptoResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseRatingResponse(Map<String, Object> data){
        RatingResponse response = RatingResponse.of(data);
        if(response.getErrorMessage() != null){
            if(failureCallback != null){
                failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
            }
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<RatingResponse>)successCallback).onSuccess(response);
        }        
    }
    

    @SuppressWarnings("unchecked")
    public abstract class RequestProxy<T extends RequestProxy<?>> implements Fetcher{

        protected CryptoRequest.Builder<?> builder;

        private RequestProxy(){
            Crypto.this.successCallback = null;
            Crypto.this.failureCallback = null;
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

        @Override
        public void fetch() {
            Crypto.this.builder = this.builder;
            Crypto.this.fetch();
        }
    }

    public class DailyRequestProxy extends RequestProxy<DailyRequestProxy>{
        public DailyRequestProxy(){
            super();
            builder = new DigitalCurrencyRequest.Builder();
            builder = builder.function(Function.DIGITAL_CURRENCY_DAILY);
            Crypto.this.successCallback = null;
            Crypto.this.failureCallback = null;
        }
 
        public DailyRequestProxy market(String market){
            ((DigitalCurrencyRequest.Builder)builder).market(market);
            return this;
        }

    }

    public class WeeklyRequestProxy extends RequestProxy<WeeklyRequestProxy>{
        public WeeklyRequestProxy(){
            builder = new DigitalCurrencyRequest.Builder();
            builder = builder.function(Function.DIGITAL_CURRENCY_WEEKLY);
            Crypto.this.successCallback = null;
            Crypto.this.failureCallback = null;
        }

        public WeeklyRequestProxy market(String market){
            ((DigitalCurrencyRequest.Builder)builder).market(market);
            return this;
        }

    }

    public class MonthlyRequestProxy extends RequestProxy<MonthlyRequestProxy>{
        public MonthlyRequestProxy(){
            builder = new DigitalCurrencyRequest.Builder();
            builder = builder.function(Function.DIGITAL_CURRENCY_MONTHLY);
            Crypto.this.successCallback = null;
            Crypto.this.failureCallback = null;
        }

        public MonthlyRequestProxy market(String market){
            ((DigitalCurrencyRequest.Builder)builder).market(market);
            return this;
        }

    }

    public class RatingRequestProxy extends RequestProxy<RatingRequestProxy> {
        public  RatingRequestProxy(){
            builder = new RatingRequest.Builder();
            Crypto.this.successCallback = null;
            Crypto.this.failureCallback = null;
        }
    }
}