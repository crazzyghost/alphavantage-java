package com.crazzyghost.alphavantage.indicator;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.Fetcher;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.indicator.request.*;
import com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.SeriesType;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;

public class Indicator{

    private IndicatorRequest.Builder<?> builder;
    private Fetcher.SuccessCallback<?> successCallback;
    private Fetcher.FailureCallback failureCallback;
    private final Config config;
    private final OkHttpClient client;
    private IndicatorRequest request;

    public Indicator(final Config config) {
        this.config = config;
        request = null;
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(Level.BASIC);
        client = new OkHttpClient.Builder()
                .connectTimeout(config.getTimeOut(), TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();
    }

    

    public void fetch(){
        if(config.getKey() == null){
            throw new AlphaVantageException("Config not set");
        }

        this.request = this.builder.build();

        final Request request = new Request.Builder()
                .url(Config.BASE_URL + UrlExtractor.extract(this.request) + config.getKey())
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                if(failureCallback != null){
                    failureCallback.onFailure(new AlphaVantageException());
                }
            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                if(!response.isSuccessful()){
                    if(failureCallback != null){
                        failureCallback.onFailure(new AlphaVantageException());
                    }
                } else {
                    final Moshi moshi = new Moshi.Builder().build();
                    final Type type = Types.newParameterizedType(Map.class, String.class, Object.class);
                    final JsonAdapter<Map<String,Object>> adapter = moshi.adapter(type);
                    parseIndicatorResponse(adapter.fromJson(response.body().string()));
                }
            }
        });
    }

    private void parseIndicatorResponse(final Map<String, Object> data){
        
        switch(builder.function){
            case SMA:
            case EMA:
            case WMA:
            case DEMA:
            case TEMA:
            case TRIMA:
            case KAMA:
            case T3:
                PeriodicSeriesResponse response = PeriodicSeriesResponse.of(data, builder.function.name());
                if(response.getErrorMessage() != null) {
                if(failureCallback != null)
                    failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
                }
                if(successCallback != null){
                    ((Fetcher.SuccessCallback<PeriodicSeriesResponse>)successCallback).onSuccess(response);
                }
                break;
            default:
                break;        
        }
        
    }


    public PeriodicalSeriesRequestProxy sma(){
        return new PeriodicalSeriesRequestProxy(Function.SMA);
    }

    public PeriodicalSeriesRequestProxy ema(){
        return new PeriodicalSeriesRequestProxy(Function.EMA);
    }

    public PeriodicalSeriesRequestProxy wma(){
        return new PeriodicalSeriesRequestProxy(Function.WMA);
    }

    public PeriodicalSeriesRequestProxy dema(){
        return new PeriodicalSeriesRequestProxy(Function.DEMA);
    }

    public PeriodicalSeriesRequestProxy tema(){
        return new PeriodicalSeriesRequestProxy(Function.TEMA);
    }

    public PeriodicalSeriesRequestProxy trima(){
        return new PeriodicalSeriesRequestProxy(Function.TRIMA);
    }

    public PeriodicalSeriesRequestProxy kama(){
        return new PeriodicalSeriesRequestProxy(Function.KAMA);
    }

    public PeriodicalSeriesRequestProxy t3(){
        return new PeriodicalSeriesRequestProxy(Function.T3);
    }

    public SimpleIndicatorRequestProxy<?> vwap(){
        return new SimpleIndicatorRequestProxy<>(Function.VWAP);
    }

    public class SimpleIndicatorRequestProxy<T extends SimpleIndicatorRequestProxy<?>> {
        protected IndicatorRequest.Builder<?> builder;
        
        public SimpleIndicatorRequestProxy(){

        }
        
        public SimpleIndicatorRequestProxy(final Function function){
            builder = new SimpleIndicatorRequest.Builder(); 
            builder = builder.function(function);   
        }

        public T dataType(final DataType dataType){
            builder = builder.dataType(dataType);
            return (T)this;
        }

        public T forSymbol(final String symbol){
            builder = builder.forSymbol(symbol);
            return (T)this;
        }

        public T interval(final Interval interval){
            builder = builder.interval(interval);
            return (T)this;
        }

        public T onSuccess(final Fetcher.SuccessCallback<?> callback){
            Indicator.this.successCallback =  callback;
            return (T)this;
        }

        public T onFailure(final Fetcher.FailureCallback callback){
            Indicator.this.failureCallback = callback;
            return (T)this;
        }

        public void fetch(){
            Indicator.this.builder = builder;
            Indicator.this.fetch();
        }

    }

    public class PeriodicalSeriesRequestProxy extends SimpleIndicatorRequestProxy<PeriodicalSeriesRequestProxy>{
 
        public PeriodicalSeriesRequestProxy(final Function function){
            builder = new PeriodicSeriesRequest.Builder(); 
            builder = builder.function(function);   
        }

        public PeriodicalSeriesRequestProxy timePeriod(final int period){
            builder = ((PeriodicSeriesRequest.Builder)builder).timePeriod(period);
            return this;
        }

        public PeriodicalSeriesRequestProxy seriesType(final SeriesType series){
            builder = ((PeriodicSeriesRequest.Builder)builder).seriesType(series);
            return this;
        }

    }
}