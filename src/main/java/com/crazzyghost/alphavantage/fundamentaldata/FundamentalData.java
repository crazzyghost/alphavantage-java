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
package com.crazzyghost.alphavantage.fundamentaldata;

import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.Fetcher;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.cryptocurrency.Crypto;
import com.crazzyghost.alphavantage.fundamentaldata.request.*;
import com.crazzyghost.alphavantage.fundamentaldata.response.*;
import com.crazzyghost.alphavantage.indicator.response.mama.MAMAResponse;
import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parser.Parser;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.Map;

/**
 * Access to Fundamental Data
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.6.0
 */
public final class FundamentalData implements Fetcher {

    private final Config config;
    private FundamentalDataRequest.Builder<?> builder;
    private Fetcher.SuccessCallback<?> successCallback;
    private Fetcher.FailureCallback failureCallback;

    public FundamentalData(Config config) { this.config = config; }


    public IncomeStatementRequestProxy incomeStatement() {
        return new IncomeStatementRequestProxy();
    }

    public BalanceSheetRequestProxy balanceSheet() {
        return new BalanceSheetRequestProxy();
    }

    public CashFlowRequestProxy cashFlow() {
        return new CashFlowRequestProxy();
    }

    public EarningsRequestProxy earnings() {
        return new EarningsRequestProxy();
    }

    public CompanyOverViewRequestProxy companyOverview() {
        return new CompanyOverViewRequestProxy();
    }

    @Override
    public void fetch() {
        Config.checkNotNullOrKeyEmpty(config);

        config.getOkHttpClient().newCall(UrlExtractor.extract(builder.build(), config.getKey())).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(failureCallback != null) failureCallback.onFailure(new AlphaVantageException());
            }

            @Override
            public void onResponse(Call call,  Response response) throws IOException {
                if(!response.isSuccessful()){
                    if(failureCallback != null) failureCallback.onFailure(new AlphaVantageException());
                } else {
                    try(ResponseBody body = response.body()){
                        String jsonBody = body.string();
//                        if (builder.function == Function.OVERVIEW) {
//                            parseFundamentalDataResponse(null, Parser.parseJSON(jsonBody, CompanyOverview.class));
//                        } else {
                            parseFundamentalDataResponse(Parser.parseJSON(jsonBody), null);
//                        }
                    }
                }
            }
        });
    }

    /**
     * Make a blocking synchronous http request to fetch the data.
     * This will be called by the {@link FundamentalData.RequestProxy#fetchSync()}.
     *
     * Using this method will overwrite any async callback
     *
     * @since 1.6.0
     * @param successCallback internally used {@link SuccessCallback}
     * @throws AlphaVantageException exception thrown
     */
    private void fetchSync(SuccessCallback<?> successCallback) throws AlphaVantageException {

        Config.checkNotNullOrKeyEmpty(config);

        this.successCallback = successCallback;
        this.failureCallback = null;
        okhttp3.OkHttpClient client = config.getOkHttpClient();
        try (Response response = client.newCall(UrlExtractor.extract(builder.build(), config.getKey())).execute()) {
            String jsonBody = response.body().string();
//            if (builder.function == Function.OVERVIEW) {
//               parseFundamentalDataResponse(null, Parser.parseJSON(jsonBody, CompanyOverview.class));
//            } else {
                parseFundamentalDataResponse(Parser.parseJSON(jsonBody), null);
//            }
        } catch(IOException e) {
            throw new AlphaVantageException(e.getMessage());
        }
    }

    private void parseFundamentalDataResponse(Map<String, Object> data, Object overviewData) {
        switch (builder.function) {
            case OVERVIEW:
                parseCompanyOverviewResponse(data);
                break;
            case BALANCE_SHEET:
                parseBalanceSheetResponse(data);
                break;
            case INCOME_STATEMENT:
                parseIncomeStatementResponse(data);
                break;
            case CASH_FLOW:
                parseCashFlowResponse(data);
                break;
            case EARNINGS:
                parseEarningsResponse(data);
                break;
            default:
                break;
        }
    }

    @SuppressWarnings("unchecked")
    private void parseCompanyOverviewResponse(Map<String, Object> data/*Object data*/) {
        CompanyOverviewResponse response = CompanyOverviewResponse.of(data);
        if(response.getErrorMessage() != null && failureCallback != null) {
            failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<CompanyOverviewResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseBalanceSheetResponse(Map<String, Object> data) {
        BalanceSheetResponse response = BalanceSheetResponse.of(data);
        if(response.getErrorMessage() != null && failureCallback != null) {
            failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<BalanceSheetResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseIncomeStatementResponse(Map<String, Object> data) {
        IncomeStatementResponse response = IncomeStatementResponse.of(data);
        if(response.getErrorMessage() != null && failureCallback != null) {
            failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<IncomeStatementResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseCashFlowResponse(Map<String, Object> data) {
        CashFlowResponse response = CashFlowResponse.of(data);
        if(response.getErrorMessage() != null && failureCallback != null) {
            failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<CashFlowResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseEarningsResponse(Map<String, Object> data) {
        EarningsResponse response = EarningsResponse.of(data);
        if(response.getErrorMessage() != null && failureCallback != null) {
            failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<EarningsResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    public abstract class RequestProxy<Proxy extends RequestProxy<?, ProxyResponse>, ProxyResponse> {
        protected FundamentalDataRequest.Builder<?> builder;
        protected ProxyResponse syncResponse;

        private RequestProxy() {}

        public Proxy forSymbol(String symbol) {
            this.builder.symbol(symbol);
            return (Proxy) this;
        }

        public Proxy onSuccess(SuccessCallback<?> callback) {
            FundamentalData.this.successCallback = callback;
            return (Proxy)this;
        }

        public Proxy onFailure(FailureCallback callback) {
            FundamentalData.this.failureCallback = callback;
            return (Proxy)this;
        }

        public void fetch() {
            FundamentalData.this.builder = this.builder;
            FundamentalData.this.fetch();
        }

        public void setSyncResponse(ProxyResponse response) {
            this.syncResponse = response;
        }


        /**
         * Set the right builder and make a synchronous request using {@link Crypto#fetch()}
         * When calling this method, any async callbacks will be overwritten
         *
         * @return The api response
         * @throws AlphaVantageException exception during call
         */
        public ProxyResponse fetchSync() throws AlphaVantageException {
            SuccessCallback<ProxyResponse> callback = this::setSyncResponse;
            FundamentalData.this.builder = this.builder;
            FundamentalData.this.fetchSync(callback);
            return this.syncResponse;
        }

    }

    /** Proxy class for building an IncomeStatementRequests **/
    public class IncomeStatementRequestProxy extends RequestProxy<IncomeStatementRequestProxy, IncomeStatementResponse> {
        public IncomeStatementRequestProxy() {
            builder = new IncomeStatementRequest.Builder();
        }
    }

    /** Proxy class for building an BalanceSheet **/
    public class BalanceSheetRequestProxy extends RequestProxy<BalanceSheetRequestProxy, BalanceSheetResponse> {
        public BalanceSheetRequestProxy() {
            builder = new BalanceSheetRequest.Builder();
        }
    }

    /** Proxy class for building an CashFlow **/
    public class CashFlowRequestProxy extends RequestProxy<CashFlowRequestProxy, CashFlowResponse> {
        public CashFlowRequestProxy() {
            builder = new CashFlowRequest.Builder();
        }
    }

    /** Proxy class for building an Earnings **/
    public class EarningsRequestProxy extends RequestProxy<EarningsRequestProxy, EarningsResponse> {
        public EarningsRequestProxy() {
            builder = new EarningsRequest.Builder();
        }
    }

    /** Proxy class for building an CompanyOverview **/
    public class CompanyOverViewRequestProxy extends RequestProxy<CompanyOverViewRequestProxy, CompanyOverviewResponse> {
        public CompanyOverViewRequestProxy() {
            builder = new CompanyOverviewRequest.Builder();
        }
    }
}
