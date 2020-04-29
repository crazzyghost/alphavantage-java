package parameters;

import static org.junit.Assert.assertEquals;

import com.crazzyghost.alphavantage.parameters.Interval;

import org.junit.Test;

public class IntervalTest {

    @Test
    public void testOneMin(){
        assertEquals(Interval.ONE_MIN.toString(), "1min");
    }

    @Test
    public void testFiveMin(){
        assertEquals(Interval.FIVE_MIN.toString(), "5min");
    }

    @Test
    public void testFifteenMin(){
        assertEquals(Interval.FIFTEEN_MIN.toString(), "15min");
    }


    @Test
    public void testThirtyMin(){
        assertEquals(Interval.THIRTY_MIN.toString(), "30min");
    }


    @Test
    public void testSixtyMin(){
        assertEquals(Interval.SIXTY_MIN.toString(), "60min");
    }


    @Test
    public void testDaily(){
        assertEquals(Interval.DAILY.toString(), "daily");
    }


    @Test
    public void testWeekly(){
        assertEquals(Interval.WEEKLY.toString(), "weekly");
    }


    @Test
    public void testMonthly(){
        assertEquals(Interval.MONTHLY.toString(), "monthly");
    }


}