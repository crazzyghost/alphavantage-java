import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.indicator.Indicator;
import com.crazzyghost.alphavantage.parameters.DataType;
import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.SeriesType;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestIndicator {

    static Config config;

    @BeforeClass
    public static void setUp(){
        config = Config
            .builder()
            .timeOut(10)
            .key("demo")
            .build();
    }

    @Test
    public void testPeriodicalSeries(){

        Indicator indicator = new Indicator(config);
        Indicator.PeriodicalSeriesRequestProxy sma = indicator.sma();
            
        sma.interval(Interval.THIRTY_MIN)
            .forSymbol("IBM")
            .seriesType(SeriesType.CLOSE)
            .timePeriod(200)
            .onSuccess((e)->{})
            .fetch();
    }

    @Test
    public void testSimpleIndicatorRequest(){
        Indicator indicator = new Indicator(config);
        indicator.vwap()
        .interval(Interval.THIRTY_MIN)
            .forSymbol("IBM")
            .fetch();        
    }
}