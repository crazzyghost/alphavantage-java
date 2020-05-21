package forex;

import static org.junit.Assert.assertTrue;
import static util.TestUtils.stream;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;
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

public class MutipleAsyncRequestTest {

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

    @Test 
    public void testMultipleCalls() throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(4);
        AtomicReference<ForexResponse> monthlyRef = new AtomicReference<>();
        AtomicReference<ForexResponse> weeklyRef = new AtomicReference<>();
        AtomicReference<ForexResponse> dailyRef = new AtomicReference<>();
        AtomicReference<ForexResponse> intradayRef = new AtomicReference<>();

        AlphaVantage.api()
            .forex()
            .monthly()
            .fromSymbol("GHS")
            .toSymbol("USD")
            .dataType(DataType.JSON)
            .onSuccess(e -> {
                monthlyRef.set(e);
                lock.countDown();
            })
            .fetch();

        AlphaVantage.api()
            .forex()
            .weekly()
            .fromSymbol("GHS")
            .toSymbol("USD")
            .dataType(DataType.JSON)
            .onSuccess(e -> {
                weeklyRef.set(e);
                lock.countDown();
            })
            .fetch();

        AlphaVantage.api()
            .forex()
            .daily()
            .fromSymbol("EUR")
            .toSymbol("USD")
            .outputSize(OutputSize.FULL)
            .dataType(DataType.JSON)
            .onSuccess(e -> {
                dailyRef.set(e);
                lock.countDown();
            })
            .fetch();
 
        AlphaVantage.api()
            .forex()
            .intraday()
            .toSymbol("USD")
            .fromSymbol("EUR")
            .dataType(DataType.JSON)
            .interval(Interval.FIVE_MIN) 
            .outputSize(OutputSize.FULL) 
            .onSuccess(e -> {
                intradayRef.set(e);
                lock.countDown();
            })
            .fetch();
 
        lock.await();
        
        System.out.println(monthlyRef.get().getMetaData().getInformation());
        System.out.println(weeklyRef.get().getMetaData().getInformation());
        System.out.println(dailyRef.get().getMetaData().getInformation());
        assertTrue(monthlyRef.get().getMetaData().getInformation().matches("Forex Monthly(.*)"));
        assertTrue(weeklyRef.get().getMetaData().getInformation().matches("Forex Weekly(.*)"));
        assertTrue(dailyRef.get().getMetaData().getInformation().matches("Forex Daily(.*)"));
        assertTrue(intradayRef.get().getMetaData().getInformation().matches("FX Intraday.*"));

    }


}