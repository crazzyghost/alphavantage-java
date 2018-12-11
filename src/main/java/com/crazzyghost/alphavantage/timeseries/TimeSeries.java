package com.crazzyghost.alphavantage.timeseries;

import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.Fetcher;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;
import com.crazzyghost.alphavantage.timeseries.request.*;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
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

public class TimeSeries{

    private Config config;
    private TimeSeriesRequest request;
    private TimeSeriesRequest.Builder builder;
    private boolean adjusted = false;
    private Fetcher.SuccessCallback<TimeSeriesResponse> successCallback;
    private Fetcher.FailureCallback failureCallback;
    private OkHttpClient client;

    public TimeSeries(Config config){
        this.config = config;
        request = null;
        client = new OkHttpClient.Builder()
                .connectTimeout(config.getTimeOut(), TimeUnit.SECONDS)
                .build();

    }


    public MonthlyRequestHelper monthly(){
        this.adjusted = false;
        return new MonthlyRequestHelper();
    }

    public WeeklyRequestHelper weekly(){
        this.adjusted = false;
        return new WeeklyRequestHelper();
    }

    public DailyRequestHelper daily(){
        this.adjusted = false;
        return new DailyRequestHelper();
    }

    public IntraDayRequestHelper intraday(){
        this.adjusted = false;
        return new IntraDayRequestHelper();
    }


    public void fetch(){

        //make sure the key is set
        if(config.getKey() == null){
            throw new AlphaVantageException("Config not set");
        }
        //build the api request parameters object finally
        this.request = this.builder.build();

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
                    TimeSeriesResponse stockResponse = TimeSeriesResponse.of(adapter.fromJson(response.body().string()), adjusted);
                    if(stockResponse.getErrorMessage() != null){
                        if(failureCallback != null){
                            failureCallback.onFailure(new AlphaVantageException(stockResponse.getErrorMessage()));
                        }
                        System.err.println(stockResponse.getErrorMessage());
                        return;
                    }
                    if(successCallback != null)
                        successCallback.onSuccess(stockResponse);
                    System.out.println("Success Fetching Response!");
                }else{

                    if(failureCallback != null){
                        failureCallback.onFailure(new AlphaVantageException());
                    }
                    System.err.println("Error Fetching Response.");
                }
            }
        });
    }


    public abstract class RequestHelper<T extends RequestHelper> implements Fetcher {

        protected TimeSeriesRequest.Builder builder;

        private RequestHelper(){

        }

        public T forSymbol(String symbol){
            this.builder.forSymbol(symbol);
            return (T)this;
        }

        public T dataType(DataType type){
            this.builder.dataType(type);
            return (T)this;
        }

        @Override
        public void fetch() {
            TimeSeries.this.builder = this.builder;
            TimeSeries.this.fetch();
        }


        public T onSuccess(SuccessCallback<TimeSeriesResponse> callback) {
            TimeSeries.this.successCallback = callback;
            return (T)this;
        }


        public T onFailure(FailureCallback callback) {
            TimeSeries.this.failureCallback = callback;
            return (T)this;
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

        public DailyRequestHelper adjusted(){
            TimeSeries.this.adjusted = true;
            ((DailyRequest.Builder)this.builder).adjusted();
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
        }

        public WeeklyRequestHelper adjusted(){
            TimeSeries.this.adjusted = true;
            ((WeeklyRequest.Builder)this.builder).adjusted();
            return this;
        }
    }

    public class MonthlyRequestHelper extends RequestHelper<MonthlyRequestHelper>{

        MonthlyRequestHelper(){
            super();
            this.builder = MonthlyRequest.builder();
        }

        public MonthlyRequestHelper adjusted(){
            TimeSeries.this.adjusted = true;
            ((MonthlyRequest.Builder)this.builder).adjusted();
            return this;
        }
    }

}
