package exchangerate;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static util.TestUtils.errorMessage;
import static util.TestUtils.exchangeRateUrl;
import static util.TestUtils.stream;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.exchangerate.ExchangeRate;
import com.crazzyghost.alphavantage.exchangerate.ExchangeRateResponse;

import org.junit.Before;
import org.junit.Test;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okhttp3.mock.Behavior;
import okhttp3.mock.MockInterceptor;
import util.TestUtils;

public class ExchangeRateSyncTest {
 
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
        mockInterceptor.addRule().get(exchangeRateUrl("EUR")).respond(errorMessage);
        mockInterceptor.addRule().get(exchangeRateUrl("USD")).respond(errorMessage).code(400);
        mockInterceptor.addRule().get(exchangeRateUrl("GHS")).delay(6000).respond(errorMessage);

    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigNotSet(){
        new ExchangeRate(null)
            .fromCurrency("BTC")
            .toCurrency("CNY")
            .fetchSync();
    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigKeyNotSet(){
        new ExchangeRate(Config.builder().build())
            .fromCurrency("BTC")
            .toCurrency("CNY")
            .fetchSync();
    }

    @Test 
    public void testExchangeRate() throws InterruptedException {

        ExchangeRateResponse response = AlphaVantage.api()
            .exchangeRate()
            .fromCurrency("BTC")
            .toCurrency("CNY")
            .fetchSync();
        assertNull(response.getErrorMessage());
    }

    @Test 
    public void testExchangeRateError() throws InterruptedException {

        ExchangeRateResponse response = AlphaVantage.api()
            .exchangeRate()
            .fromCurrency("BTC")
            .toCurrency("EUR")
            .fetchSync();

        assertNotNull(response.getErrorMessage());    
        
    }

    @Test 
    public void testExchangeRateUnsuccessful() throws InterruptedException {
   
        ExchangeRate exchangeRate = AlphaVantage.api().exchangeRate();
        ExchangeRateResponse response = exchangeRate
            .fromCurrency("BTC")
            .toCurrency("USD")
            .fetchSync();
        
        assertNotNull(response.getErrorMessage());    
    }

    @Test(expected = AlphaVantageException.class) 
    public void testExchangeRateFailure() throws InterruptedException{

        ExchangeRate exchangeRate = AlphaVantage.api().exchangeRate();
        ExchangeRateResponse response = exchangeRate
            .fromCurrency("BTC")
            .toCurrency("GHS")
            .fetchSync();
        
        assertNotNull(response.getErrorMessage());    
    }


}