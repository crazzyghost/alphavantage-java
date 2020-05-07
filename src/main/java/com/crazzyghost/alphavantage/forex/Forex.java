package com.crazzyghost.alphavantage.forex;

import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Fetcher;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.forex.request.*;
import com.crazzyghost.alphavantage.forex.response.ForexResponse;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class Forex implements Fetcher{

    private Config config;
    private ForexRequest request;
    private ForexRequest.Builder<?> builder;
    private Fetcher.SuccessCallback<ForexResponse> successCallback;
    private Fetcher.FailureCallback failureCallback;

    public Forex(Config config){
        this.config = config;
        request = null;

    }

    public WeeklyRequestProxy weekly(){
        return new WeeklyRequestProxy();
    }

    public DailyRequestProxy daily(){
        return new DailyRequestProxy();
    }

    public IntraDayRequestProxy intraday(){
        return new IntraDayRequestProxy();
    }

    public MonthlyRequestProxy monthly(){
        return new MonthlyRequestProxy();
    }


    @Override
    public void fetch(){

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
                    ForexResponse forexResponse = ForexResponse.of(adapter.fromJson(response.body().string()));

                    if(forexResponse.getErrorMessage() != null) {
                        if(failureCallback != null)
                            failureCallback.onFailure(new AlphaVantageException(forexResponse.getErrorMessage()));
                    }
                    if(successCallback != null){
                        successCallback.onSuccess(forexResponse);
                    }
                }else{

                    if(failureCallback != null){
                        failureCallback.onFailure(new AlphaVantageException());
                    }
                }
            }
        });
    }


    @SuppressWarnings("unchecked")
    public abstract class RequestProxy<T extends RequestProxy<?>> implements Fetcher {

        protected ForexRequest.Builder<?> builder;

        private RequestProxy(){
            Forex.this.successCallback = null;
            Forex.this.failureCallback = null;
        }

        public T toSymbol(String toSymbol){
            this.builder.toSymbol(toSymbol);
            return (T)this;
        }

        public T fromSymbol(String fromSymbol){
            this.builder.fromSymbol(fromSymbol);
            return (T)this;
        }

        public T dataType(DataType type){
            this.builder.dataType(type);
            return (T)this;
        }


        public T onSuccess(SuccessCallback<ForexResponse> callback) {
            Forex.this.successCallback = callback;
            return (T)this;
        }


        public T onFailure(FailureCallback callback) {
            Forex.this.failureCallback = callback;
            return (T)this;
        }

        @Override
        public void fetch() {
            Forex.this.builder = this.builder;
            Forex.this.fetch();
        }

    }


    public class DailyRequestProxy extends RequestProxy<DailyRequestProxy>{

        DailyRequestProxy() {
            super();
            this.builder = new DailyRequest.Builder();
            Forex.this.successCallback = null;
            Forex.this.failureCallback = null;
        }

        public DailyRequestProxy outputSize(OutputSize size){
            ((DailyRequest.Builder)this.builder).outputSize(size);
            return this;
        }

    }

    public class IntraDayRequestProxy extends RequestProxy<IntraDayRequestProxy>{

        IntraDayRequestProxy() {
            super();
            this.builder = new IntraDayRequest.Builder();
            Forex.this.successCallback = null;
            Forex.this.failureCallback = null;
        }

        public IntraDayRequestProxy outputSize(OutputSize size){
            ((IntraDayRequest.Builder)this.builder).outputSize(size);
            return this;
        }

        public IntraDayRequestProxy interval(Interval interval){
            ((IntraDayRequest.Builder)this.builder).interval(interval);
            return this;
        }
    }

    public class WeeklyRequestProxy extends RequestProxy<WeeklyRequestProxy>{

        WeeklyRequestProxy(){
            super();
            this.builder = new WeeklyRequest.Builder();
            Forex.this.successCallback = null;
            Forex.this.failureCallback = null;
        }
    }

    public class MonthlyRequestProxy extends RequestProxy<MonthlyRequestProxy>{

        MonthlyRequestProxy(){
            super();
            this.builder = new MonthlyRequest.Builder();
            Forex.this.successCallback = null;
            Forex.this.failureCallback = null;
        }
    }
}
