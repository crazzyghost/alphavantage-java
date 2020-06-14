package timeseries;

import static org.junit.Assert.assertNotNull;
import static util.TestUtils.errorMessage;
import static util.TestUtils.stream;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.AlphaVantageException;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.OutputSize;
import com.crazzyghost.alphavantage.timeseries.TimeSeries;
import com.crazzyghost.alphavantage.timeseries.response.QuoteResponse;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;

import org.junit.Before;
import org.junit.Test;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okhttp3.mock.Behavior;
import okhttp3.mock.MockInterceptor;
import util.TestUtils;


public class TimeSeriesSyncTest {

    MockInterceptor mockInterceptor = new MockInterceptor(Behavior.RELAYED);
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    Config config;

    @Before
    public void setUp() throws IOException {
        TestUtils.forDirectory("timeseries");

        loggingInterceptor.level(Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(4, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(mockInterceptor)
            .build();

        config = Config.builder()
            .key("demo")
            .httpClient(client)
            .build();

        AlphaVantage.api().init(config);

    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigNotSet() {
        new TimeSeries(null)
            .daily()
            .forSymbol("USD")
            .fetchSync();
    }

    @Test(expected = AlphaVantageException.class)
    public void testConfigKeyNotSet() {
        new TimeSeries(Config.builder().build())
            .daily()
            .forSymbol("USD")
            .fetchSync();
    }

    @Test
    public void testGlobalQuoteSync() throws IOException {
        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=IBM&datatype=json&apikey=demo")
            .respond(stream("globalquote"));

        QuoteResponse response = AlphaVantage.api()
            .timeSeries()
            .quote()
            .forSymbol("IBM")
            .fetchSync();
            
        assertNotNull(response);
    }

    @Test
    public void testDailySync() throws IOException{
        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?outputsize=full&function=TIME_SERIES_DAILY&symbol=IBM&datatype=json&apikey=demo")
            .respond(stream("daily"));

        TimeSeriesResponse response = AlphaVantage.api()
            .timeSeries()
            .daily()
            .forSymbol("IBM")
            .outputSize(OutputSize.FULL)
            .dataType(DataType.JSON)
            .fetchSync();
            
        assertNotNull(response);
    }

    @Test
    public void testIntradaySync() throws IOException {
        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?interval=5min&outputsize=full&function=TIME_SERIES_INTRADAY&symbol=IBM&datatype=json&apikey=demo")
            .respond(stream("intraday"));
        
        TimeSeriesResponse response = AlphaVantage.api()
            .timeSeries()
            .intraday()
            .forSymbol("IBM")
            .outputSize(OutputSize.FULL)
            .dataType(DataType.JSON)
            .fetchSync();
            
        assertNotNull(response);
    }

    @Test
    public void testWeeklySync()throws IOException {
        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=IBM&datatype=json&apikey=demo")
            .respond(stream("weekly"));

        TimeSeriesResponse response = AlphaVantage.api()
            .timeSeries()
            .weekly()
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .fetchSync();
            
        assertNotNull(response);
    }

    @Test
    public void testMonthlySync() throws IOException {
        mockInterceptor.addRule()
            .get("https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=IBM&datatype=json&apikey=demo")
            .respond(stream("monthly"));

        TimeSeriesResponse response = AlphaVantage.api()
            .timeSeries()
            .monthly()
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .fetchSync();
            
        assertNotNull(response);
    }


}
