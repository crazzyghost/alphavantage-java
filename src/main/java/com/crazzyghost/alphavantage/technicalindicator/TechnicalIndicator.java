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
package com.crazzyghost.alphavantage.technicalindicator;

import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.Fetcher;
import com.crazzyghost.alphavantage.RequestExecutor;
import com.crazzyghost.alphavantage.ResponseDispatcher;
import com.crazzyghost.alphavantage.parameters.*;
import com.crazzyghost.alphavantage.technicalindicator.request.*;
import com.crazzyghost.alphavantage.technicalindicator.response.*;
import com.crazzyghost.alphavantage.technicalindicator.response.ad.ADResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.adosc.ADOSCResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.adx.ADXResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.adxr.ADXRResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.apo.APOResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.aroon.AROONResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.aroonosc.AROONOSCResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.atr.ATRResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.bbands.BBANDSResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.bop.BOPResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.cci.CCIResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.cmo.CMOResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.dema.DEMAResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.dx.DXResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.ema.EMAResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.htdcperiod.HTDCPERIODResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.htdcphase.HTDCPHASEResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.htphasor.HTPHASORResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.htsine.HTSINEResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.httrendline.HTTRENDLINEResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.httrendmode.HTTRENDMODEResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.kama.KAMAResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.macd.MACDEXTResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.macd.MACDResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.mama.MAMAResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.mfi.MFIResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.midpoint.MIDPOINTResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.midprice.MIDPRICEResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.minusdi.MINUSDIResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.minusdm.MINUSDMResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.mom.MOMResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.natr.NATRResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.obv.OBVResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.plusdi.PLUSDIResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.plusdm.PLUSDMResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.ppo.PPOResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.roc.ROCResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.rocr.ROCRResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.rsi.RSIResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.sar.SARResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.sma.SMAResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.stoch.STOCHResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.stochf.STOCHFResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.stochrsi.STOCHRSIResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.t3.T3Response;
import com.crazzyghost.alphavantage.technicalindicator.response.tema.TEMAResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.trange.TRANGEResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.trima.TRIMAResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.trix.TRIXResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.ultosc.ULTOSCResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.vwap.VWAPResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.willr.WILLRResponse;
import com.crazzyghost.alphavantage.technicalindicator.response.wma.WMAResponse;

import java.util.Map;

/**
 * Access to the technical indicator endpoints — moving averages, oscillators, momentum, volatility,
 * cycle and Hilbert transform studies — each exposed as a request proxy that is built up fluently
 * and then fetched.
 *
 * <p>This facade supersedes the older {@link com.crazzyghost.alphavantage.indicator.Indicator},
 * whose accessor {@code AlphaVantage.indicator()} is deprecated in favour of {@code
 * AlphaVantage.technicalIndicator()}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 */
public final class TechnicalIndicator implements Fetcher {

    private TechnicalIndicatorRequest.Builder<?> builder;
    private Fetcher.SuccessCallback<?> successCallback;
    private Fetcher.FailureCallback failureCallback;
    private final Config config;

    public TechnicalIndicator(Config config) {
        this.config = config;
    }

    /**
     * Fetches technical indicator data asynchronously, dispatching the parsed response to the
     * callback registered on the request proxy.
     */
    @Override
    public void fetch() {
        RequestExecutor.fetchAsync(
                config, builder.build(), this::parseTechnicalIndicatorResponse, failureCallback);
    }

    /**
     * Makes a blocking synchronous http request to fetch the data. This is called by {@link
     * SimpleTechnicalIndicatorRequestProxy#fetchSync()}.
     *
     * <p>On Android this will throw {@code NetworkOnMainThreadException}. In that case the call
     * should be made on another thread.
     *
     * <p>Using this method will overwrite any async callback.
     *
     * @param successCallback internally used {@link SuccessCallback} that receives the parsed
     *     response
     * @throws AlphaVantageException if the request fails or the response cannot be read
     * @since 1.4.1
     */
    private void fetchSync(SuccessCallback<?> successCallback) throws AlphaVantageException {
        this.successCallback = successCallback;
        this.failureCallback = null;
        parseTechnicalIndicatorResponse(RequestExecutor.fetchSync(config, builder.build()));
    }

    private void parsePeriodicSeriesResponse(Map<String, Object> data) {
        PeriodicSeriesResponse response = null;
        switch (builder.getFunction()) {
            case SMA:
                response = SMAResponse.of(data);
                break;
            case EMA:
                response = EMAResponse.of(data);
                break;
            case WMA:
                response = WMAResponse.of(data);
                break;
            case DEMA:
                response = DEMAResponse.of(data);
                break;
            case TEMA:
                response = TEMAResponse.of(data);
                break;
            case TRIMA:
                response = TRIMAResponse.of(data);
                break;
            case KAMA:
                response = KAMAResponse.of(data);
                break;
            case T3:
                response = T3Response.of(data);
                break;
            case RSI:
                response = RSIResponse.of(data);
                break;
            case MOM:
                response = MOMResponse.of(data);
                break;
            case CMO:
                response = CMOResponse.of(data);
                break;
            case ROC:
                response = ROCResponse.of(data);
                break;
            case ROCR:
                response = ROCRResponse.of(data);
                break;
            case TRIX:
                response = TRIXResponse.of(data);
                break;
            case MIDPOINT:
                response = MIDPOINTResponse.of(data);
            default:
                break;
        }

        ResponseDispatcher.dispatch(response, successCallback, failureCallback);
    }

    private void parseMAMAResponse(Map<String, Object> data) {
        ResponseDispatcher.dispatch(MAMAResponse.of(data), successCallback, failureCallback);
    }

    private void parseSimpleTechnicalIndicatorResponse(Map<String, Object> data) {

        SimpleTechnicalIndicatorResponse response = null;
        switch (builder.getFunction()) {
            case VWAP:
                response = VWAPResponse.of(data);
                break;
            case BOP:
                response = BOPResponse.of(data);
                break;
            case TRANGE:
                response = TRANGEResponse.of(data);
                break;
            case AD:
                response = ADResponse.of(data);
                break;
            case OBV:
                response = OBVResponse.of(data);
            default:
                break;
        }

        ResponseDispatcher.dispatch(response, successCallback, failureCallback);
    }

    private void parseMACDResponse(Map<String, Object> data) {
        ResponseDispatcher.dispatch(MACDResponse.of(data), successCallback, failureCallback);
    }

    private void parseMACDEXTResponse(Map<String, Object> data) {
        ResponseDispatcher.dispatch(MACDEXTResponse.of(data), successCallback, failureCallback);
    }

    private void parseSTOCHResponse(Map<String, Object> data) {
        ResponseDispatcher.dispatch(STOCHResponse.of(data), successCallback, failureCallback);
    }

    private void parseSTOCHFResponse(Map<String, Object> data) {
        ResponseDispatcher.dispatch(STOCHFResponse.of(data), successCallback, failureCallback);
    }

    private void parseSTOCHRSIResponse(Map<String, Object> data) {
        ResponseDispatcher.dispatch(STOCHRSIResponse.of(data), successCallback, failureCallback);
    }

    private void parsePriceOscillatorResponse(Map<String, Object> data) {
        PriceOscillatorResponse response = null;
        switch (builder.getFunction()) {
            case APO:
                response = APOResponse.of(data);
                break;
            case PPO:
                response = PPOResponse.of(data);
            default:
                break;
        }
        ResponseDispatcher.dispatch(response, successCallback, failureCallback);
    }

    private void parsePeriodicResponse(Map<String, Object> data) {
        PeriodicResponse response = null;
        switch (builder.getFunction()) {
            case WILLR:
                response = WILLRResponse.of(data);
                break;
            case ADX:
                response = ADXResponse.of(data);
                break;
            case ADXR:
                response = ADXRResponse.of(data);
                break;
            case CCI:
                response = CCIResponse.of(data);
                break;
            case AROONOSC:
                response = AROONOSCResponse.of(data);
                break;
            case MFI:
                response = MFIResponse.of(data);
                break;
            case DX:
                response = DXResponse.of(data);
                break;
            case MINUS_DI:
                response = MINUSDIResponse.of(data);
                break;
            case PLUS_DI:
                response = PLUSDIResponse.of(data);
                break;
            case MINUS_DM:
                response = MINUSDMResponse.of(data);
                break;
            case PLUS_DM:
                response = PLUSDMResponse.of(data);
                break;
            case MIDPRICE:
                response = MIDPRICEResponse.of(data);
                break;
            case ATR:
                response = ATRResponse.of(data);
                break;
            case NATR:
                response = NATRResponse.of(data);
            default:
                break;
        }
        ResponseDispatcher.dispatch(response, successCallback, failureCallback);
    }

    private void parseAROONResponse(Map<String, Object> data) {
        ResponseDispatcher.dispatch(AROONResponse.of(data), successCallback, failureCallback);
    }

    private void parseULTOSCResponse(Map<String, Object> data) {
        ResponseDispatcher.dispatch(ULTOSCResponse.of(data), successCallback, failureCallback);
    }

    private void parseBBANDSResponse(Map<String, Object> data) {
        ResponseDispatcher.dispatch(BBANDSResponse.of(data), successCallback, failureCallback);
    }

    private void parseSARResponse(Map<String, Object> data) {
        ResponseDispatcher.dispatch(SARResponse.of(data), successCallback, failureCallback);
    }

    private void parseADOSCResponse(Map<String, Object> data) {
        ResponseDispatcher.dispatch(ADOSCResponse.of(data), successCallback, failureCallback);
    }

    private void parseSeriesResponse(Map<String, Object> data) {
        SeriesResponse response = null;
        switch (builder.getFunction()) {
            case HT_TRENDLINE:
                response = HTTRENDLINEResponse.of(data);
                break;
            case HT_TRENDMODE:
                response = HTTRENDMODEResponse.of(data);
                break;
            case HT_DCPERIOD:
                response = HTDCPERIODResponse.of(data);
                break;
            case HT_DCPHASE:
                response = HTDCPHASEResponse.of(data);
            default:
                break;
        }
        ResponseDispatcher.dispatch(response, successCallback, failureCallback);
    }

    private void parseHTSINEResponse(Map<String, Object> data) {
        ResponseDispatcher.dispatch(HTSINEResponse.of(data), successCallback, failureCallback);
    }

    private void parseHTPHASORResponse(Map<String, Object> data) {
        ResponseDispatcher.dispatch(HTPHASORResponse.of(data), successCallback, failureCallback);
    }

    private void parseTechnicalIndicatorResponse(Map<String, Object> data) {

        switch (builder.getFunction()) {
            case SMA:
            case EMA:
            case WMA:
            case DEMA:
            case TEMA:
            case TRIMA:
            case KAMA:
            case T3:
            case RSI:
            case MOM:
            case CMO:
            case ROC:
            case ROCR:
            case TRIX:
            case MIDPOINT:
                parsePeriodicSeriesResponse(data);
                break;
            case MAMA:
                parseMAMAResponse(data);
                break;
            case VWAP:
            case BOP:
            case TRANGE:
            case AD:
            case OBV:
                parseSimpleTechnicalIndicatorResponse(data);
                break;
            case MACD:
                parseMACDResponse(data);
                break;
            case MACDEXT:
                parseMACDEXTResponse(data);
                break;
            case STOCH:
                parseSTOCHResponse(data);
                break;
            case STOCHF:
                parseSTOCHFResponse(data);
                break;
            case STOCHRSI:
                parseSTOCHRSIResponse(data);
                break;
            case APO:
            case PPO:
                parsePriceOscillatorResponse(data);
                break;
            case WILLR:
            case ADX:
            case ADXR:
            case CCI:
            case AROONOSC:
            case MFI:
            case DX:
            case MINUS_DI:
            case PLUS_DI:
            case MINUS_DM:
            case PLUS_DM:
            case MIDPRICE:
            case ATR:
            case NATR:
                parsePeriodicResponse(data);
                break;
            case AROON:
                parseAROONResponse(data);
                break;
            case ULTOSC:
                parseULTOSCResponse(data);
                break;
            case BBANDS:
                parseBBANDSResponse(data);
                break;
            case SAR:
                parseSARResponse(data);
                break;
            case ADOSC:
                parseADOSCResponse(data);
                break;
            case HT_TRENDLINE:
            case HT_TRENDMODE:
            case HT_DCPERIOD:
            case HT_DCPHASE:
                parseSeriesResponse(data);
                break;
            case HT_SINE:
                parseHTSINEResponse(data);
                break;
            case HT_PHASOR:
                parseHTPHASORResponse(data);
                break;
            default:
                break;
        }
    }

    /**
     * Exposes the simple moving average ({@code SMA}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicSeriesRequest} for the
     * parameter contract.
     *
     * @return a request proxy for {@code SMA}
     */
    public PeriodicSeriesRequestProxy<SMAResponse> sma() {
        return new PeriodicSeriesRequestProxy<>(Function.SMA);
    }

    /**
     * Exposes the exponential moving average ({@code EMA}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicSeriesRequest} for the
     * parameter contract.
     *
     * @return a request proxy for {@code EMA}
     */
    public PeriodicSeriesRequestProxy<EMAResponse> ema() {
        return new PeriodicSeriesRequestProxy<>(Function.EMA);
    }

    /**
     * Exposes the weighted moving average ({@code WMA}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicSeriesRequest} for the
     * parameter contract.
     *
     * @return a request proxy for {@code WMA}
     */
    public PeriodicSeriesRequestProxy<WMAResponse> wma() {
        return new PeriodicSeriesRequestProxy<>(Function.WMA);
    }

    /**
     * Exposes the double exponential moving average ({@code DEMA}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicSeriesRequest} for the
     * parameter contract.
     *
     * @return a request proxy for {@code DEMA}
     */
    public PeriodicSeriesRequestProxy<DEMAResponse> dema() {
        return new PeriodicSeriesRequestProxy<>(Function.DEMA);
    }

    /**
     * Exposes the triple exponential moving average ({@code TEMA}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicSeriesRequest} for the
     * parameter contract.
     *
     * @return a request proxy for {@code TEMA}
     */
    public PeriodicSeriesRequestProxy<TEMAResponse> tema() {
        return new PeriodicSeriesRequestProxy<>(Function.TEMA);
    }

    /**
     * Exposes the triangular moving average ({@code TRIMA}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicSeriesRequest} for the
     * parameter contract.
     *
     * @return a request proxy for {@code TRIMA}
     */
    public PeriodicSeriesRequestProxy<TRIMAResponse> trima() {
        return new PeriodicSeriesRequestProxy<>(Function.TRIMA);
    }

    /**
     * Exposes the Kaufman adaptive moving average ({@code KAMA}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicSeriesRequest} for the
     * parameter contract.
     *
     * @return a request proxy for {@code KAMA}
     */
    public PeriodicSeriesRequestProxy<KAMAResponse> kama() {
        return new PeriodicSeriesRequestProxy<>(Function.KAMA);
    }

    /**
     * Exposes the MESA adaptive moving average ({@code MAMA}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.MAMARequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code MAMA}
     */
    public MAMARequestProxy mama() {
        return new MAMARequestProxy();
    }

    /**
     * Exposes T3, Tillson's triple exponential moving average ({@code T3}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicSeriesRequest} for the
     * parameter contract.
     *
     * @return a request proxy for {@code T3}
     */
    public PeriodicSeriesRequestProxy<T3Response> t3() {
        return new PeriodicSeriesRequestProxy<>(Function.T3);
    }

    /**
     * Exposes the volume weighted average price ({@code VWAP}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.SimpleTechnicalIndicatorRequest} for
     * the parameter contract.
     *
     * @return a request proxy for {@code VWAP}
     */
    public SimpleTechnicalIndicatorRequestProxy<?, VWAPResponse> vwap() {
        return new SimpleTechnicalIndicatorRequestProxy<>(Function.VWAP);
    }

    /**
     * Exposes moving average convergence / divergence ({@code MACD}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.MACDRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code MACD}
     */
    public MACDRequestProxy macd() {
        return new MACDRequestProxy();
    }

    /**
     * Exposes MACD with controllable moving-average type ({@code MACDEXT}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.MACDEXTRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code MACDEXT}
     */
    public MACDEXTRequestProxy macdext() {
        return new MACDEXTRequestProxy();
    }

    /**
     * Exposes the stochastic oscillator ({@code STOCH}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.STOCHRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code STOCH}
     */
    public STOCHRequestProxy stoch() {
        return new STOCHRequestProxy();
    }

    /**
     * Exposes the stochastic fast oscillator ({@code STOCHF}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.STOCHFRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code STOCHF}
     */
    public STOCHFRequestProxy stochf() {
        return new STOCHFRequestProxy();
    }

    /**
     * Exposes the relative strength index ({@code RSI}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicSeriesRequest} for the
     * parameter contract.
     *
     * @return a request proxy for {@code RSI}
     */
    public PeriodicSeriesRequestProxy<RSIResponse> rsi() {
        return new PeriodicSeriesRequestProxy<>(Function.RSI);
    }

    /**
     * Exposes the stochastic relative strength index ({@code STOCHRSI}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.STOCHRSIRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code STOCHRSI}
     */
    public STOCHRSIRequestProxy stochrsi() {
        return new STOCHRSIRequestProxy();
    }

    /**
     * Exposes Williams' %R ({@code WILLR}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code WILLR}
     */
    public PeriodicRequestProxy<WILLRResponse> willr() {
        return new PeriodicRequestProxy<>(Function.WILLR);
    }

    /**
     * Exposes the average directional movement index ({@code ADX}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code ADX}
     */
    public PeriodicRequestProxy<ADXResponse> adx() {
        return new PeriodicRequestProxy<>(Function.ADX);
    }

    /**
     * Exposes the average directional movement index rating ({@code ADXR}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code ADXR}
     */
    public PeriodicRequestProxy<ADXRResponse> adxr() {
        return new PeriodicRequestProxy<>(Function.ADXR);
    }

    /**
     * Exposes the absolute price oscillator ({@code APO}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PriceOscillatorRequest} for the
     * parameter contract.
     *
     * @return a request proxy for {@code APO}
     */
    public PriceOscillatorRequestProxy<APOResponse> apo() {
        return new PriceOscillatorRequestProxy<>(Function.APO);
    }

    /**
     * Exposes the percentage price oscillator ({@code PPO}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PriceOscillatorRequest} for the
     * parameter contract.
     *
     * @return a request proxy for {@code PPO}
     */
    public PriceOscillatorRequestProxy<PPOResponse> ppo() {
        return new PriceOscillatorRequestProxy<>(Function.PPO);
    }

    /**
     * Exposes momentum ({@code MOM}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicSeriesRequest} for the
     * parameter contract.
     *
     * @return a request proxy for {@code MOM}
     */
    public PeriodicSeriesRequestProxy<MOMResponse> mom() {
        return new PeriodicSeriesRequestProxy<>(Function.MOM);
    }

    /**
     * Exposes the balance of power ({@code BOP}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.SimpleTechnicalIndicatorRequest} for
     * the parameter contract.
     *
     * @return a request proxy for {@code BOP}
     */
    public SimpleTechnicalIndicatorRequestProxy<?, BOPResponse> bop() {
        return new SimpleTechnicalIndicatorRequestProxy<>(Function.BOP);
    }

    /**
     * Exposes the commodity channel index ({@code CCI}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code CCI}
     */
    public PeriodicRequestProxy<CCIResponse> cci() {
        return new PeriodicRequestProxy<>(Function.CCI);
    }

    /**
     * Exposes the Chande momentum oscillator ({@code CMO}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicSeriesRequest} for the
     * parameter contract.
     *
     * @return a request proxy for {@code CMO}
     */
    public PeriodicSeriesRequestProxy<CMOResponse> cmo() {
        return new PeriodicSeriesRequestProxy<>(Function.CMO);
    }

    /**
     * Exposes the rate of change ({@code ROC}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicSeriesRequest} for the
     * parameter contract.
     *
     * @return a request proxy for {@code ROC}
     */
    public PeriodicSeriesRequestProxy<ROCResponse> roc() {
        return new PeriodicSeriesRequestProxy<>(Function.ROC);
    }

    /**
     * Exposes the rate of change ratio ({@code ROCR}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicSeriesRequest} for the
     * parameter contract.
     *
     * @return a request proxy for {@code ROCR}
     */
    public PeriodicSeriesRequestProxy<ROCRResponse> rocr() {
        return new PeriodicSeriesRequestProxy<>(Function.ROCR);
    }

    /**
     * Exposes the Aroon indicator ({@code AROON}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code AROON}
     */
    public PeriodicRequestProxy<AROONResponse> aroon() {
        return new PeriodicRequestProxy<>(Function.AROON);
    }

    /**
     * Exposes the Aroon oscillator ({@code AROONOSC}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code AROONOSC}
     */
    public PeriodicRequestProxy<AROONOSCResponse> aroonosc() {
        return new PeriodicRequestProxy<>(Function.AROONOSC);
    }

    /**
     * Exposes the money flow index ({@code MFI}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code MFI}
     */
    public PeriodicRequestProxy<MFIResponse> mfi() {
        return new PeriodicRequestProxy<>(Function.MFI);
    }

    /**
     * Exposes the 1-day rate of change of a triple exponentially smoothed moving average ({@code
     * TRIX}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicSeriesRequest} for the
     * parameter contract.
     *
     * @return a request proxy for {@code TRIX}
     */
    public PeriodicSeriesRequestProxy<TRIXResponse> trix() {
        return new PeriodicSeriesRequestProxy<>(Function.TRIX);
    }

    /**
     * Exposes the ultimate oscillator ({@code ULTOSC}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.ULTOSCRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code ULTOSC}
     */
    public ULTOSCRequestProxy ultosc() {
        return new ULTOSCRequestProxy();
    }

    /**
     * Exposes the directional movement index ({@code DX}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code DX}
     */
    public PeriodicRequestProxy<DXResponse> dx() {
        return new PeriodicRequestProxy<>(Function.DX);
    }

    /**
     * Exposes the minus directional indicator ({@code MINUS_DI}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code MINUS_DI}
     */
    public PeriodicRequestProxy<MINUSDIResponse> minusdi() {
        return new PeriodicRequestProxy<>(Function.MINUS_DI);
    }

    /**
     * Exposes the plus directional indicator ({@code PLUS_DI}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code PLUS_DI}
     */
    public PeriodicRequestProxy<PLUSDIResponse> plusdi() {
        return new PeriodicRequestProxy<>(Function.PLUS_DI);
    }

    /**
     * Exposes the minus directional movement ({@code MINUS_DM}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code MINUS_DM}
     */
    public PeriodicRequestProxy<MINUSDMResponse> minusdm() {
        return new PeriodicRequestProxy<>(Function.MINUS_DM);
    }

    /**
     * Exposes the plus directional movement ({@code PLUS_DM}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code PLUS_DM}
     */
    public PeriodicRequestProxy<PLUSDMResponse> plusdm() {
        return new PeriodicRequestProxy<>(Function.PLUS_DM);
    }

    /**
     * Exposes Bollinger Bands ({@code BBANDS}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.BBANDSRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code BBANDS}
     */
    public BBANDSRequestProxy bbands() {
        return new BBANDSRequestProxy();
    }

    /**
     * Exposes the midpoint ({@code MIDPOINT}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicSeriesRequest} for the
     * parameter contract.
     *
     * @return a request proxy for {@code MIDPOINT}
     */
    public PeriodicSeriesRequestProxy<MIDPOINTResponse> midpoint() {
        return new PeriodicSeriesRequestProxy<>(Function.MIDPOINT);
    }

    /**
     * Exposes the midprice ({@code MIDPRICE}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code MIDPRICE}
     */
    public PeriodicRequestProxy<MIDPRICEResponse> midprice() {
        return new PeriodicRequestProxy<>(Function.MIDPRICE);
    }

    /**
     * Exposes the parabolic SAR ({@code SAR}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.SARRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code SAR}
     */
    public SARRequestProxy sar() {
        return new SARRequestProxy();
    }

    /**
     * Exposes the true range ({@code TRANGE}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.SimpleTechnicalIndicatorRequest} for
     * the parameter contract.
     *
     * @return a request proxy for {@code TRANGE}
     */
    public SimpleTechnicalIndicatorRequestProxy<?, TRANGEResponse> trange() {
        return new SimpleTechnicalIndicatorRequestProxy<>(Function.TRANGE);
    }

    /**
     * Exposes the average true range ({@code ATR}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code ATR}
     */
    public PeriodicRequestProxy<ATRResponse> atr() {
        return new PeriodicRequestProxy<>(Function.ATR);
    }

    /**
     * Exposes the normalized average true range ({@code NATR}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.PeriodicRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code NATR}
     */
    public PeriodicRequestProxy<NATRResponse> natr() {
        return new PeriodicRequestProxy<>(Function.NATR);
    }

    /**
     * Exposes the Chaikin A/D line ({@code AD}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.SimpleTechnicalIndicatorRequest} for
     * the parameter contract.
     *
     * @return a request proxy for {@code AD}
     */
    public SimpleTechnicalIndicatorRequestProxy<?, ADResponse> ad() {
        return new SimpleTechnicalIndicatorRequestProxy<>(Function.AD);
    }

    /**
     * Exposes the Chaikin A/D oscillator ({@code ADOSC}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.ADOSCRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code ADOSC}
     */
    public ADOSCRequestProxy adosc() {
        return new ADOSCRequestProxy();
    }

    /**
     * Exposes on balance volume ({@code OBV}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.SimpleTechnicalIndicatorRequest} for
     * the parameter contract.
     *
     * @return a request proxy for {@code OBV}
     */
    public SimpleTechnicalIndicatorRequestProxy<?, OBVResponse> obv() {
        return new SimpleTechnicalIndicatorRequestProxy<>(Function.OBV);
    }

    /**
     * Exposes the Hilbert transform instantaneous trendline ({@code HT_TRENDLINE}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.SeriesRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code HT_TRENDLINE}
     */
    public SeriesRequestProxy<HTTRENDLINEResponse> httrendline() {
        return new SeriesRequestProxy<>(Function.HT_TRENDLINE);
    }

    /**
     * Exposes the Hilbert transform sine wave ({@code HT_SINE}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.SeriesRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code HT_SINE}
     */
    public SeriesRequestProxy<HTSINEResponse> htsine() {
        return new SeriesRequestProxy<>(Function.HT_SINE);
    }

    /**
     * Exposes the Hilbert transform trend vs cycle mode ({@code HT_TRENDMODE}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.SeriesRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code HT_TRENDMODE}
     */
    public SeriesRequestProxy<HTTRENDMODEResponse> httrendmode() {
        return new SeriesRequestProxy<>(Function.HT_TRENDMODE);
    }

    /**
     * Exposes the Hilbert transform dominant cycle phase ({@code HT_DCPHASE}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.SeriesRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code HT_DCPHASE}
     */
    public SeriesRequestProxy<HTDCPHASEResponse> htdcphase() {
        return new SeriesRequestProxy<>(Function.HT_DCPHASE);
    }

    /**
     * Exposes the Hilbert transform dominant cycle period ({@code HT_DCPERIOD}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.SeriesRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code HT_DCPERIOD}
     */
    public SeriesRequestProxy<HTDCPERIODResponse> htdcperiod() {
        return new SeriesRequestProxy<>(Function.HT_DCPERIOD);
    }

    /**
     * Exposes the Hilbert transform phasor components ({@code HT_PHASOR}). See {@link
     * com.crazzyghost.alphavantage.technicalindicator.request.SeriesRequest} for the parameter
     * contract.
     *
     * @return a request proxy for {@code HT_PHASOR}
     */
    public SeriesRequestProxy<HTPHASORResponse> htphasor() {
        return new SeriesRequestProxy<>(Function.HT_PHASOR);
    }

    /**
     * A base proxy for building requests. Adds the functionality of adding callbacks and a terminal
     * method for fetching data.
     *
     * @param <T> a concrete {@link SimpleTechnicalIndicatorRequestProxy} implementation
     * @param <U> the response type that implementation's terminal fetch returns
     */
    @SuppressWarnings("unchecked")
    public class SimpleTechnicalIndicatorRequestProxy<
            T extends SimpleTechnicalIndicatorRequestProxy<?, U>, U> {

        protected TechnicalIndicatorRequest.Builder<?> builder;
        protected U syncResponse;

        public SimpleTechnicalIndicatorRequestProxy() {}

        public SimpleTechnicalIndicatorRequestProxy(Function function) {
            builder = new SimpleTechnicalIndicatorRequest.Builder();
            builder = builder.function(function);
        }

        public T dataType(DataType dataType) {
            builder = builder.dataType(dataType);
            return (T) this;
        }

        public T forSymbol(String symbol) {
            builder = builder.forSymbol(symbol);
            return (T) this;
        }

        public T interval(Interval interval) {
            builder = builder.interval(interval);
            return (T) this;
        }

        /**
         * Sets the historical intraday window to request, in {@code YYYY-MM} form. Only meaningful
         * for intraday {@link Interval} values.
         *
         * @param month the historical window, in {@code YYYY-MM} form
         * @return this proxy
         */
        public T month(String month) {
            builder = builder.month(month);
            return (T) this;
        }

        /**
         * Sets the data freshness tier for premium Alpha Vantage plans. Controls
         * whether the request fetches realtime or fifteen-minute-delayed data.
         * <p>
         * Requires a premium API key to have any effect. Free keys ignore or reject
         * this parameter. When unset, the parameter is omitted from the request
         * entirely.
         *
         * @param entitlement the freshness tier, {@link Entitlement#REALTIME} or
         *     {@link Entitlement#DELAYED}
         * @return this proxy
         * @since 1.9.0
         */
        public T entitlement(Entitlement entitlement) {
            builder = builder.entitlement(entitlement);
            return (T) this;
        }

        public T onSuccess(Fetcher.SuccessCallback<?> callback) {
            TechnicalIndicator.this.successCallback = callback;
            return (T) this;
        }

        public T onFailure(Fetcher.FailureCallback callback) {
            TechnicalIndicator.this.failureCallback = callback;
            return (T) this;
        }

        public void fetch() {
            TechnicalIndicator.this.builder = builder;
            TechnicalIndicator.this.fetch();
        }

        /**
         * Sets the response received during a synchronous call.
         *
         * @param response the parsed response to hand back to {@link #fetchSync()}
         */
        private void setSyncResponse(U response) {
            this.syncResponse = response;
        }

        /**
         * Sets the right builder and makes a synchronous request using {@link
         * TechnicalIndicator#fetch()}.
         *
         * <p>When calling this method, any async callbacks will be overwritten.
         *
         * @return the api response
         * @throws AlphaVantageException if the request fails or the response cannot be read
         */
        public U fetchSync() throws AlphaVantageException {
            SuccessCallback<U> callback = (e) -> setSyncResponse(e);
            TechnicalIndicator.this.builder = this.builder;
            TechnicalIndicator.this.fetchSync(callback);
            return this.syncResponse;
        }
    }

    public class PeriodicSeriesRequestProxy<T>
            extends SimpleTechnicalIndicatorRequestProxy<PeriodicSeriesRequestProxy<T>, T> {

        public PeriodicSeriesRequestProxy(Function function) {
            builder = new PeriodicSeriesRequest.Builder();
            builder = builder.function(function);
        }

        public PeriodicSeriesRequestProxy<T> timePeriod(int period) {
            builder = ((PeriodicSeriesRequest.Builder) builder).timePeriod(period);
            return this;
        }

        public PeriodicSeriesRequestProxy<T> seriesType(SeriesType series) {
            builder = ((PeriodicSeriesRequest.Builder) builder).seriesType(series);
            return this;
        }
    }

    public class PeriodicRequestProxy<T>
            extends SimpleTechnicalIndicatorRequestProxy<PeriodicRequestProxy<T>, T> {

        public PeriodicRequestProxy(Function function) {
            builder = new PeriodicRequest.Builder();
            builder = builder.function(function);
        }

        public PeriodicRequestProxy<T> timePeriod(int period) {
            builder = ((PeriodicRequest.Builder) builder).timePeriod(period);
            return this;
        }
    }

    public class SeriesRequestProxy<T>
            extends SimpleTechnicalIndicatorRequestProxy<SeriesRequestProxy<T>, T> {

        public SeriesRequestProxy(Function function) {
            builder = new SeriesRequest.Builder();
            builder = builder.function(function);
        }

        public SeriesRequestProxy<T> seriesType(SeriesType series) {
            builder = ((SeriesRequest.Builder) builder).seriesType(series);
            return this;
        }
    }

    public class MAMARequestProxy
            extends SimpleTechnicalIndicatorRequestProxy<MAMARequestProxy, MAMAResponse> {

        public MAMARequestProxy() {
            builder = new MAMARequest.Builder();
        }

        public MAMARequestProxy fastLimit(double fastLimit) {
            builder = ((MAMARequest.Builder) builder).fastLimit(fastLimit);
            return this;
        }

        public MAMARequestProxy seriesType(SeriesType series) {
            builder = ((MAMARequest.Builder) builder).seriesType(series);
            return this;
        }

        public MAMARequestProxy slowLimit(double slowLimit) {
            builder = ((MAMARequest.Builder) builder).slowLimit(slowLimit);
            return this;
        }
    }

    public class MACDRequestProxy
            extends SimpleTechnicalIndicatorRequestProxy<MACDRequestProxy, MACDResponse> {

        public MACDRequestProxy() {
            builder = new MACDRequest.Builder();
        }

        public MACDRequestProxy fastPeriod(int fastLimit) {
            builder = ((MACDRequest.Builder) builder).fastPeriod(fastLimit);
            return this;
        }

        public MACDRequestProxy slowPeriod(int slowPeriod) {
            builder = ((MACDRequest.Builder) builder).slowPeriod(slowPeriod);
            return this;
        }

        public MACDRequestProxy signalPeriod(int signalPeriod) {
            builder = ((MACDRequest.Builder) builder).signalPeriod(signalPeriod);
            return this;
        }

        public MACDRequestProxy seriesType(SeriesType series) {
            builder = ((MACDRequest.Builder) builder).seriesType(series);
            return this;
        }
    }

    public class MACDEXTRequestProxy
            extends SimpleTechnicalIndicatorRequestProxy<MACDEXTRequestProxy, MACDEXTResponse> {

        public MACDEXTRequestProxy() {
            builder = new MACDEXTRequest.Builder();
        }

        public MACDEXTRequestProxy fastPeriod(int period) {
            builder = ((MACDEXTRequest.Builder) builder).fastPeriod(period);
            return this;
        }

        public MACDEXTRequestProxy slowPeriod(int period) {
            builder = ((MACDEXTRequest.Builder) builder).slowPeriod(period);
            return this;
        }

        public MACDEXTRequestProxy signalPeriod(int period) {
            builder = ((MACDEXTRequest.Builder) builder).signalPeriod(period);
            return this;
        }

        public MACDEXTRequestProxy fastMaType(MAType type) {
            builder = ((MACDEXTRequest.Builder) builder).fastMaType(type);
            return this;
        }

        public MACDEXTRequestProxy slowMaType(MAType type) {
            builder = ((MACDEXTRequest.Builder) builder).slowMaType(type);
            return this;
        }

        public MACDEXTRequestProxy signalMaType(MAType type) {
            builder = ((MACDEXTRequest.Builder) builder).signalMaType(type);
            return this;
        }

        public MACDEXTRequestProxy seriesType(SeriesType series) {
            builder = ((MACDEXTRequest.Builder) builder).seriesType(series);
            return this;
        }
    }

    public class STOCHRequestProxy
            extends SimpleTechnicalIndicatorRequestProxy<STOCHRequestProxy, STOCHResponse> {

        public STOCHRequestProxy() {
            builder = new STOCHRequest.Builder();
        }

        public STOCHRequestProxy fastKPeriod(int period) {
            builder = ((STOCHRequest.Builder) builder).fastKPeriod(period);
            return this;
        }

        public STOCHRequestProxy slowKPeriod(int period) {
            builder = ((STOCHRequest.Builder) builder).slowKPeriod(period);
            return this;
        }

        public STOCHRequestProxy slowDPeriod(int period) {
            builder = ((STOCHRequest.Builder) builder).slowDPeriod(period);
            return this;
        }

        public STOCHRequestProxy slowKMaType(MAType type) {
            builder = ((STOCHRequest.Builder) builder).slowKMaType(type);
            return this;
        }

        public STOCHRequestProxy slowDMaType(MAType type) {
            builder = ((STOCHRequest.Builder) builder).slowDMaType(type);
            return this;
        }
    }

    public class STOCHFRequestProxy
            extends SimpleTechnicalIndicatorRequestProxy<STOCHFRequestProxy, STOCHFResponse> {

        public STOCHFRequestProxy() {
            builder = new STOCHFRequest.Builder();
        }

        public STOCHFRequestProxy fastKPeriod(int period) {
            builder = ((STOCHFRequest.Builder) builder).fastKPeriod(period);
            return this;
        }

        public STOCHFRequestProxy fastDPeriod(int period) {
            builder = ((STOCHFRequest.Builder) builder).fastDPeriod(period);
            return this;
        }

        public STOCHFRequestProxy fastDMaType(MAType type) {
            builder = ((STOCHFRequest.Builder) builder).fastDMaType(type);
            return this;
        }
    }

    public class STOCHRSIRequestProxy
            extends SimpleTechnicalIndicatorRequestProxy<STOCHRSIRequestProxy, STOCHRSIResponse> {

        public STOCHRSIRequestProxy() {
            builder = new STOCHRSIRequest.Builder();
        }

        public STOCHRSIRequestProxy fastKPeriod(int period) {
            builder = ((STOCHRSIRequest.Builder) builder).fastKPeriod(period);
            return this;
        }

        public STOCHRSIRequestProxy fastDPeriod(int period) {
            builder = ((STOCHRSIRequest.Builder) builder).fastDPeriod(period);
            return this;
        }

        public STOCHRSIRequestProxy fastDMaType(MAType type) {
            builder = ((STOCHRSIRequest.Builder) builder).fastDMaType(type);
            return this;
        }

        public STOCHRSIRequestProxy timePeriod(int period) {
            builder = ((STOCHRSIRequest.Builder) builder).timePeriod(period);
            return this;
        }

        public STOCHRSIRequestProxy seriesType(SeriesType series) {
            builder = ((STOCHRSIRequest.Builder) builder).seriesType(series);
            return this;
        }
    }

    public class PriceOscillatorRequestProxy<T>
            extends SimpleTechnicalIndicatorRequestProxy<PriceOscillatorRequestProxy<T>, T> {

        public PriceOscillatorRequestProxy(Function function) {
            builder = new PriceOscillatorRequest.Builder();
            builder = builder.function(function);
        }

        public PriceOscillatorRequestProxy<T> fastPeriod(int period) {
            builder = ((PriceOscillatorRequest.Builder) builder).fastPeriod(period);
            return this;
        }

        public PriceOscillatorRequestProxy<T> slowPeriod(int period) {
            builder = ((PriceOscillatorRequest.Builder) builder).slowPeriod(period);
            return this;
        }

        public PriceOscillatorRequestProxy<T> seriesType(SeriesType series) {
            builder = ((PriceOscillatorRequest.Builder) builder).seriesType(series);
            return this;
        }

        public PriceOscillatorRequestProxy<T> maType(MAType type) {
            builder = ((PriceOscillatorRequest.Builder) builder).maType(type);
            return this;
        }
    }

    public class ULTOSCRequestProxy
            extends SimpleTechnicalIndicatorRequestProxy<ULTOSCRequestProxy, ULTOSCResponse> {

        public ULTOSCRequestProxy() {
            builder = new ULTOSCRequest.Builder();
        }

        public ULTOSCRequestProxy timePeriod1(int period) {
            builder = ((ULTOSCRequest.Builder) builder).timePeriod1(period);
            return this;
        }

        public ULTOSCRequestProxy timePeriod2(int period) {
            builder = ((ULTOSCRequest.Builder) builder).timePeriod2(period);
            return this;
        }

        public ULTOSCRequestProxy timePeriod3(int period) {
            builder = ((ULTOSCRequest.Builder) builder).timePeriod3(period);
            return this;
        }
    }

    public class BBANDSRequestProxy
            extends SimpleTechnicalIndicatorRequestProxy<BBANDSRequestProxy, BBANDSResponse> {

        public BBANDSRequestProxy() {
            builder = new BBANDSRequest.Builder();
        }

        public BBANDSRequestProxy nbdevup(int dev) {
            builder = ((BBANDSRequest.Builder) builder).nbdevup(dev);
            return this;
        }

        public BBANDSRequestProxy nbdevdn(int dev) {
            builder = ((BBANDSRequest.Builder) builder).nbdevdn(dev);
            return this;
        }

        public BBANDSRequestProxy maType(MAType type) {
            builder = ((BBANDSRequest.Builder) builder).maType(type);
            return this;
        }

        public BBANDSRequestProxy timePeriod(int period) {
            builder = ((BBANDSRequest.Builder) builder).timePeriod(period);
            return this;
        }

        public BBANDSRequestProxy seriesType(SeriesType series) {
            builder = ((BBANDSRequest.Builder) builder).seriesType(series);
            return this;
        }
    }

    public class SARRequestProxy
            extends SimpleTechnicalIndicatorRequestProxy<SARRequestProxy, SARResponse> {

        public SARRequestProxy() {
            builder = new SARRequest.Builder();
        }

        public SARRequestProxy acceleration(double acceleration) {
            builder = ((SARRequest.Builder) builder).acceleration(acceleration);
            return this;
        }

        public SARRequestProxy maximum(double maximum) {
            builder = ((SARRequest.Builder) builder).maximum(maximum);
            return this;
        }
    }

    public class ADOSCRequestProxy
            extends SimpleTechnicalIndicatorRequestProxy<ADOSCRequestProxy, ADOSCResponse> {

        public ADOSCRequestProxy() {
            builder = new ADOSCRequest.Builder();
        }

        public ADOSCRequestProxy fastPeriod(int period) {
            builder = ((ADOSCRequest.Builder) builder).fastPeriod(period);
            return this;
        }

        public ADOSCRequestProxy slowPeriod(int period) {
            builder = ((ADOSCRequest.Builder) builder).slowPeriod(period);
            return this;
        }
    }
}
