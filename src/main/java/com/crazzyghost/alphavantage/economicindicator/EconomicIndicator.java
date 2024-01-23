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
package com.crazzyghost.alphavantage.economicindicator;

import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.Fetcher;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.economicindicator.request.*;
import com.crazzyghost.alphavantage.economicindicator.response.EconomicIndicatorResponse;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.Maturity;
import com.crazzyghost.alphavantage.parser.Parser;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

/**
 * EconomicIndicator
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 */
public class EconomicIndicator implements Fetcher {

    private final Config config;
    private EconomicIndicatorRequest.Builder<?> builder;
    private Fetcher.SuccessCallback<EconomicIndicatorResponse> successCallback;
    private Fetcher.FailureCallback failureCallback;

    public EconomicIndicator(Config config) {
        this.config = config;
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
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    try(ResponseBody body = response.body()){
                        EconomicIndicatorResponse economicIndicatorResponse = EconomicIndicatorResponse.of(Parser.parseJSON(body.string()));
                        if (economicIndicatorResponse.getErrorMessage() != null && failureCallback != null) {
                            failureCallback.onFailure(new AlphaVantageException(economicIndicatorResponse.getErrorMessage()));
                        }
                        if (successCallback != null) {
                            successCallback.onSuccess(economicIndicatorResponse);
                        }
                    }
                } else {
                    if(failureCallback != null) {
                        failureCallback.onFailure(new AlphaVantageException());
                    }
                }
            }
        });
    }

    /**
     * Make a blocking synchronous http request to fetch the data.
     * This will be called by the {@link EconomicIndicator.RequestProxy#fetchSync()}.
     *
     * Using this method will overwrite any async callback
     *
     * @since 1.7.0
     * @throws AlphaVantageException exception thrown
     */
    private EconomicIndicatorResponse fetchSync() throws AlphaVantageException {

        Config.checkNotNullOrKeyEmpty(config);

        this.successCallback = null;
        this.failureCallback = null;
        okhttp3.OkHttpClient client = config.getOkHttpClient();
        try (Response response = client.newCall(UrlExtractor.extract(builder.build(), config.getKey())).execute()) {
            return EconomicIndicatorResponse.of(Parser.parseJSON(response.body().string()));
        } catch(IOException e) {
            throw new AlphaVantageException(e.getMessage());
        }
    }

    public RealGdpRequestProxy realGdp() {
        return new RealGdpRequestProxy();
    }

    public RealGdpPerCapitaRequestProxy realGdpPerCapita() {
        return new RealGdpPerCapitaRequestProxy();
    }

    public TreasuryYieldRequestProxy treasuryYield() {
        return new TreasuryYieldRequestProxy();
    }

    public FederalFundsRateRequestProxy federalFundsRate() {
        return new FederalFundsRateRequestProxy();
    }

    public CpiRequestProxy cpi() {
        return new CpiRequestProxy();
    }

    public InflationRequestProxy inflation() {
        return new InflationRequestProxy();
    }

    public InflationExpectationRequestProxy inflationExpectation() {
        return new InflationExpectationRequestProxy();
    }

    public ConsumerSentimentRequestProxy consumerSentiment() {
        return new ConsumerSentimentRequestProxy();
    }

    public RetailSalesRequestProxy retailSales() {
        return new RetailSalesRequestProxy();
    }

    public DurableGoodsOrdersRequestProxy durables() {
        return new DurableGoodsOrdersRequestProxy();
    }

    public UnemploymentRateRequestProxy unemployment() {
        return new UnemploymentRateRequestProxy();
    }

    public NonFarmPayrollRequestProxy nonFarmPayroll() {
        return new NonFarmPayrollRequestProxy();
    }

    public abstract class RequestProxy<Proxy extends  RequestProxy<?>> {
        protected EconomicIndicatorRequest.Builder<?> builder;

        private RequestProxy() {}

        public Proxy onSuccess(SuccessCallback<EconomicIndicatorResponse> callback) {
            EconomicIndicator.this.successCallback = callback;
            return (Proxy)this;
        }

        public Proxy onFailure(FailureCallback callback) {
            EconomicIndicator.this.failureCallback = callback;
            return (Proxy)this;
        }

        public void fetch() {
            EconomicIndicator.this.builder = this.builder;
            EconomicIndicator.this.fetch();
        }

        /**
         * Set the right builder and make a synchronous request using {@link EconomicIndicator#fetch()}
         * When calling this method, any async callbacks will be overwritten
         *
         * @return The api response
         * @throws AlphaVantageException exception during call
         */
        public EconomicIndicatorResponse fetchSync() throws AlphaVantageException {
            EconomicIndicator.this.builder = this.builder;
            return EconomicIndicator.this.fetchSync();
        }

    }

    public class RealGdpRequestProxy extends RequestProxy<RealGdpRequestProxy> {
        public RealGdpRequestProxy() {
            builder = new RealGdpRequest.Builder();
        }

        public RealGdpRequestProxy interval(Interval interval){
            builder = ((RealGdpRequest.Builder)builder).interval(interval);
            return this;
        }
    }

    public class RealGdpPerCapitaRequestProxy extends RequestProxy<RealGdpPerCapitaRequestProxy> {
        public RealGdpPerCapitaRequestProxy() {
            builder = new RealGdpPerCapitaRequest.Builder();
        }
    }

    public class TreasuryYieldRequestProxy extends RequestProxy<TreasuryYieldRequestProxy> {
        public TreasuryYieldRequestProxy() {
            builder = new TreasuryYieldRequest.Builder();
        }

        public TreasuryYieldRequestProxy interval(Interval interval){
            builder = ((TreasuryYieldRequest.Builder)builder).interval(interval);
            return this;
        }

        public TreasuryYieldRequestProxy maturity(Maturity maturity){
            builder = ((TreasuryYieldRequest.Builder)builder).maturity(maturity);
            return this;
        }
    }

    public class FederalFundsRateRequestProxy extends RequestProxy<FederalFundsRateRequestProxy> {
        public FederalFundsRateRequestProxy() {
            builder = new FederalFundsRateRequest.Builder();
        }

        public FederalFundsRateRequestProxy interval(Interval interval){
            builder = ((FederalFundsRateRequest.Builder)builder).interval(interval);
            return this;
        }
    }

    public class CpiRequestProxy extends RequestProxy<CpiRequestProxy> {
        public CpiRequestProxy() {
            builder = new CpiRequest.Builder();
        }

        public CpiRequestProxy interval(Interval interval){
            builder = ((CpiRequest.Builder)builder).interval(interval);
            return this;
        }
    }

    public class InflationRequestProxy extends RequestProxy<InflationRequestProxy> {
        public InflationRequestProxy() {
            builder = new InflationRequest.Builder();
        }
    }

    public class InflationExpectationRequestProxy extends RequestProxy<InflationExpectationRequestProxy> {
        public InflationExpectationRequestProxy() {
            builder = new InflationExpectationRequest.Builder();
        }
    }

    public class ConsumerSentimentRequestProxy extends RequestProxy<ConsumerSentimentRequestProxy> {
        public ConsumerSentimentRequestProxy() {
            builder = new ConsumerSentimentRequest.Builder();
        }
    }

    public class RetailSalesRequestProxy extends RequestProxy<RetailSalesRequestProxy> {
        public RetailSalesRequestProxy() {
            builder = new RetailSalesRequest.Builder();
        }
    }

    public class DurableGoodsOrdersRequestProxy extends RequestProxy<DurableGoodsOrdersRequestProxy> {
        public DurableGoodsOrdersRequestProxy() {
            builder = new DurableGoodsOrdersRequest.Builder();
        }
    }

    public class UnemploymentRateRequestProxy extends RequestProxy<UnemploymentRateRequestProxy> {
        public UnemploymentRateRequestProxy() {
            builder = new UnemploymentRateRequest.Builder();
        }
    }

    public class NonFarmPayrollRequestProxy extends RequestProxy<NonFarmPayrollRequestProxy> {
        public NonFarmPayrollRequestProxy() {
            builder = new NonFarmPayrollRequest.Builder();
        }
    }

}
