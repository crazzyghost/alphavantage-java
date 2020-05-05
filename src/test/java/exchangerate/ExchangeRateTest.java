package exchangerate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import util.TestUtils;
import static util.TestUtils.json;
import static util.TestUtils.stream;
import static util.TestUtils.error;
import static util.TestUtils.errorMessage;
import static util.TestUtils.exchangeRateUrl;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.exchangerate.ExchangeRate;
import com.crazzyghost.alphavantage.exchangerate.ExchangeRateRequest;
import com.crazzyghost.alphavantage.exchangerate.ExchangeRateResponse;

import org.junit.Before;
import org.junit.Test;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okhttp3.mock.Behavior;
import okhttp3.mock.MockInterceptor;

public class ExchangeRateTest {
 
    MockInterceptor mockInterceptor = new MockInterceptor(Behavior.UNORDERED);
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    Config config;

    @Before
    public void setUp() throws IOException {
        TestUtils.forDirectory("exchangerate");
        
        loggingInterceptor.level(Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(4, TimeUnit.SECONDS)
            .callTimeout(4, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(mockInterceptor)
            .build();

        config = Config.builder()
            .key("demo")
            .httpClient(client)
            .build();
        
        AlphaVantage.api().init(config);

        mockInterceptor.addRule().get(exchangeRateUrl(null)).respond(stream("data"));
        mockInterceptor.addRule().get(exchangeRateUrl(null)).respond(errorMessage);
        mockInterceptor.addRule().get(exchangeRateUrl("USD")).respond(errorMessage).code(400);
        mockInterceptor.addRule().get(exchangeRateUrl("GHS")).delay(6000).respond(errorMessage);

    }

    
    @Test 
    public void testRequest(){

        String expected = "function=CURRENCY_EXCHANGE_RATE&from_currency=BTC&to_currency=CNY&apikey=demo";        
        ExchangeRateRequest  request = ExchangeRateRequest.builder()
            .fromCurrency("BTC")
            .toCurrency("CNY")
            .build();
        assertEquals(expected, UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testResponse() throws IOException{
        ExchangeRateResponse response = ExchangeRateResponse.of(json("data"));
        System.out.println(response.toString());
        assertEquals(response.getExchangeRate(),62423.68125, 0.0);
        assertEquals(response.getBidPrice(),62423.68125, 0.0);
        assertEquals(response.getAskPrice(),62423.751885, 0.0);
        assertEquals(response.getFromCurrencyCode(), "BTC");
        assertEquals(response.getFromCurrencyName(), "Bitcoin");
        assertEquals(response.getToCurrencyCode(), "CNY");
        assertEquals(response.getToCurrencyName(), "Chinese Yuan");
        assertNotNull(response.getLastRefreshed());
        assertNotNull(response.getTimeZone());
        assertTrue(response.toString().matches("(.*), errorMessage='null'(.*)"));
    }

    @Test
    public void testResponseNoBidOrAskPrice() throws IOException{
        ExchangeRateResponse response = ExchangeRateResponse.of(json("data-with-no-bid-or-ask-price"));
        assertNull(response.getBidPrice());
        assertNull(response.getAskPrice());
    }

    @Test
    public void testResponseError() throws IOException{
        ExchangeRateResponse response = ExchangeRateResponse.of(error());
        assertNotNull(response.getErrorMessage());
    }


    @Test(expected = AlphaVantageException.class)
    public void testConfigNotSet(){
        new ExchangeRate(null)
            .fromCurrency("BTC")
            .toCurrency("CNY")
            .fetch();
    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigKeyNotSet(){
        new ExchangeRate(Config.builder().build())
            .fromCurrency("BTC")
            .toCurrency("CNY")
            .fetch();
    }

    @Test 
    public void testExchangeRate() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<ExchangeRateResponse> ref = new AtomicReference<>();

        AlphaVantage.api()
            .exchangeRate()
            .fromCurrency("BTC")
            .toCurrency("CNY")
            .onSuccess((e)->{
                lock.countDown();
                ref.set(e);
            })
            .fetch();
        
        lock.await(); 
        assertNotNull(ref.get());
    }

    @Test 
    public void testExchangeRateError() throws InterruptedException{
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage.api()
            .exchangeRate()
            .fromCurrency("BTC")
            .toCurrency("CNY")
            // .onSuccess(e->lock.countDown())
            .onFailure(e->{
                lock.countDown();
                ref.set(e);
            })
            .fetch();
        
        lock.await(); 
        assertNotNull(ref.get());    
    }

    @Test 
    public void testExchangeRateUnsuccessful() throws InterruptedException{
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage.api()
            .exchangeRate()
            .fromCurrency("BTC")
            .toCurrency("USD")
            // .onSuccess(e->lock.countDown())
            .onFailure(e->{
                lock.countDown();
                ref.set(e);
            })
            .fetch();
        
        lock.await(); 
        assertNotNull(ref.get());    
    }

    @Test 
    public void testExchangeRateFailure() throws InterruptedException{
        CountDownLatch lock = new CountDownLatch(1);
        AtomicReference<AlphaVantageException> ref = new AtomicReference<>();

        AlphaVantage.api()
            .exchangeRate()
            .fromCurrency("BTC")
            .toCurrency("GHS")
            .onSuccess(e->lock.countDown())
            .onFailure(e->{
                lock.countDown();
                ref.set(e);
            })
            .fetch();
        
        lock.await(); 
        assertNotNull(ref.get());    
    }


}