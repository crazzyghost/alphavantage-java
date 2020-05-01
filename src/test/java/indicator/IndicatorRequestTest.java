package indicator;

import static org.junit.Assert.assertEquals;

import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.indicator.request.IndicatorRequest;
import com.crazzyghost.alphavantage.indicator.request.MACDEXTRequest;
import com.crazzyghost.alphavantage.parameters.Function;
import com.crazzyghost.alphavantage.parameters.MAType;
import com.crazzyghost.alphavantage.parameters.SeriesType;

import org.junit.Test;

public class IndicatorRequestTest {
    
    @Test
    public void testMACDEXTRequest(){
        String expected = "series_type=open&fastperiod=12&slowperiod=26&signalperiod=9&fastmatype=8&slowmatype=0&signalmatype=0&function=MACDEXT&symbol=IBM&interval=daily&datatype=json&apikey=demo";
        
        IndicatorRequest request = new MACDEXTRequest
            .Builder()
            .function(Function.MACDEXT)
            .seriesType(SeriesType.OPEN)
            .fastPeriod(12)
            .slowPeriod(26)
            .signalPeriod(9)
            .slowMaType(MAType.SMA)
            .fastMaType(MAType.MAMA)
            .signalMaType(MAType.SMA)
            .forSymbol("IBM")
            .build();

        assertEquals(expected, UrlExtractor.extract(request) + "demo");
    }
}