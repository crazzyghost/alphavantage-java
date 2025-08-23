package timeseries;

import static org.junit.Assert.assertEquals;

import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;
import com.crazzyghost.alphavantage.timeseries.request.*;

import org.junit.Test;

public class TimeSeriesRequestTest {

    @Test
    public void testDailyRequest() {
        String expected = "https://www.alphavantage.co/query?outputsize=full&function=TIME_SERIES_DAILY&symbol=IBM&datatype=json&apikey=demo";
        TimeSeriesRequest request = new DailyRequest.Builder()
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .outputSize(OutputSize.FULL)
            .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }


    @Test
    public void testWeeklyRequest(){
        String expected = "https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=IBM&datatype=json&apikey=demo";
        TimeSeriesRequest request = new WeeklyRequest.Builder()
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testMonthlyRequest(){
        String expected = "https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=IBM&datatype=json&apikey=demo";
        TimeSeriesRequest request = new MonthlyRequest.Builder()
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");           
    }


    @Test
    public void testDailyRequestAdjusted() {
        String expected = "https://www.alphavantage.co/query?outputsize=full&function=TIME_SERIES_DAILY_ADJUSTED&symbol=IBM&datatype=json&apikey=demo";
        TimeSeriesRequest request = new DailyRequest.Builder()
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .outputSize(OutputSize.FULL)
            .adjusted()
            .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }


    @Test
    public void testWeeklyRequestAdjusted(){
        String expected = "https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY_ADJUSTED&symbol=IBM&datatype=json&apikey=demo";
        TimeSeriesRequest request = new WeeklyRequest.Builder()
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .adjusted()
            .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testMonthlyRequestAdjusted(){
        String expected = "https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY_ADJUSTED&symbol=IBM&datatype=json&apikey=demo";
        TimeSeriesRequest request = new MonthlyRequest.Builder()
            .forSymbol("IBM")
            .adjusted()
            .dataType(DataType.JSON)
            .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");           
    }

    
    @Test
    public void testIntraDayRequest() {
        String expected = "https://www.alphavantage.co/query?interval=5min&outputsize=full&adjusted=false&extended_hours=false&function=TIME_SERIES_INTRADAY&symbol=IBM&datatype=json&apikey=demo";
        TimeSeriesRequest request = new IntraDayRequest.Builder()
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .interval(Interval.FIVE_MIN) 
            .outputSize(OutputSize.FULL)   
            .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testIntraDayRequestAdjusted() {
        String expected = "https://www.alphavantage.co/query?interval=5min&outputsize=full&adjusted=true&extended_hours=false&function=TIME_SERIES_INTRADAY&symbol=IBM&datatype=json&apikey=demo";
        TimeSeriesRequest request = new IntraDayRequest.Builder()
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .interval(Interval.FIVE_MIN)
            .outputSize(OutputSize.FULL)
            .adjusted()
            .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testIntraDayRequestExtendedHours() {
        String expected = "https://www.alphavantage.co/query?interval=5min&outputsize=full&adjusted=false&extended_hours=true&function=TIME_SERIES_INTRADAY&symbol=IBM&datatype=json&apikey=demo";
        TimeSeriesRequest request = new IntraDayRequest.Builder()
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .interval(Interval.FIVE_MIN)
            .outputSize(OutputSize.FULL)
            .extendedHours()
            .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testIntraDayRequestMonth() {
        String expected = "https://www.alphavantage.co/query?interval=5min&outputsize=full&adjusted=false&extended_hours=false&month=2023-11&function=TIME_SERIES_INTRADAY&symbol=IBM&datatype=json&apikey=demo";
        TimeSeriesRequest request = new IntraDayRequest.Builder()
                .forSymbol("IBM")
                .dataType(DataType.JSON)
                .interval(Interval.FIVE_MIN)
                .outputSize(OutputSize.FULL)
                .month("2023-11")
                .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testGlobalQuoteRequest() {
        String expected = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=IBM&datatype=json&apikey=demo";
        TimeSeriesRequest request = new QuoteRequest.Builder()
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");   
    }

    @Test
    public void testRealtimeBulkQuoteRequest() {
        String expected = "https://www.alphavantage.co/query?function=REALTIME_BULK_QUOTES&symbol=IBM,MSFT&datatype=json&apikey=demo";
        TimeSeriesRequest request = new RealtimeBulkQuoteRequest.Builder()
                .forSymbol("IBM")
                .forSymbol("MSFT")
                .dataType(DataType.JSON)
                .build();
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
    }



}