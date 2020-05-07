package forex;

import static org.junit.Assert.assertEquals;

import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.forex.request.DailyRequest;
import com.crazzyghost.alphavantage.forex.request.ForexRequest;
import com.crazzyghost.alphavantage.forex.request.IntraDayRequest;
import com.crazzyghost.alphavantage.forex.request.MonthlyRequest;
import com.crazzyghost.alphavantage.forex.request.WeeklyRequest;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;

import org.junit.Test;

public class ForexRequestTest {

    @Test
    public void testDailyRequest() {
        String expected = "https://www.alphavantage.co/query?function=FX_DAILY&outputsize=full&from_symbol=EUR&to_symbol=USD&datatype=json&apikey=demo";
        ForexRequest request = new DailyRequest.Builder()
            .toSymbol("USD")
            .fromSymbol("EUR")
            .dataType(DataType.JSON)
            .outputSize(OutputSize.FULL)
            .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testIntraDayRequest() {
        String expected = "https://www.alphavantage.co/query?interval=5min&function=FX_INTRADAY&outputsize=full&from_symbol=EUR&to_symbol=USD&datatype=json&apikey=demo";
        ForexRequest request = new IntraDayRequest.Builder()
            .toSymbol("USD")
            .fromSymbol("EUR")
            .dataType(DataType.JSON)
            .interval(Interval.FIVE_MIN) 
            .outputSize(OutputSize.FULL)   
            .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");   
    }


    @Test
    public void testWeeklyRequest(){
        String expected = "https://www.alphavantage.co/query?function=FX_WEEKLY&from_symbol=EUR&to_symbol=USD&datatype=json&apikey=demo";
        ForexRequest request = new WeeklyRequest.Builder()
            .toSymbol("USD")
            .fromSymbol("EUR")
            .dataType(DataType.JSON)
            .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testMonthlyRequest(){
        String expected = "https://www.alphavantage.co/query?function=FX_MONTHLY&from_symbol=EUR&to_symbol=USD&datatype=json&apikey=demo";
        ForexRequest request = new MonthlyRequest.Builder()
            .toSymbol("USD")
            .fromSymbol("EUR")
            .dataType(DataType.JSON)
            .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");           
    }

    
}