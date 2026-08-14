package parameters;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

    @Test
    public void testValuesContent(){
        assertArrayEquals(
            new Entitlement[]{Entitlement.REALTIME, Entitlement.DELAYED},
            Entitlement.values());
    }

    @Test
    public void testValueOfRealtime(){
        assertEquals(Entitlement.REALTIME, Entitlement.valueOf("REALTIME"));
    }

    @Test
    public void testValueOfDelayed(){
        assertEquals(Entitlement.DELAYED, Entitlement.valueOf("DELAYED"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValueOfUnknownThrows(){
        Entitlement.valueOf("realtime");
    }

    @Test
    public void testWireValuesAreDistinct(){
        assertNotEquals(Entitlement.REALTIME.toString(), Entitlement.DELAYED.toString());
    }
}
