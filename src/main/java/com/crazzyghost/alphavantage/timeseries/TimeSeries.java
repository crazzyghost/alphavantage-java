package com.crazzyghost.alphavantage.timeseries;

import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.Fetcher;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;
import com.crazzyghost.alphavantage.parser.Parser;
import com.crazzyghost.alphavantage.timeseries.request.*;
import com.crazzyghost.alphavantage.timeseries.response.QuoteResponse;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.Map;

/**
 * Access to Stock Time Series Data
 * @author crazzyghost
 * @since 1.0.0
 */
public class TimeSeries implements Fetcher{

    private Config config;
    private TimeSeriesRequest.Builder<?> builder;
    private boolean adjusted = false;
    private Fetcher.SuccessCallback<?> successCallback;
    private Fetcher.FailureCallback failureCallback;
 
    public TimeSeries(Config config){
        this.config = config;
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
     * Make a blocking synchronous http request to fetch the data.
     * This will be called by the {@link RequestProxy#fetchSync()}. 
     * You will have to handle it on a separate thread to avoid ui or program hang
     * Prefer using the async fetch implementation through the {@link RequestProxy#fetch()}
     * @since 1.4.1
     * @param successCallback internally used {@link SuccessCallback}
     * @throws AlphaVantageException exception thrown
     */
    private void fetchSync(SuccessCallback<?> successCallback) throws AlphaVantageException {

        Config.checkNotNullOrKeyEmpty(config);
        
        this.successCallback = successCallback;
        okhttp3.OkHttpClient client = config.getOkHttpClient();
        try(Response response = client.newCall(UrlExtractor.extract(builder.build(), config.getKey())).execute()){
            parseResponse(Parser.parseJSON(response.body().string()));
        }catch(IOException e){
            throw new AlphaVantageException(e.getMessage());
        }        
    }

    /**
     * Fetch Stock Time Series data
     */
    @Override
    public void fetch(){

        Config.checkNotNullOrKeyEmpty(config);
        
        config.getOkHttpClient().newCall(UrlExtractor.extract(builder.build(), config.getKey())).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(failureCallback != null) failureCallback.onFailure(new AlphaVantageException(e.getMessage()));   
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    try(ResponseBody body = response.body()){
                        parseResponse(Parser.parseJSON(body.string()));
                    }
                }else{
                    if(failureCallback != null) failureCallback.onFailure(new AlphaVantageException());
                }
            }
        });
    }

     /**
     * parse {@link TimeSeriesResponse}
     * @param data parsed JSON data
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
     * @param data parsed JSON data
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
     * @param <U> A Response Type to return during a synchronous call
     */
    @SuppressWarnings("unchecked")
    public abstract class RequestProxy<T extends RequestProxy<?, U>, U> {

        protected TimeSeriesRequest.Builder<?> builder;
        protected U syncResponse;

        private RequestProxy(){
        }

        /**
         * Set the symbol for the request
         * @param symbol
         * @return
         */
        public T forSymbol(String symbol){
            this.builder.forSymbol(symbol);
            return (T)this;
        }

        /**
         * Set the dataType for the request
         * @param type the datatype {@link DataType}
         * @return 
         */
        public T dataType(DataType type){
            this.builder.dataType(type);
            return (T)this;
        }

        /**
         * Set the success callback during an async call
         * @param callback
         * @return
         */
        public T onSuccess(SuccessCallback<?> callback) {
            TimeSeries.this.successCallback = callback;
            return (T)this;
        }

        /**
         * Set the failure callback during an async call
         * @param callback
         * @return
         */
        public T onFailure(FailureCallback callback) {
            TimeSeries.this.failureCallback = callback;
            return (T)this;
        }

        /**
         * Set the right builder and make an async http request using the {@link TimeSeries#fetch()}
         */
        public void fetch() {
            TimeSeries.this.builder = this.builder;
            TimeSeries.this.fetch();
        }
        
        /**
         * Set the reponse during a synchronous call
         * @param response
         */
        public void setSyncResponse(U response) {
            this.syncResponse = response;
        }


        /**
         * Set the right builder and make a synchronous request using {@link TimeSeries#fetch()}
         * @return The api response
         * @throws AlphaVantageException
         */
        public U fetchSync() throws AlphaVantageException {
            SuccessCallback<U> callback = (e) -> setSyncResponse(e);
            TimeSeries.this.builder = this.builder;
            TimeSeries.this.fetchSync(callback);
            return this.syncResponse;            
        }

    }


    /**
     * Proxy for building a {@link DailyRequest}
     */
    public class DailyRequestProxy extends RequestProxy<DailyRequestProxy, TimeSeriesResponse>{

        DailyRequestProxy() {
            super();
            this.builder = new DailyRequest.Builder();
        }

        /**
         * Set the output size of the request
         * @param size {@link OutputSize}
         * @return
         */
        public DailyRequestProxy outputSize(OutputSize size){
            ((DailyRequest.Builder)this.builder).outputSize(size);
            return this;
        }

        /**
         * Set the time series function to adjusted
         * @return 
         */
        public DailyRequestProxy adjusted(){
            TimeSeries.this.adjusted = true;
            ((DailyRequest.Builder)this.builder).adjusted();
            return this;
        }

    }

    /**
     * Proxy for building an {@link IntraDayRequest}
     */
    public class IntraDayRequestProxy extends RequestProxy<IntraDayRequestProxy, TimeSeriesResponse>{

        IntraDayRequestProxy() {
            super();
            this.builder = new IntraDayRequest.Builder();
        }

        /**
         * Set the output size of the request
         * @param size {@link OutputSize}
         * @return
         */
        public IntraDayRequestProxy outputSize(OutputSize size){
            ((IntraDayRequest.Builder)this.builder).outputSize(size);
            return this;
        }

        /**
         * Set the interval of the request
         * @param interval {@link Interval}
         * @return
         */
        public IntraDayRequestProxy interval(Interval interval){
            ((IntraDayRequest.Builder)this.builder).interval(interval);
            return this;
        }
    }

    /**
     * Proxy for building a {@link WeeklyRequest}
     */
    public class WeeklyRequestProxy extends RequestProxy<WeeklyRequestProxy, TimeSeriesResponse>{

        WeeklyRequestProxy(){
            super();
            this.builder = new WeeklyRequest.Builder();
        }
        /**
         * Set the request function to adjusted
         * @return 
         */
        public WeeklyRequestProxy adjusted(){
            TimeSeries.this.adjusted = true;
            ((WeeklyRequest.Builder)this.builder).adjusted();
            return this;
        }
    }

    /**
     * Proxy for building a {@link MonthlyRequest}
     */
    public class MonthlyRequestProxy extends RequestProxy<MonthlyRequestProxy, TimeSeriesResponse>{

        MonthlyRequestProxy(){
            super();
            this.builder = new MonthlyRequest.Builder();
        }

        /**
         * Set the request function to adjusted
         * @return 
         */
        public MonthlyRequestProxy adjusted(){
            TimeSeries.this.adjusted = true;
            ((MonthlyRequest.Builder)this.builder).adjusted();
            return this;
        }
    }

    /**
     * Proxy for building a {@link QuoteRequest}
     */
    public class GlobalQuoteRequestProxy extends RequestProxy<GlobalQuoteRequestProxy , QuoteResponse>{
        
        GlobalQuoteRequestProxy(){
            super();
            this.builder = new QuoteRequest.Builder();
        }
        
    }

}
