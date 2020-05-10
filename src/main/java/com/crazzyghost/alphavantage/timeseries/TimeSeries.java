package com.crazzyghost.alphavantage.timeseries;

import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.Fetcher;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;
import com.crazzyghost.alphavantage.timeseries.request.*;
import com.crazzyghost.alphavantage.timeseries.response.QuoteResponse;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Access to Stock Time Series Data
 * @author crazzyghost
 * @since 1.0.0
 */
public class TimeSeries implements Fetcher{

    private Config config;
    private TimeSeriesRequest request;
    private TimeSeriesRequest.Builder<?> builder;
    private boolean adjusted = false;
    private Fetcher.SuccessCallback<?> successCallback;
    private Fetcher.FailureCallback failureCallback;
 
    public TimeSeries(Config config){
        this.config = config;
        request = null;
    }

    /**
     * Access monthly stock time series data
     * @return {@link MonthlyRequestProxy} instance
     */
    public MonthlyRequestProxy monthly(){
        this.adjusted = false;
        return new MonthlyRequestProxy();
    }

     /**
     * Access weekly stock time series data
     * @return {@link WeeklyRequestProxy} instance
     */
    public WeeklyRequestProxy weekly(){
        this.adjusted = false;
        return new WeeklyRequestProxy();
    }

     /**
     * Access daily stock time series data
     * @return {@link DailyRequestProxy} instance
     */
    public DailyRequestProxy daily(){
        this.adjusted = false;
        return new DailyRequestProxy();
    }

     /**
     * Access intraday stock time series data
     * @return {@link IntraDayRequestProxy} instance
     */
    public IntraDayRequestProxy intraday(){
        return new IntraDayRequestProxy();
    }

     /**
     * Access monthly stock time series data
     * @return {@link GlobalQuoteRequestProxy} instance
     */
    public GlobalQuoteRequestProxy quote(){
        return new GlobalQuoteRequestProxy();
    }

    /**
     * Fetch Stock Time Series data
     */
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
                    failureCallback.onFailure(new AlphaVantageException(e.getMessage()));
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if(response.isSuccessful()){
                    Moshi moshi = new Moshi.Builder().build();
                    Type type = Types.newParameterizedType(Map.class, String.class, Object.class);
                    JsonAdapter<Map<String,Object>> adapter = moshi.adapter(type);
                    parseResponse(adapter.fromJson(response.body().string()));
                }else{
                    if(failureCallback != null){
                        failureCallback.onFailure(new AlphaVantageException());
                    }
                }
            }
        });
    }

     /**
     * parse {@link TimeSeriesResponse}
     * @param data [arsed JSON data
     */
    @SuppressWarnings("unchecked")
    private void parseTimeSeriesResponse(Map<String, Object> data){
        TimeSeriesResponse response = TimeSeriesResponse.of(data, adjusted);
        if(response.getErrorMessage() != null){
            if(failureCallback != null){
                failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
            }
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<TimeSeriesResponse>)successCallback).onSuccess(response);
        }
    }


    /**
     * parse {@link QuoteResponse}
     * @param data [arsed JSON data
     */
    @SuppressWarnings("unchecked")
    private void parseGlobalQuoteResponse(Map<String, Object> data){
        QuoteResponse response = QuoteResponse.of(data);
        if(response.getErrorMessage() != null){
            if(failureCallback != null){
                failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
            }
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<QuoteResponse>)successCallback).onSuccess(response);
        }
    }

    /**
     * parse a JSON response to a {@link TimeSeriesResponse} or {@link QuoteResponse} object
     * @param data parsed JSON response
     */
    private void parseResponse(Map<String, Object> data){
        
        switch(builder.function){
            case TIME_SERIES_DAILY:
            case TIME_SERIES_DAILY_ADJUSTED:
            case TIME_SERIES_MONTHLY:
            case TIME_SERIES_MONTHLY_ADJUSTED:
            case TIME_SERIES_WEEKLY:
            case TIME_SERIES_WEEKLY_ADJUSTED:
            case TIME_SERIES_INTRADAY:
                parseTimeSeriesResponse(data);
                break;
            case GLOBAL_QUOTE:
                parseGlobalQuoteResponse(data);
            default:
                break;
                
        }
    }

    
    /**
     * An abstract proxy for building requests. Adds the functionality of adding callbacks and a terminal method for 
     * fetching data.
     * @param <T> A Concrete {@link RequestProxy} Implementation
     */
    @SuppressWarnings("unchecked")
    public abstract class RequestProxy<T extends RequestProxy<?>> {

        protected TimeSeriesRequest.Builder<?> builder;

        private RequestProxy(){
            TimeSeries.this.failureCallback = null;
            TimeSeries.this.successCallback = null;
        }

        public T forSymbol(String symbol){
            this.builder.forSymbol(symbol);
            return (T)this;
        }

        public T dataType(DataType type){
            this.builder.dataType(type);
            return (T)this;
        }

        public void fetch() {
            TimeSeries.this.builder = this.builder;
            TimeSeries.this.fetch();
        }

        public T onSuccess(SuccessCallback<?> callback) {
            TimeSeries.this.successCallback = callback;
            return (T)this;
        }

        public T onFailure(FailureCallback callback) {
            TimeSeries.this.failureCallback = callback;
            return (T)this;
        }
    }


    /**
     * Proxy for building a {@link DailyRequest}
     */
    public class DailyRequestProxy extends RequestProxy<DailyRequestProxy>{

        DailyRequestProxy() {
            super();
            this.builder = new DailyRequest.Builder();
        }

        public DailyRequestProxy outputSize(OutputSize size){
            ((DailyRequest.Builder)this.builder).outputSize(size);
            return this;
        }

        public DailyRequestProxy adjusted(){
            TimeSeries.this.adjusted = true;
            ((DailyRequest.Builder)this.builder).adjusted();
            return this;
        }

    }

    /**
     * Proxy for building an {@link IntraDayRequest}
     */
    public class IntraDayRequestProxy extends RequestProxy<IntraDayRequestProxy>{

        IntraDayRequestProxy() {
            super();
            this.builder = new IntraDayRequest.Builder();
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

    /**
     * Proxy for building a {@link WeeklyRequest}
     */
    public class WeeklyRequestProxy extends RequestProxy<WeeklyRequestProxy>{

        WeeklyRequestProxy(){
            super();
            this.builder = new WeeklyRequest.Builder();
        }

        public WeeklyRequestProxy adjusted(){
            TimeSeries.this.adjusted = true;
            ((WeeklyRequest.Builder)this.builder).adjusted();
            return this;
        }
    }

    /**
     * Proxy for building a {@link MonthlyRequest}
     */
    public class MonthlyRequestProxy extends RequestProxy<MonthlyRequestProxy>{

        MonthlyRequestProxy(){
            super();
            this.builder = new MonthlyRequest.Builder();
        }

        public MonthlyRequestProxy adjusted(){
            TimeSeries.this.adjusted = true;
            ((MonthlyRequest.Builder)this.builder).adjusted();
            return this;
        }
    }

    public class GlobalQuoteRequestProxy extends RequestProxy<GlobalQuoteRequestProxy>{

        GlobalQuoteRequestProxy(){
            super();
            this.builder = new QuoteRequest.Builder();
        }
    }

}
