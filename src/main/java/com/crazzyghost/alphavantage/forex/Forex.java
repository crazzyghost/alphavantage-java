/*
 *
 * Copyright (c) 2020 Sylvester Sefa-Yeboah
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
import com.crazzyghost.alphavantage.parser.Parser;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

/**
 * Access to Foreign Exchange Time Series Data.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public final class Forex implements Fetcher {

    private final Config config;
    private ForexRequest.Builder<?> builder;
    private Fetcher.SuccessCallback<ForexResponse> successCallback;
    private Fetcher.FailureCallback failureCallback;

    public Forex(Config config){
        this.config = config;
    }

    /**
     * Accesses weekly foreign exchange time series data.
     *
     * @return a {@link WeeklyRequestProxy} instance
     */
    public WeeklyRequestProxy weekly(){
        return new WeeklyRequestProxy();
    }

    /**
     * Accesses daily foreign exchange time series data.
     *
     * @return a {@link DailyRequestProxy} instance
     */
    public DailyRequestProxy daily(){
        return new DailyRequestProxy();
    }

    /**
     * Accesses intraday foreign exchange time series data.
     *
     * @return an {@link IntraDayRequestProxy} instance
     */
    public IntraDayRequestProxy intraday(){
        return new IntraDayRequestProxy();
    }

    /**
     * Accesses monthly foreign exchange time series data.
     *
     * @return a {@link MonthlyRequestProxy} instance
     */
    public MonthlyRequestProxy monthly(){
        return new MonthlyRequestProxy();
    }

    /**
     * Fetches foreign exchange data asynchronously, dispatching the result to the
     * callback registered on the request proxy.
     */
    @Override
    public void fetch(){

        Config.checkNotNullOrKeyEmpty(config);

        config.getOkHttpClient().newCall(UrlExtractor.extract(builder.build(), config.getKey())).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(failureCallback != null){
                    failureCallback.onFailure(new AlphaVantageException());
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    try(ResponseBody body = response.body()){
                        ForexResponse forexResponse = ForexResponse.of(Parser.parseJSON(body.string()));
                        if(forexResponse.getErrorMessage() != null && failureCallback != null) failureCallback.onFailure(new AlphaVantageException(forexResponse.getErrorMessage()));
                        if(successCallback != null) successCallback.onSuccess(forexResponse);
                    }
                }else{
                    if(failureCallback != null) failureCallback.onFailure(new AlphaVantageException());
                }
            }
        });
    }

    /**
     * Makes a blocking synchronous http request to fetch the data.
     * This is called by {@link RequestProxy#fetchSync()}.
     * <p>
     * On Android this will throw {@code NetworkOnMainThreadException}. In that case
     * the call should be made on another thread.
     * <p>
     * Using this method will overwrite any async callback.
     *
     * @param successCallback internally used {@link SuccessCallback} that receives the parsed response
     * @throws AlphaVantageException if the request fails or the response cannot be read
     * @since 1.4.1
     */
    private void fetchSync(SuccessCallback<ForexResponse> successCallback) throws AlphaVantageException {

        Config.checkNotNullOrKeyEmpty(config);
        
        this.successCallback = successCallback;
        this.failureCallback = null;
        okhttp3.OkHttpClient client = config.getOkHttpClient();
        try(Response response = client.newCall(UrlExtractor.extract(builder.build(), config.getKey())).execute()){
            ForexResponse forexResponse = ForexResponse.of(Parser.parseJSON(response.body().string()));
            this.successCallback.onSuccess(forexResponse);
        }catch(IOException e){
            throw new AlphaVantageException(e.getMessage());
        }        
    }


    /**
     * An abstract proxy for building requests. Adds the functionality of adding
     * callbacks and a terminal method for fetching data.
     *
     * @param <T> a concrete {@link RequestProxy} implementation
     */
    @SuppressWarnings("unchecked")
    public abstract class RequestProxy<T extends RequestProxy<?>> {

        protected ForexRequest.Builder<?> builder;
        protected ForexResponse syncResponse;

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

        public void fetch() {
            Forex.this.builder = this.builder;
            Forex.this.fetch();
        }

        /**
         * Sets the response received during a synchronous call.
         *
         * @param response the parsed response to hand back to {@link #fetchSync()}
         */
        public void setSyncResponse(ForexResponse response) {
            this.syncResponse = response;
        }


        /**
         * Sets the right builder and makes a synchronous request using
         * {@link Forex#fetch()}.
         * <p>
         * When calling this method, any async callbacks will be overwritten.
         *
         * @return the api response
         * @throws AlphaVantageException if the request fails or the response cannot be read
         */
        public ForexResponse fetchSync() throws AlphaVantageException {
            SuccessCallback<ForexResponse> callback = (e) -> setSyncResponse(e);
            Forex.this.builder = this.builder;
            Forex.this.fetchSync(callback);
            return this.syncResponse;            
        }

    }

    /**
     * Proxy for building a {@link DailyRequest}.
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

    }

    /**
     * Proxy for building an {@link IntraDayRequest}.
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
     * Proxy for building a {@link WeeklyRequest}.
     */
    public class WeeklyRequestProxy extends RequestProxy<WeeklyRequestProxy>{

        WeeklyRequestProxy(){
            super();
            this.builder = new WeeklyRequest.Builder();
        }
    }

    /**
     * Proxy for building a {@link MonthlyRequest}.
     */
    public class MonthlyRequestProxy extends RequestProxy<MonthlyRequestProxy>{

        MonthlyRequestProxy(){
            super();
            this.builder = new MonthlyRequest.Builder();
        }
    }
}
