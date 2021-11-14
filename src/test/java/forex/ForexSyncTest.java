package forex;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static util.TestUtils.errorMessage;
import static util.TestUtils.stream;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.forex.Forex;
import com.crazzyghost.alphavantage.forex.response.ForexResponse;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;

import org.junit.Before;
import org.junit.Test;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okhttp3.mock.Behavior;
import okhttp3.mock.MockInterceptor;
import util.TestUtils;

public class ForexSyncTest {

    MockInterceptor mockInterceptor = new MockInterceptor(Behavior.RELAYED);
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    Config config;

    @Before
    public void setUp() throws IOException {
        TestUtils.forDirectory("forex");

        loggingInterceptor.level(Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(4, TimeUnit.SECONDS)
                .callTimeout(4, TimeUnit.SECONDS).addInterceptor(loggingInterceptor).addInterceptor(mockInterceptor)
                .build();

        config = Config.builder().key("demo").httpClient(client).build();

        AlphaVantage.api().init(config);

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=FX_MONTHLY&from_symbol=EUR&to_symbol=USD&datatype=json&apikey=demo")
            .respond(errorMessage)
            .code(400);

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=FX_MONTHLY&from_symbol=GHS&to_symbol=USD&datatype=json&apikey=demo")
            .respond(stream("monthly"));

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=FX_WEEKLY&from_symbol=GHS&to_symbol=USD&datatype=json&apikey=demo")
            .respond(stream("weekly"));

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=FX_DAILY&outputsize=full&from_symbol=EUR&to_symbol=USD&datatype=json&apikey=demo")
            .respond(stream("daily"));

        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?interval=5min&function=FX_INTRADAY&outputsize=full&from_symbol=EUR&to_symbol=USD&datatype=json&apikey=demo")
            .respond(stream("intraday"));
    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigNotSet() {
        new Forex(null)
            .daily()
            .fromSymbol("USD")
            .toSymbol("EUR")
            .fetchSync();
    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigKeyNotSet() {
        new Forex(Config.builder().build())
            .daily()
            .fromSymbol("USD")
            .toSymbol("EUR")
            .fetchSync();
    }


    @Test
    public void testMonthly() throws InterruptedException {

        Forex forex = AlphaVantage.api().forex();
        Forex.MonthlyRequestProxy requestProxy =  forex.monthly();
        
        requestProxy.fromSymbol("GHS")
            .toSymbol("USD")
            .dataType(DataType.JSON);
        
        ForexResponse response = requestProxy.fetchSync();
        
        assertNull(response.getErrorMessage());
    }

    
    @Test
    public void testWeekly() throws InterruptedException {

        Forex forex = AlphaVantage.api().forex();
        Forex.WeeklyRequestProxy requestProxy =  forex.weekly();
        requestProxy.fromSymbol("GHS")
            .toSymbol("USD")
            .dataType(DataType.JSON);
            
        ForexResponse response = requestProxy.fetchSync();
        assertNull(response.getErrorMessage());

    }

    @Test
    public void testDaily() throws InterruptedException {

        Forex forex = AlphaVantage.api().forex();
        Forex.DailyRequestProxy requestProxy = forex.daily();
        requestProxy.fromSymbol("EUR")
            .toSymbol("USD")
            .outputSize(OutputSize.FULL)
            .dataType(DataType.JSON);

        ForexResponse response = requestProxy.fetchSync();
        assertNull(response.getErrorMessage());
    }

    @Test
    public void testMonthlyError() throws InterruptedException {

        Forex forex = AlphaVantage.api().forex();
        Forex.MonthlyRequestProxy requestProxy = forex.monthly();
        requestProxy.fromSymbol("AFN")
            .toSymbol("USD")
            .dataType(DataType.JSON);

        ForexResponse response = requestProxy.fetchSync();
        assertNotNull(response.getErrorMessage());
    
    }


    @Test
    public void testIntraday() throws InterruptedException {

        Forex forex = AlphaVantage.api().forex();
        Forex.IntraDayRequestProxy requestProxy = forex.intraday();
        requestProxy.toSymbol("USD")
            .fromSymbol("EUR")
            .dataType(DataType.JSON)
            .interval(Interval.FIVE_MIN) 
            .outputSize(OutputSize.FULL); 

        ForexResponse response = requestProxy.fetchSync();
        assertNull(response.getErrorMessage());
    
    }

}