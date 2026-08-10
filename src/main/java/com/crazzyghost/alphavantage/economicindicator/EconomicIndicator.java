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
 * Access to the US economic indicator endpoints — real GDP and GDP per capita,
 * treasury yield, federal funds rate, CPI and inflation, consumer sentiment,
 * retail sales, durable goods orders, unemployment and non-farm payroll — each
 * exposed as a request proxy that is built up fluently and then fetched.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 */
public class EconomicIndicator implements Fetcher {

    private final Config config;
    private EconomicIndicatorRequest.Builder<?> builder;
    private Fetcher.SuccessCallback<EconomicIndicatorResponse> successCallback;
    private Fetcher.FailureCallback failureCallback;

    /**
     * Creates access to the economic indicator endpoints for the given configuration.
     *
     * @param config the configuration this instance uses for every subsequent request
     */
    public EconomicIndicator(Config config) {
        this.config = config;
    }

    /**
     * Fetches economic indicator data asynchronously, dispatching the result to the
     * callback registered on the request proxy.
     */
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
     * Makes a blocking synchronous http request to fetch the data.
     * This is called by {@link EconomicIndicator.RequestProxy#fetchSync()}.
     * <p>
     * Using this method will overwrite any async callback.
     *
     * @return the economic indicator series returned by the API
     * @throws AlphaVantageException if the request fails or the response cannot be read
     * @since 1.7.0
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

    /**
     * Accesses the annual and quarterly real gross domestic product (GDP) of the
     * United States.
     *
     * @return a {@link RealGdpRequestProxy} instance
     */
    public RealGdpRequestProxy realGdp() {
        return new RealGdpRequestProxy();
    }

    /**
     * Accesses the quarterly real GDP per capita of the United States.
     *
     * @return a {@link RealGdpPerCapitaRequestProxy} instance
     */
    public RealGdpPerCapitaRequestProxy realGdpPerCapita() {
        return new RealGdpPerCapitaRequestProxy();
    }

    /**
     * Accesses the daily, weekly, and monthly US Treasury yield for a given
     * {@link Maturity}.
     *
     * @return a {@link TreasuryYieldRequestProxy} instance
     */
    public TreasuryYieldRequestProxy treasuryYield() {
        return new TreasuryYieldRequestProxy();
    }

    /**
     * Accesses the daily, weekly, and monthly federal funds rate (interest rate)
     * of the United States.
     *
     * @return a {@link FederalFundsRateRequestProxy} instance
     */
    public FederalFundsRateRequestProxy federalFundsRate() {
        return new FederalFundsRateRequestProxy();
    }

    /**
     * Accesses the monthly and semiannual consumer price index (CPI) of the
     * United States.
     *
     * @return a {@link CpiRequestProxy} instance
     */
    public CpiRequestProxy cpi() {
        return new CpiRequestProxy();
    }

    /**
     * Accesses the annual inflation rate of the United States, as measured by
     * the consumer price index.
     *
     * @return an {@link InflationRequestProxy} instance
     */
    public InflationRequestProxy inflation() {
        return new InflationRequestProxy();
    }

    /**
     * Accesses the monthly median expected inflation rate over the next 12
     * months, as measured by the University of Michigan's Surveys of Consumers.
     *
     * @return an {@link InflationExpectationRequestProxy} instance
     */
    public InflationExpectationRequestProxy inflationExpectation() {
        return new InflationExpectationRequestProxy();
    }

    /**
     * Accesses the monthly consumer sentiment and confidence index of the
     * United States, as measured by the University of Michigan's Surveys of
     * Consumers.
     *
     * @return a {@link ConsumerSentimentRequestProxy} instance
     */
    public ConsumerSentimentRequestProxy consumerSentiment() {
        return new ConsumerSentimentRequestProxy();
    }

    /**
     * Accesses the monthly advance retail sales of the United States.
     *
     * @return a {@link RetailSalesRequestProxy} instance
     */
    public RetailSalesRequestProxy retailSales() {
        return new RetailSalesRequestProxy();
    }

    /**
     * Accesses the monthly manufacturers' new orders for durable goods in the
     * United States.
     *
     * @return a {@link DurableGoodsOrdersRequestProxy} instance
     */
    public DurableGoodsOrdersRequestProxy durables() {
        return new DurableGoodsOrdersRequestProxy();
    }

    /**
     * Accesses the monthly unemployment rate of the United States.
     *
     * @return an {@link UnemploymentRateRequestProxy} instance
     */
    public UnemploymentRateRequestProxy unemployment() {
        return new UnemploymentRateRequestProxy();
    }

    /**
     * Accesses the monthly total nonfarm payroll of the United States, a key
     * indicator of overall employment.
     *
     * @return a {@link NonFarmPayrollRequestProxy} instance
     */
    public NonFarmPayrollRequestProxy nonFarmPayroll() {
        return new NonFarmPayrollRequestProxy();
    }

    /**
     * An abstract proxy for building requests. Adds the functionality of adding
     * callbacks and a terminal method for fetching data.
     *
     * @param <Proxy> the concrete {@link RequestProxy} implementation
     */
    public abstract class RequestProxy<Proxy extends  RequestProxy<?>> {
        /** The builder accumulating this proxy's request parameters. */
        protected EconomicIndicatorRequest.Builder<?> builder;

        private RequestProxy() {}

        /**
         * Registers the callback invoked when {@link #fetch()} succeeds.
         *
         * @param callback the callback that receives the parsed response
         * @return this proxy, for method chaining
         */
        public Proxy onSuccess(SuccessCallback<EconomicIndicatorResponse> callback) {
            EconomicIndicator.this.successCallback = callback;
            return (Proxy)this;
        }

        /**
         * Registers the callback invoked when {@link #fetch()} fails.
         *
         * @param callback the callback that receives the failure cause
         * @return this proxy, for method chaining
         */
        public Proxy onFailure(FailureCallback callback) {
            EconomicIndicator.this.failureCallback = callback;
            return (Proxy)this;
        }

        /**
         * Sets the right builder and makes an asynchronous request using
         * {@link EconomicIndicator#fetch()}.
         */
        public void fetch() {
            EconomicIndicator.this.builder = this.builder;
            EconomicIndicator.this.fetch();
        }

        /**
         * Sets the right builder and makes a synchronous request using
         * {@link EconomicIndicator#fetch()}.
         * <p>
         * When calling this method, any async callbacks will be overwritten.
         *
         * @return the api response
         * @throws AlphaVantageException if the request fails or the response cannot be read
         */
        public EconomicIndicatorResponse fetchSync() throws AlphaVantageException {
            EconomicIndicator.this.builder = this.builder;
            return EconomicIndicator.this.fetchSync();
        }

    }

    /**
     * Proxy for building a {@link RealGdpRequest}.
     */
    public class RealGdpRequestProxy extends RequestProxy<RealGdpRequestProxy> {
        /**
         * Creates a proxy for the {@code REAL_GDP} endpoint.
         */
        public RealGdpRequestProxy() {
            builder = new RealGdpRequest.Builder();
        }

        /**
         * Sets the reporting interval for the series.
         *
         * @param interval the reporting interval; must be {@link Interval#QUARTERLY}
         *                 or {@link Interval#ANNUAL}
         * @return this proxy, for method chaining
         */
        public RealGdpRequestProxy interval(Interval interval){
            builder = ((RealGdpRequest.Builder)builder).interval(interval);
            return this;
        }
    }

    /**
     * Proxy for building a {@link RealGdpPerCapitaRequest}.
     */
    public class RealGdpPerCapitaRequestProxy extends RequestProxy<RealGdpPerCapitaRequestProxy> {
        /**
         * Creates a proxy for the {@code REAL_GDP_PER_CAPITA} endpoint.
         */
        public RealGdpPerCapitaRequestProxy() {
            builder = new RealGdpPerCapitaRequest.Builder();
        }
    }

    /**
     * Proxy for building a {@link TreasuryYieldRequest}.
     */
    public class TreasuryYieldRequestProxy extends RequestProxy<TreasuryYieldRequestProxy> {
        /**
         * Creates a proxy for the {@code TREASURY_YIELD} endpoint.
         */
        public TreasuryYieldRequestProxy() {
            builder = new TreasuryYieldRequest.Builder();
        }

        /**
         * Sets the reporting interval for the series.
         *
         * @param interval the reporting interval; must be {@link Interval#DAILY},
         *                 {@link Interval#WEEKLY}, or {@link Interval#MONTHLY}
         * @return this proxy, for method chaining
         */
        public TreasuryYieldRequestProxy interval(Interval interval){
            builder = ((TreasuryYieldRequest.Builder)builder).interval(interval);
            return this;
        }

        /**
         * Sets the bond maturity the yield is reported for.
         *
         * @param maturity the Treasury bond maturity
         * @return this proxy, for method chaining
         */
        public TreasuryYieldRequestProxy maturity(Maturity maturity){
            builder = ((TreasuryYieldRequest.Builder)builder).maturity(maturity);
            return this;
        }
    }

    /**
     * Proxy for building a {@link FederalFundsRateRequest}.
     */
    public class FederalFundsRateRequestProxy extends RequestProxy<FederalFundsRateRequestProxy> {
        /**
         * Creates a proxy for the {@code FEDERAL_FUNDS_RATE} endpoint.
         */
        public FederalFundsRateRequestProxy() {
            builder = new FederalFundsRateRequest.Builder();
        }

        /**
         * Sets the reporting interval for the series.
         *
         * @param interval the reporting interval; must be {@link Interval#DAILY},
         *                 {@link Interval#WEEKLY}, or {@link Interval#MONTHLY}
         * @return this proxy, for method chaining
         */
        public FederalFundsRateRequestProxy interval(Interval interval){
            builder = ((FederalFundsRateRequest.Builder)builder).interval(interval);
            return this;
        }
    }

    /**
     * Proxy for building a {@link CpiRequest}.
     */
    public class CpiRequestProxy extends RequestProxy<CpiRequestProxy> {
        /**
         * Creates a proxy for the {@code CPI} endpoint.
         */
        public CpiRequestProxy() {
            builder = new CpiRequest.Builder();
        }

        /**
         * Sets the reporting interval for the series.
         *
         * @param interval the reporting interval; must be {@link Interval#MONTHLY}
         *                 or {@link Interval#SEMI_ANNUAL}
         * @return this proxy, for method chaining
         */
        public CpiRequestProxy interval(Interval interval){
            builder = ((CpiRequest.Builder)builder).interval(interval);
            return this;
        }
    }

    /**
     * Proxy for building an {@link InflationRequest}.
     */
    public class InflationRequestProxy extends RequestProxy<InflationRequestProxy> {
        /**
         * Creates a proxy for the {@code INFLATION} endpoint.
         */
        public InflationRequestProxy() {
            builder = new InflationRequest.Builder();
        }
    }

    /**
     * Proxy for building an {@link InflationExpectationRequest}.
     */
    public class InflationExpectationRequestProxy extends RequestProxy<InflationExpectationRequestProxy> {
        /**
         * Creates a proxy for the {@code INFLATION_EXPECTATION} endpoint.
         */
        public InflationExpectationRequestProxy() {
            builder = new InflationExpectationRequest.Builder();
        }
    }

    /**
     * Proxy for building a {@link ConsumerSentimentRequest}.
     */
    public class ConsumerSentimentRequestProxy extends RequestProxy<ConsumerSentimentRequestProxy> {
        /**
         * Creates a proxy for the {@code CONSUMER_SENTIMENT} endpoint.
         */
        public ConsumerSentimentRequestProxy() {
            builder = new ConsumerSentimentRequest.Builder();
        }
    }

    /**
     * Proxy for building a {@link RetailSalesRequest}.
     */
    public class RetailSalesRequestProxy extends RequestProxy<RetailSalesRequestProxy> {
        /**
         * Creates a proxy for the {@code RETAIL_SALES} endpoint.
         */
        public RetailSalesRequestProxy() {
            builder = new RetailSalesRequest.Builder();
        }
    }

    /**
     * Proxy for building a {@link DurableGoodsOrdersRequest}.
     */
    public class DurableGoodsOrdersRequestProxy extends RequestProxy<DurableGoodsOrdersRequestProxy> {
        /**
         * Creates a proxy for the {@code DURABLES} endpoint.
         */
        public DurableGoodsOrdersRequestProxy() {
            builder = new DurableGoodsOrdersRequest.Builder();
        }
    }

    /**
     * Proxy for building an {@link UnemploymentRateRequest}.
     */
    public class UnemploymentRateRequestProxy extends RequestProxy<UnemploymentRateRequestProxy> {
        /**
         * Creates a proxy for the {@code UNEMPLOYMENT} endpoint.
         */
        public UnemploymentRateRequestProxy() {
            builder = new UnemploymentRateRequest.Builder();
        }
    }

    /**
     * Proxy for building a {@link NonFarmPayrollRequest}.
     */
    public class NonFarmPayrollRequestProxy extends RequestProxy<NonFarmPayrollRequestProxy> {
        /**
         * Creates a proxy for the {@code NONFARM_PAYROLL} endpoint.
         */
        public NonFarmPayrollRequestProxy() {
            builder = new NonFarmPayrollRequest.Builder();
        }
    }

}
