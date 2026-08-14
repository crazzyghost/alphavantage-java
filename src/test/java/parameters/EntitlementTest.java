package parameters;

import static org.junit.Assert.assertEquals;

import com.crazzyghost.alphavantage.parameters.Entitlement;

import org.junit.Test;

public class EntitlementTest {

    @Test 
    public void testRealtime(){
        assertEquals("realtime", Entitlement.REALTIME.toString());
    }

    @Test 
    public void testDelayed(){
        assertEquals("delayed", Entitlement.DELAYED.toString());
    }

    @Test
    public void testValuesCount(){
        assertEquals(2, Entitlement.values().length);
    }
}
