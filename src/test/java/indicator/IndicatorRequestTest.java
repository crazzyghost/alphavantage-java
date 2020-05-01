package indicator;

import static org.junit.Assert.assertEquals;

import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.indicator.request.IndicatorRequest;
import com.crazzyghost.alphavantage.indicator.request.MACDEXTRequest;
import com.crazzyghost.alphavantage.indicator.request.MACDRequest;
import com.crazzyghost.alphavantage.indicator.request.MAMARequest;
import com.crazzyghost.alphavantage.indicator.request.PeriodicRequest;
import com.crazzyghost.alphavantage.indicator.request.PeriodicSeriesRequest;
import com.crazzyghost.alphavantage.indicator.request.PriceOscillatorRequest;
import com.crazzyghost.alphavantage.indicator.request.STOCHFRequest;
import com.crazzyghost.alphavantage.indicator.request.STOCHRSIRequest;
import com.crazzyghost.alphavantage.indicator.request.STOCHRequest;
import com.crazzyghost.alphavantage.indicator.request.SeriesRequest;
import com.crazzyghost.alphavantage.indicator.request.SimpleIndicatorRequest;
import com.crazzyghost.alphavantage.indicator.request.ULTOSCRequest;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.MAType;
import com.crazzyghost.alphavantage.parameters.SeriesType;

import org.junit.Test;

public class IndicatorRequestTest {
    
    @Test
    public void testMACDEXTRequest(){
        String expected = "series_type=open&fastperiod=12&slowperiod=26&signalperiod=9&fastmatype=8&slowmatype=0&signalmatype=0&function=MACDEXT&symbol=IBM&interval=daily&datatype=json&apikey=demo";
        
        IndicatorRequest request = new MACDEXTRequest
            .Builder()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .fastPeriod(12)
            .slowPeriod(26)
            .signalPeriod(9)
            .slowMaType(MAType.SMA)
            .fastMaType(MAType.MAMA)
            .signalMaType(MAType.SMA)
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .build();
        assertEquals(expected, UrlExtractor.extract(request) + "demo");
    }


    @Test
    public void testMACDRequest(){
        String expected = "series_type=open&fastperiod=12&slowperiod=26&signalperiod=9&function=MACD&symbol=IBM&interval=daily&datatype=json&apikey=demo";
        
        IndicatorRequest request = new MACDRequest
            .Builder()
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .fastPeriod(12)
            .slowPeriod(26)
            .signalPeriod(9)
            .forSymbol("IBM")
            .dataType(DataType.JSON)
            .build();
        assertEquals(expected, UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testMAMARequest(){
        String expected = "series_type=open&fastlimit=0.1&slowlimit=0.5&function=MAMA&symbol=IBM&interval=daily&datatype=json&apikey=demo";
        
        IndicatorRequest request = new MAMARequest
            .Builder()
            .interval(Interval.DAILY)
            .fastLimit(0.1)
            .slowLimit(0.5)
            .seriesType(SeriesType.OPEN)
            .forSymbol("IBM")
            .build();
        assertEquals(expected, UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testPeriodicRequest(){
        String expected = "time_period=60&function=DX&symbol=IBM&interval=daily&datatype=json&apikey=demo";
        
        IndicatorRequest request = new PeriodicRequest
            .Builder()
            .function(Function.DX)
            .interval(Interval.DAILY)
            .timePeriod(60)
            .forSymbol("IBM")
            .build();
        assertEquals(expected, UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testPeriodicSeriesRequest(){
        String expected = "series_type=open&time_period=60&function=SMA&symbol=IBM&interval=daily&datatype=json&apikey=demo";
        
        IndicatorRequest request = new PeriodicSeriesRequest
            .Builder()
            .function(Function.SMA)
            .interval(Interval.DAILY)
            .timePeriod(60)
            .seriesType(SeriesType.OPEN)
            .forSymbol("IBM")
            .build();
        assertEquals(expected, UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testPriceOscillatorRequest(){
        String expected = "series_type=open&fastperiod=0.1&slowperiod=0.2&matype=8&function=PPO&symbol=IBM&interval=daily&datatype=json&apikey=demo";
        
        IndicatorRequest request = new PriceOscillatorRequest
            .Builder()
            .function(Function.PPO)
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .maType(MAType.MAMA)
            .fastPeriod(0.1)
            .slowPeriod(0.2)
            .forSymbol("IBM")
            .build();
        assertEquals(expected, UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testSeriesRequest(){
        String expected = "series_type=open&function=HT_TRENDLINE&symbol=IBM&interval=daily&datatype=json&apikey=demo";
        
        IndicatorRequest request = new SeriesRequest
            .Builder()
            .function(Function.HT_TRENDLINE)
            .interval(Interval.DAILY)
            .seriesType(SeriesType.OPEN)
            .forSymbol("IBM")
            .build();
        assertEquals(expected, UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testSimpleIndicatorRequest(){
        String expected = "function=VWAP&symbol=IBM&interval=60min&datatype=json&apikey=demo";
        
        IndicatorRequest request = new SimpleIndicatorRequest
            .Builder()
            .function(Function.VWAP)
            .interval(Interval.SIXTY_MIN)
            .forSymbol("IBM")
            .build();

        assertEquals(expected, UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testSTOCHFRequest(){
        String expected = "fastkperiod=5&fastdperiod=3&fastdmatype=8&function=STOCHF&symbol=IBM&interval=60min&datatype=json&apikey=demo";
        
        IndicatorRequest request = new STOCHFRequest
            .Builder()
            .interval(Interval.SIXTY_MIN)
            .fastKPeriod(5)
            .fastDPeriod(3)
            .fastDMaType(MAType.MAMA)
            .forSymbol("IBM")
            .build();
        System.out.println(UrlExtractor.extract(request));
        assertEquals(expected, UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testSTOCHRequest(){
        String expected = "fastkperiod=5&slowkperiod=3&slowdperiod=3&slowkmatype=0&slowdmatype=0&function=STOCH&symbol=IBM&interval=60min&datatype=json&apikey=demo";
        
        IndicatorRequest request = new STOCHRequest
            .Builder()
            .interval(Interval.SIXTY_MIN)
            .fastKPeriod(5)
            .slowKPeriod(3)
            .slowDPeriod(3)
            .slowKMaType(MAType.SMA)
            .slowDMaType(MAType.SMA)
            .forSymbol("IBM")
            .build();
        assertEquals(expected, UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testSTOCHRSIRequest(){
        String expected = "time_period=60&series_type=open&fastkperiod=5&fastdperiod=3&fastdmatype=8&function=STOCHRSI&symbol=IBM&interval=60min&datatype=json&apikey=demo";
        
        IndicatorRequest request = new STOCHRSIRequest
            .Builder()
            .interval(Interval.SIXTY_MIN)
            .fastKPeriod(5)
            .fastDPeriod(3)
            .fastDMaType(MAType.MAMA)
            .seriesType(SeriesType.OPEN)
            .timePeriod(60)
            .forSymbol("IBM")
            .build();
        System.out.println(UrlExtractor.extract(request));
        assertEquals(expected, UrlExtractor.extract(request) + "demo");
    }

    @Test
    public void testULTOSCRequest(){
        String expected = "timeperiod1=7&timeperiod2=14&timeperiod3=28&function=ULTOSC&symbol=IBM&interval=60min&datatype=json&apikey=demo";
        
        IndicatorRequest request = new ULTOSCRequest
            .Builder()
            .interval(Interval.SIXTY_MIN)
            .timePeriod1(7)
            .timePeriod2(14)
            .timePeriod3(28)
            .forSymbol("IBM")
            .build();
        System.out.println(UrlExtractor.extract(request));
        assertEquals(expected, UrlExtractor.extract(request) + "demo");
    }


}
