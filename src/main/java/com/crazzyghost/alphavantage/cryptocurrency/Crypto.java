package com.crazzyghost.alphavantage.cryptocurrency;

import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.Fetcher;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.cryptocurrency.input.CryptoRequest;
import com.crazzyghost.alphavantage.cryptocurrency.output.CryptoResponse;
import com.crazzyghost.alphavantage.parameters.Function;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Crypto implements Fetcher {

    private Config config;
    private CryptoRequest request;
    private CryptoRequest.Builder builder;
    private Fetcher.SuccessCallback<CryptoResponse> successCallback;
    private Fetcher.FailureCallback failureCallback;
    private OkHttpClient client;

    public Crypto(Config config){
        this.config = config;
        this.request = null;
        client = new OkHttpClient.Builder()
                .connectTimeout(config.getTimeOut(), TimeUnit.SECONDS)
                .build();

    }

    public DailyRequestHelper daily(){
        return new DailyRequestHelper();
    }

    public WeeklyRequestHelper weekly(){
        return new WeeklyRequestHelper();
    }

    public MonthlyRequestHelper monthly(){
        return new MonthlyRequestHelper();
    }


    @Override
    public void fetch() {

        //make sure the key is set
        if(config.getKey() == null){
            throw new AlphaVantageException("Config not set");
        }
        //build the api request parameters object finally
        this.request = this.builder.build();
        String market = request.getMarket();
        //okhttp
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(config.getTimeOut(), TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(Config.BASE_URL + UrlExtractor.extract(this.request) + config.getKey())
                .build();

        System.out.println(Config.BASE_URL + UrlExtractor.extract(this.request) + "***");

        //make the call
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //respond to callback on failure
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
                    CryptoResponse cryptoResponse = CryptoResponse.of(adapter.fromJson(response.body().string()), market);
                    if(cryptoResponse.getErrorMessage() != null){
                        if(failureCallback != null){
                            failureCallback.onFailure(new AlphaVantageException(cryptoResponse.getErrorMessage()));
                        }
                        System.err.println(cryptoResponse.getErrorMessage());
                        return;
                    }
                    if(successCallback != null)
                        successCallback.onSuccess(cryptoResponse);
                }else{

                    if(failureCallback != null){
                        failureCallback.onFailure(new AlphaVantageException());
                    }
                }
            }
        });
    }


    public abstract class RequestHelper<T extends RequestHelper> implements Fetcher{

        protected CryptoRequest.Builder builder;

        private RequestHelper(){
            this.builder = CryptoRequest.builder();
        }

        public T forSymbol(String symbol){
            this.builder.symbol(symbol);
            return (T)this;
        }

        public T market(String market){
            this.builder.market(market);
            return (T)this;
        }

        public T onSuccess(SuccessCallback<CryptoResponse> callback){
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

    public class DailyRequestHelper extends RequestHelper<DailyRequestHelper>{
        public DailyRequestHelper(){
            super();
            this.builder.function(Function.DIGITAL_CURRENCY_DAILY);
        }
    }

    public class WeeklyRequestHelper extends RequestHelper<WeeklyRequestHelper>{
        public WeeklyRequestHelper(){
            super();
            this.builder.function(Function.DIGITAL_CURRENCY_WEEKLY);
        }
    }

    public class MonthlyRequestHelper extends RequestHelper<MonthlyRequestHelper>{
        public MonthlyRequestHelper(){
            super();
            this.builder.function(Function.DIGITAL_CURRENCY_MONTHLY);
        }
    }
}
