package com.crazzyghost.alphavantage.exchangerate;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.Fetcher;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ExchangeRate implements Fetcher {

    private Config config;
    private ExchangeRateRequest request;
    private ExchangeRateRequest.Builder builder;
    private Fetcher.SuccessCallback<ExchangeRateResponse> successCallback;
    private Fetcher.FailureCallback failureCallback;
    private OkHttpClient client;

    public ExchangeRate(Config config){
        this.config = config;
        this.request = null;
        this.builder = ExchangeRateRequest.builder();
        client = new OkHttpClient.Builder()
                .connectTimeout(config.getTimeOut(), TimeUnit.SECONDS)
                .build();
    }

    public ExchangeRate toCurrency(String toCurrency){
        this.builder.toCurrency(toCurrency);
        return this;
    }

    public ExchangeRate fromCurrency(String fromCurrency){
        this.builder.fromCurrency(fromCurrency);
        return this;
    }

    /**
     * @param callback successful fetch handler
     * @return current instance of {@link ExchangeRateResponse}
     */
    public ExchangeRate onSuccess(SuccessCallback<ExchangeRateResponse> callback){
        this.successCallback = callback;
        return this;
    }

    /**
     * @param callback failed fetch handler
     * @return current instance of {@link ExchangeRateResponse}
     */
    public ExchangeRate onFailure(FailureCallback callback){
        this.failureCallback = callback;
        return this;
    }

    @Override
    public void fetch() {

        if(config.getKey() == null){
            throw new AlphaVantageException("Config not set");
        }
        this.request = this.builder.build();

        Request request = new Request.Builder()
                .url(Config.BASE_URL + UrlExtractor.extract(this.request) + config.getKey())
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
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
                    ExchangeRateResponse exchangeResponse = ExchangeRateResponse.of(adapter.fromJson(response.body().string()));
                    if(exchangeResponse.getErrorMessage() != null){
                        if(failureCallback != null){
                            failureCallback.onFailure(new AlphaVantageException(exchangeResponse.getErrorMessage()));
                        }
                        return;
                    }
                    if(successCallback != null)
                        successCallback.onSuccess(exchangeResponse);
                }else{

                    if(failureCallback != null){
                        failureCallback.onFailure(new AlphaVantageException());
                    }
                }
            }
        });
    }
}
