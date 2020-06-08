package com.crazzyghost.alphavantage.indicator;

import java.io.IOException;
import java.util.Map;

import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.Fetcher;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.indicator.request.*;
import com.crazzyghost.alphavantage.indicator.response.*;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.MAType;
import com.crazzyghost.alphavantage.parameters.SeriesType;
import com.crazzyghost.alphavantage.parser.Parser;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * Access to Technical Indicator Data
 * @author crazzyghost
 * @since 1.1.0
 */
public class Indicator implements Fetcher{

    private IndicatorRequest.Builder<?> builder;
    private Fetcher.SuccessCallback<?> successCallback;
    private Fetcher.FailureCallback failureCallback;
    private  Config config;

    public Indicator(Config config) {
        this.config = config;
    }

    /**
     * Fetch Technical Indicator Data
     */
    @Override
    public void fetch(){

        Config.checkNotNullOrKeyEmpty(config);

        config.getOkHttpClient().newCall(UrlExtractor.extract(builder.build(), config.getKey())).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call,  IOException e) {
                if(failureCallback != null) failureCallback.onFailure(new AlphaVantageException());
            }

            @Override
            public void onResponse(Call call,  Response response) throws IOException {
                if(!response.isSuccessful()){
                    if(failureCallback != null) failureCallback.onFailure(new AlphaVantageException());
                } else {
                    try(ResponseBody body = response.body()){
                        parseIndicatorResponse(Parser.parseJSON(body.string()));
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void parsePeriodicSeriesResponse(Map<String, Object> data){
        PeriodicSeriesResponse response = PeriodicSeriesResponse.of(data, builder.function.name());
        if(response.getErrorMessage() != null) {
            if(failureCallback != null)
                failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<PeriodicSeriesResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseMAMAResponse(Map<String, Object> data) {
        MAMAResponse response = MAMAResponse.of(data);
        if(response.getErrorMessage() != null) {
            if(failureCallback != null)
                failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<MAMAResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseSimpleIndicatorResponse(Map<String, Object> data){
        String functionName = builder.function.name();
        if(functionName.equals("AD")) functionName = "Chaikin A/D";

        SimpleIndicatorResponse response = SimpleIndicatorResponse.of(data, functionName);
        if(response.getErrorMessage() != null) {
            if(failureCallback != null)
                failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<SimpleIndicatorResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseMACDResponse(Map<String, Object> data) {
        MACDResponse response = MACDResponse.of(data);
        if(response.getErrorMessage() != null) {
            if(failureCallback != null)
                failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<MACDResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseMACDEXTResponse(Map<String, Object> data) {
        MACDEXTResponse response = MACDEXTResponse.of(data);
        if(response.getErrorMessage() != null) {
            if(failureCallback != null)
                failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<MACDEXTResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseSTOCHResponse(Map<String, Object> data) {
        STOCHResponse response = STOCHResponse.of(data);
        if(response.getErrorMessage() != null) {
            if(failureCallback != null)
                failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<STOCHResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseSTOCHFResponse(Map<String, Object> data) {
        STOCHFResponse response = STOCHFResponse.of(data);
        if(response.getErrorMessage() != null) {
            if(failureCallback != null)
                failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<STOCHFResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseSTOCHRSIResponse(Map<String, Object> data) {
        STOCHRSIResponse response = STOCHRSIResponse.of(data);
        if(response.getErrorMessage() != null) {
            if(failureCallback != null)
                failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<STOCHRSIResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parsePriceOscillatorResponse(Map<String, Object> data) {
        PriceOscillatorResponse response = PriceOscillatorResponse.of(data, builder.function.name());
        if(response.getErrorMessage() != null) {
            if(failureCallback != null)
                failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<PriceOscillatorResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parsePeriodicResponse(Map<String, Object> data){
        PeriodicResponse response = PeriodicResponse.of(data, builder.function.name());
        if(response.getErrorMessage() != null) {
            if(failureCallback != null)
                failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<PeriodicResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseAROONResponse(Map<String, Object> data){
        AROONResponse response = AROONResponse.of(data);
        if(response.getErrorMessage() != null) {
            if(failureCallback != null)
                failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<AROONResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseULTOSCResponse(Map<String, Object> data){
        ULTOSCResponse response = ULTOSCResponse.of(data);
        if(response.getErrorMessage() != null) {
            if(failureCallback != null)
                failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<ULTOSCResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseBBANDSResponse(Map<String, Object> data){
        BBANDSResponse response = BBANDSResponse.of(data);
        if(response.getErrorMessage() != null) {
            if(failureCallback != null)
                failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<BBANDSResponse>)successCallback).onSuccess(response);
        }
    }


    @SuppressWarnings("unchecked")
    private void parseSARResponse(Map<String, Object> data){
        SARResponse response = SARResponse.of(data);
        if(response.getErrorMessage() != null) {
            if(failureCallback != null)
                failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<SARResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseADOSCResponse(Map<String, Object> data){
        ADOSCResponse response = ADOSCResponse.of(data);
        if(response.getErrorMessage() != null) {
            if(failureCallback != null)
                failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<ADOSCResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseSeriesResponse(Map<String, Object> data){
        String functionName = builder.function.name();
        if(functionName.equals("HT_TRENDMODE")) functionName = "TRENDMODE";
        if(functionName.equals("HT_DCPERIOD")) functionName = "DCPERIOD";
        SeriesResponse response = SeriesResponse.of(data, functionName);
        if(response.getErrorMessage() != null) {
            if(failureCallback != null)
                failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<SeriesResponse>)successCallback).onSuccess(response);
        }
    }


    @SuppressWarnings("unchecked")
    private void parseHTSINEResponse(Map<String, Object> data){
        HTSINEResponse response = HTSINEResponse.of(data);
        if(response.getErrorMessage() != null) {
            if(failureCallback != null)
                failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<HTSINEResponse>)successCallback).onSuccess(response);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseHTPHASORResponse(Map<String, Object> data){
        HTPHASORResponse response = HTPHASORResponse.of(data);
        if(response.getErrorMessage() != null) {
            if(failureCallback != null)
                failureCallback.onFailure(new AlphaVantageException(response.getErrorMessage()));
        }
        if(successCallback != null){
            ((Fetcher.SuccessCallback<HTPHASORResponse>)successCallback).onSuccess(response);
        }
    }

    private void parseIndicatorResponse(Map<String, Object> data){
        
        switch(builder.function){
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
                parseSimpleIndicatorResponse(data);
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


    public PeriodicSeriesRequestProxy sma(){
        return new PeriodicSeriesRequestProxy(Function.SMA);
    }

    public PeriodicSeriesRequestProxy ema(){
        return new PeriodicSeriesRequestProxy(Function.EMA);
    }

    public PeriodicSeriesRequestProxy wma(){
        return new PeriodicSeriesRequestProxy(Function.WMA);
    }

    public PeriodicSeriesRequestProxy dema(){
        return new PeriodicSeriesRequestProxy(Function.DEMA);
    }

    public PeriodicSeriesRequestProxy tema(){
        return new PeriodicSeriesRequestProxy(Function.TEMA);
    }

    public PeriodicSeriesRequestProxy trima(){
        return new PeriodicSeriesRequestProxy(Function.TRIMA);
    }

    public PeriodicSeriesRequestProxy kama(){
        return new PeriodicSeriesRequestProxy(Function.KAMA);
    }

    public MAMARequestProxy mama(){
        return new MAMARequestProxy();
    }

    public PeriodicSeriesRequestProxy t3(){
        return new PeriodicSeriesRequestProxy(Function.T3);
    }

    public SimpleIndicatorRequestProxy<SimpleIndicatorRequestProxy<?>> vwap(){
        return new SimpleIndicatorRequestProxy<>(Function.VWAP);
    }

    public MACDRequestProxy macd(){
        return new MACDRequestProxy();
    }

    public MACDEXTRequestProxy macdext(){
        return new MACDEXTRequestProxy();
    }

    public STOCHRequestProxy stoch(){
        return new STOCHRequestProxy();
    }

    public STOCHFRequestProxy stochf(){
        return new STOCHFRequestProxy();
    }

    public PeriodicSeriesRequestProxy rsi(){
        return new PeriodicSeriesRequestProxy(Function.RSI);
    }

    public STOCHRSIRequestProxy stochrsi(){
        return new STOCHRSIRequestProxy();
    }

    public PeriodicRequestProxy willr(){
        return new PeriodicRequestProxy(Function.WILLR);
    }
    
    public PeriodicRequestProxy adx(){
        return new PeriodicRequestProxy(Function.ADX);
    }

    public PeriodicRequestProxy adxr(){
        return new PeriodicRequestProxy(Function.ADXR);
    }

    public PriceOscillatorRequestProxy apo(){
        return new PriceOscillatorRequestProxy(Function.APO);
    }

    public PriceOscillatorRequestProxy ppo(){
        return new PriceOscillatorRequestProxy(Function.PPO);
    }
    
    public PeriodicSeriesRequestProxy mom(){
        return new PeriodicSeriesRequestProxy(Function.MOM);
    }

    public SimpleIndicatorRequestProxy<SimpleIndicatorRequestProxy<?>> bop(){
        return new SimpleIndicatorRequestProxy<>(Function.BOP);
    }

    public PeriodicRequestProxy cci(){
        return new PeriodicRequestProxy(Function.CCI);
    }

    public PeriodicSeriesRequestProxy cmo(){
        return new PeriodicSeriesRequestProxy(Function.CMO);
    }

    public PeriodicSeriesRequestProxy roc(){
        return new PeriodicSeriesRequestProxy(Function.ROC);
    }

    public PeriodicSeriesRequestProxy rocr(){
        return new PeriodicSeriesRequestProxy(Function.ROCR);
    }

    public PeriodicRequestProxy aroon(){
        return new PeriodicRequestProxy(Function.AROON);
    }

    public PeriodicRequestProxy aroonosc(){
        return new PeriodicRequestProxy(Function.AROONOSC);
    }

    public PeriodicRequestProxy mfi(){
        return new PeriodicRequestProxy(Function.MFI);
    }

    public PeriodicSeriesRequestProxy trix(){
        return new PeriodicSeriesRequestProxy(Function.TRIX);
    }

    public ULTOSCRequestProxy ultosc(){
        return new ULTOSCRequestProxy();
    }

    public PeriodicRequestProxy dx(){
        return new PeriodicRequestProxy(Function.DX);
    }

    public PeriodicRequestProxy minusdi(){
        return new PeriodicRequestProxy(Function.MINUS_DI);
    }

    public PeriodicRequestProxy plusdi(){
        return new PeriodicRequestProxy(Function.PLUS_DI);
    }

    public PeriodicRequestProxy minusdm(){
        return new PeriodicRequestProxy(Function.MINUS_DM);
    }

    public PeriodicRequestProxy plusdm(){
        return new PeriodicRequestProxy(Function.PLUS_DM);
    }

    public BBANDSRequestProxy bbands(){
        return new BBANDSRequestProxy();
    }

    public PeriodicSeriesRequestProxy midpoint(){
        return new PeriodicSeriesRequestProxy(Function.MIDPOINT);
    }

    public PeriodicRequestProxy midprice(){
        return new PeriodicRequestProxy(Function.MIDPRICE);
    }

    public SARRequestProxy sar(){
        return new SARRequestProxy();
    }

    public SimpleIndicatorRequestProxy<SimpleIndicatorRequestProxy<?>> trange(){
        return new SimpleIndicatorRequestProxy<>(Function.TRANGE);
    }

    public PeriodicRequestProxy atr(){
        return new PeriodicRequestProxy(Function.ATR);
    }

    public PeriodicRequestProxy natr(){
        return new PeriodicRequestProxy(Function.NATR);
    }

    public SimpleIndicatorRequestProxy<SimpleIndicatorRequestProxy<?>> ad(){
        return new SimpleIndicatorRequestProxy<>(Function.AD);
    }

    public ADOSCRequestProxy adosc(){
        return new ADOSCRequestProxy();
    }

    public SimpleIndicatorRequestProxy<SimpleIndicatorRequestProxy<?>> obv(){
        return new SimpleIndicatorRequestProxy<>(Function.OBV);
    }

    public SeriesRequestProxy httrendline(){
        return new SeriesRequestProxy(Function.HT_TRENDLINE);
    }

    public SeriesRequestProxy htsine(){
        return new SeriesRequestProxy(Function.HT_SINE);
    }
    
    public SeriesRequestProxy httrendmode(){
        return new SeriesRequestProxy(Function.HT_TRENDMODE);
    }

    public SeriesRequestProxy htdcphase(){
        return new SeriesRequestProxy(Function.HT_DCPHASE);
    }

    public SeriesRequestProxy htdcperiod(){
        return new SeriesRequestProxy(Function.HT_DCPERIOD);
    }

    public SeriesRequestProxy htphasor(){
        return new SeriesRequestProxy(Function.HT_PHASOR);
    }


    /**
     * An base proxy for building requests. Adds the functionality of adding callbacks and a terminal method for 
     * fetching data.
     * @param <T> A Concrete {@link SimpleIndicatorRequestProxy} Implementation
     */
    @SuppressWarnings("unchecked")
    public class SimpleIndicatorRequestProxy<T extends SimpleIndicatorRequestProxy<?>> {
        protected IndicatorRequest.Builder<?> builder;
        
        public SimpleIndicatorRequestProxy(){
        
        }
        
        public SimpleIndicatorRequestProxy( Function function){
            builder = new SimpleIndicatorRequest.Builder(); 
            builder = builder.function(function);       
        }

        public T dataType( DataType dataType){
            builder = builder.dataType(dataType);
            return (T)this;
        }

        public T forSymbol( String symbol){
            builder = builder.forSymbol(symbol);
            return (T)this;
        }

        public T interval( Interval interval){
            builder = builder.interval(interval);
            return (T)this;
        }

        public T onSuccess( Fetcher.SuccessCallback<?> callback){
            Indicator.this.successCallback =  callback;
            return (T)this;
        }

        public T onFailure( Fetcher.FailureCallback callback){
            Indicator.this.failureCallback = callback;
            return (T)this;
        }

        public void fetch(){
            Indicator.this.builder = builder;
            Indicator.this.fetch();
        }

    }


    public class PeriodicSeriesRequestProxy extends SimpleIndicatorRequestProxy<PeriodicSeriesRequestProxy>{
 
        public PeriodicSeriesRequestProxy( Function function){
            builder = new PeriodicSeriesRequest.Builder(); 
            builder = builder.function(function);
        }

        public PeriodicSeriesRequestProxy timePeriod( int period){
            builder = ((PeriodicSeriesRequest.Builder)builder).timePeriod(period);
            return this;
        }

        public PeriodicSeriesRequestProxy seriesType( SeriesType series){
            builder = ((PeriodicSeriesRequest.Builder)builder).seriesType(series);
            return this;
        }
    }

    public class PeriodicRequestProxy extends SimpleIndicatorRequestProxy<PeriodicRequestProxy>{
 
        public PeriodicRequestProxy( Function function){
            builder = new PeriodicRequest.Builder(); 
            builder = builder.function(function);
        }

        public PeriodicRequestProxy timePeriod( int period){
            builder = ((PeriodicRequest.Builder)builder).timePeriod(period);
            return this;
        }
    }

    public class SeriesRequestProxy extends SimpleIndicatorRequestProxy<SeriesRequestProxy>{
 
        public SeriesRequestProxy( Function function){
            builder = new SeriesRequest.Builder(); 
            builder = builder.function(function);   
        }

        public SeriesRequestProxy seriesType( SeriesType series){
            builder = ((SeriesRequest.Builder)builder).seriesType(series);
            return this;
        }
    }

    public class MAMARequestProxy extends SimpleIndicatorRequestProxy<MAMARequestProxy>{
 
        public MAMARequestProxy(){
            builder = new MAMARequest.Builder(); 
        }

        public MAMARequestProxy fastLimit( double fastLimit){
            builder = ((MAMARequest.Builder)builder).fastLimit(fastLimit);
            return this;
        }

        public MAMARequestProxy seriesType( SeriesType series){
            builder = ((MAMARequest.Builder)builder).seriesType(series);
            return this;
        }

        public MAMARequestProxy slowLimit( double slowLimit){
            builder = ((MAMARequest.Builder)builder).slowLimit(slowLimit);
            return this;
        }
    }

    public class MACDRequestProxy extends SimpleIndicatorRequestProxy<MACDRequestProxy>{
 
        public MACDRequestProxy(){
            builder = new MACDRequest.Builder(); 
        }

        public MACDRequestProxy fastPeriod( int fastLimit){
            builder = ((MACDRequest.Builder)builder).fastPeriod(fastLimit);
            return this;
        }

        public MACDRequestProxy slowPeriod( int slowPeriod){
            builder = ((MACDRequest.Builder)builder).slowPeriod(slowPeriod);
            return this;
        }

        public MACDRequestProxy signalPeriod( int signalPeriod){
            builder = ((MACDRequest.Builder)builder).signalPeriod(signalPeriod);
            return this;
        }

        public MACDRequestProxy seriesType( SeriesType series){
            builder = ((MACDRequest.Builder)builder).seriesType(series);
            return this;
        }
    }

    public class MACDEXTRequestProxy extends SimpleIndicatorRequestProxy<MACDEXTRequestProxy>{
 
        public MACDEXTRequestProxy(){
            builder = new MACDEXTRequest.Builder();
        }

        public MACDEXTRequestProxy fastPeriod( int period){
            builder = ((MACDEXTRequest.Builder)builder).fastPeriod(period);
            return this;
        }

        public MACDEXTRequestProxy slowPeriod( int period){
            builder = ((MACDEXTRequest.Builder)builder).slowPeriod(period);
            return this;
        }

        public MACDEXTRequestProxy signalPeriod( int period){
            builder = ((MACDEXTRequest.Builder)builder).signalPeriod(period);
            return this;
        }

        public MACDEXTRequestProxy fastMaType( MAType type){
            builder = ((MACDEXTRequest.Builder)builder).fastMaType(type);
            return this;
        }

        public MACDEXTRequestProxy slowMaType( MAType type){
            builder = ((MACDEXTRequest.Builder)builder).slowMaType(type);
            return this;
        }

        public MACDEXTRequestProxy signalMaType( MAType type){
            builder = ((MACDEXTRequest.Builder)builder).signalMaType(type);
            return this;
        }

        public MACDEXTRequestProxy seriesType( SeriesType series){
            builder = ((MACDEXTRequest.Builder)builder).seriesType(series);
            return this;
        }
    }

    public class STOCHRequestProxy extends SimpleIndicatorRequestProxy<STOCHRequestProxy>{
 
        public STOCHRequestProxy(){
            builder = new STOCHRequest.Builder();            
        }

        public STOCHRequestProxy fastKPeriod( int period){
            builder = ((STOCHRequest.Builder)builder).fastKPeriod(period);
            return this;
        }

        public STOCHRequestProxy slowKPeriod( int period){
            builder = ((STOCHRequest.Builder)builder).slowKPeriod(period);
            return this;
        }
 
        public STOCHRequestProxy slowDPeriod( int period){
            builder = ((STOCHRequest.Builder)builder).slowDPeriod(period);
            return this;
        }

        public STOCHRequestProxy slowKMaType( MAType type){
            builder = ((STOCHRequest.Builder)builder).slowKMaType(type);
            return this;
        }

        public STOCHRequestProxy slowDMaType( MAType type){
            builder = ((STOCHRequest.Builder)builder).slowDMaType(type);
            return this;
        }
    }

    public class STOCHFRequestProxy extends SimpleIndicatorRequestProxy<STOCHFRequestProxy>{
 
        public STOCHFRequestProxy(){
            builder = new STOCHFRequest.Builder();
        }

        public STOCHFRequestProxy fastKPeriod( int period){
            builder = ((STOCHFRequest.Builder)builder).fastKPeriod(period);
            return this;
        }

        public STOCHFRequestProxy fastDPeriod( int period){
            builder = ((STOCHFRequest.Builder)builder).fastDPeriod(period);
            return this;
        }
 
        public STOCHFRequestProxy fastDMaType( MAType type){
            builder = ((STOCHFRequest.Builder)builder).fastDMaType(type);
            return this;
        }
    }

    public class STOCHRSIRequestProxy extends SimpleIndicatorRequestProxy<STOCHRSIRequestProxy>{
 
        public STOCHRSIRequestProxy(){
            builder = new STOCHRSIRequest.Builder();
        }

        public STOCHRSIRequestProxy fastKPeriod( int period){
            builder = ((STOCHRSIRequest.Builder)builder).fastKPeriod(period);
            return this;
        }

        public STOCHRSIRequestProxy fastDPeriod( int period){
            builder = ((STOCHRSIRequest.Builder)builder).fastDPeriod(period);
            return this;
        }
 
        public STOCHRSIRequestProxy fastDMaType( MAType type){
            builder = ((STOCHRSIRequest.Builder)builder).fastDMaType(type);
            return this;
        }

        public STOCHRSIRequestProxy timePeriod( int period){
            builder = ((STOCHRSIRequest.Builder)builder).timePeriod(period);
            return this;
        }

        public STOCHRSIRequestProxy seriesType( SeriesType series){
            builder = ((STOCHRSIRequest.Builder)builder).seriesType(series);
            return this;
        }
    }

    public class PriceOscillatorRequestProxy extends SimpleIndicatorRequestProxy<PriceOscillatorRequestProxy>{
 
        public PriceOscillatorRequestProxy( Function function){
            builder = new PriceOscillatorRequest.Builder(); 
            builder = builder.function(function); 
        }

        public PriceOscillatorRequestProxy fastPeriod( int period){
            builder = ((PriceOscillatorRequest.Builder)builder).fastPeriod(period);
            return this;
        }

        public  PriceOscillatorRequestProxy slowPeriod( int period){
            builder = ((PriceOscillatorRequest.Builder)builder).slowPeriod(period);
            return this;
        }

        public  PriceOscillatorRequestProxy seriesType( SeriesType series){
            builder = ((PriceOscillatorRequest.Builder)builder).seriesType(series);
            return this;
        }

        public  PriceOscillatorRequestProxy maType( MAType type){
            builder = ((PriceOscillatorRequest.Builder)builder).maType(type);
            return this;
        }
    }

    public class ULTOSCRequestProxy extends SimpleIndicatorRequestProxy<ULTOSCRequestProxy> {
        
        public ULTOSCRequestProxy(){
            builder = new ULTOSCRequest.Builder();
        }

        public ULTOSCRequestProxy timePeriod1( int period){
            builder = ((ULTOSCRequest.Builder)builder).timePeriod1(period);
            return this;
        }

        public ULTOSCRequestProxy timePeriod2( int period){
            builder = ((ULTOSCRequest.Builder)builder).timePeriod2(period);
            return this;
        }
        
        public ULTOSCRequestProxy timePeriod3( int period){
            builder = ((ULTOSCRequest.Builder)builder).timePeriod3(period);
            return this;
        }
    }

    public class BBANDSRequestProxy extends SimpleIndicatorRequestProxy<BBANDSRequestProxy>{
 
        public BBANDSRequestProxy(){
            builder = new BBANDSRequest.Builder(); 
        }

        public BBANDSRequestProxy nbdevup( int dev){
            builder = ((BBANDSRequest.Builder)builder).nbdevup(dev);
            return this;            
        }

        public BBANDSRequestProxy nbdevdn( int dev){
            builder = ((BBANDSRequest.Builder)builder).nbdevdn(dev);
            return this;            
        }

        public BBANDSRequestProxy maType(MAType type){
            builder = ((BBANDSRequest.Builder)builder).maType(type);
            return this;            
        }

        public BBANDSRequestProxy timePeriod( int period){
            builder = ((BBANDSRequest.Builder)builder).timePeriod(period);
            return this;
        }

        public BBANDSRequestProxy seriesType( SeriesType series){
            builder = ((BBANDSRequest.Builder)builder).seriesType(series);
            return this;
        }
    }


    public class SARRequestProxy extends SimpleIndicatorRequestProxy<SARRequestProxy>{
 
        public SARRequestProxy(){
            builder = new SARRequest.Builder(); 
        }

        public SARRequestProxy acceleration( double acceleration){
            builder = ((SARRequest.Builder)builder).acceleration(acceleration);
            return this;
        }

        public SARRequestProxy maximum( double maximum){
            builder = ((SARRequest.Builder)builder).maximum(maximum);
            return this;
        }
        
    }

    public class ADOSCRequestProxy extends SimpleIndicatorRequestProxy<ADOSCRequestProxy>{
 
        public ADOSCRequestProxy(){
            builder = new ADOSCRequest.Builder(); 
        }

        public ADOSCRequestProxy fastPeriod( int period){
            builder = ((ADOSCRequest.Builder)builder).fastPeriod(period);
            return this;
        }

        public  ADOSCRequestProxy slowPeriod( int period){
            builder = ((ADOSCRequest.Builder)builder).slowPeriod(period);
            return this;
        }

    }

}