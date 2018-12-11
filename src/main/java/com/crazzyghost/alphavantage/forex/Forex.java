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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Forex{

    

    private Config config;
    private ForexRequest request;
    private ForexRequest.Builder builder;
    private Fetcher.SuccessCallback<ForexResponse> successCallback;
    private Fetcher.FailureCallback failureCallback;
    private OkHttpClient client;

    public Forex(Config config){
        this.config = config;
        request = null;
        client = new OkHttpClient.Builder()
                .connectTimeout(config.getTimeOut(), TimeUnit.SECONDS)
                .build();

    }

    public WeeklyRequestHelper weekly(){
        return new WeeklyRequestHelper();
    }

    public DailyRequestHelper daily(){
        return new DailyRequestHelper();
    }

    public IntraDayRequestHelper intraday(){
        return new IntraDayRequestHelper();
    }

    public MonthlyRequestHelper monthly(){
        return new MonthlyRequestHelper();
    }


    private void fetch(){

        //make sure the key is set
        if(config.getKey() == null){
            throw new AlphaVantageException("Config not set");
        }
        //build the api request parameters object finally
        this.request = this.builder.build();
        //okhttp
        Request request = new Request.Builder()
                .url(Config.BASE_URL + UrlExtractor.extract(this.request) + config.getKey())
                .build();

        System.out.println(Config.BASE_URL + UrlExtractor.extract(this.request) + config.getKey());

        //make the call
        System.out.println("Fetching Response ...");
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //respond to callback on failure
                System.out.println("Failed Fetching Response ... " + e.getClass().getCanonicalName());
//
                if(failureCallback != null){
                    failureCallback.onFailure(new AlphaVantageException());
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("Received Response.");
                if(response.isSuccessful()){

                    Moshi moshi = new Moshi.Builder().build();
                    Type type = Types.newParameterizedType(Map.class, String.class, Object.class);
                    JsonAdapter<Map<String,Object>> adapter = moshi.adapter(type);
                    ForexResponse forexResponse = ForexResponse.of(adapter.fromJson(response.body().string()));

                    if(forexResponse.getErrorMessage() != null) {
                        if(failureCallback != null)
                            failureCallback.onFailure(new AlphaVantageException(forexResponse.getErrorMessage()));
                        System.err.println(forexResponse.getErrorMessage());
                        return;
                    }
                    if(successCallback != null){
                        successCallback.onSuccess(forexResponse);
                        System.out.println("Success Fetching Response!");
                    }
                }else{

                    if(failureCallback != null){
                        failureCallback.onFailure(new AlphaVantageException());
                    }
                    System.err.println("Error Fetching Response");
                }
            }
        });
    }


    public abstract class RequestHelper<T extends RequestHelper> implements Fetcher {

        protected ForexRequest.Builder builder;

        private RequestHelper(){

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


    public class DailyRequestHelper extends RequestHelper<DailyRequestHelper>{

        DailyRequestHelper() {
            super();
            this.builder = DailyRequest.builder();
        }

        public DailyRequestHelper outputSize(OutputSize size){
            ((DailyRequest.Builder)this.builder).outputSize(size);
            return this;
        }

    }

    public class IntraDayRequestHelper extends RequestHelper<IntraDayRequestHelper>{

        IntraDayRequestHelper() {
            super();
            this.builder = IntraDayRequest.builder();
        }

        public IntraDayRequestHelper outputSize(OutputSize size){
            ((DailyRequest.Builder)this.builder).outputSize(size);
            return this;
        }

        public IntraDayRequestHelper interval(Interval interval){
            ((IntraDayRequest.Builder)this.builder).interval(interval);
            return this;
        }
    }

    public class WeeklyRequestHelper extends RequestHelper<WeeklyRequestHelper>{

        WeeklyRequestHelper(){
            super();
            this.builder = WeeklyRequest.builder();
            System.out.println(this.builder);
        }
    }

    public class MonthlyRequestHelper extends RequestHelper<MonthlyRequestHelper>{

        MonthlyRequestHelper(){
            super();
            this.builder = MonthlyRequest.builder();
        }
    }
}
