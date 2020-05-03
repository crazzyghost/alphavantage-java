package exchangerate;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ExchangeRateTest {
    
    @Before
    public void setUp(){

    }

    @Test
    public void testConfigNotSet(){
        assertEquals(5, 4);
    }

    @Test
    public void testConfigKeyNotSet(){
        assertEquals(5, 4);
    }

    @Test
    public void testResponseFailure(){
        assertEquals(5, 4);
    }


    @Test 
    public void testRequest(){
        assertEquals(5, 4);
    }

    @Test
    public void testResponse(){
        assertEquals(5, 4);
    }

    @Test 
    public void testExchangeRate(){
        assertEquals(5, 4);
    }

    @Test 
    public void testExchangeRateError(){
        assertEquals(5, 4);
    }

    @Test 
    public void testExchangeRateErrorWithoutFailureCallback(){
        assertEquals(5, 4);
    }

}