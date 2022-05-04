package economicindicator;

import com.crazzyghost.alphavantage.economicindicator.response.EconomicIndicatorResponse;
import com.crazzyghost.alphavantage.economicindicator.response.EconomicIndicatorUnit;
import org.junit.Before;
import org.junit.Test;
import util.TestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static util.TestUtils.*;

import java.io.IOException;

public class EconomicIndicatorResponseTest {
    @Before
    public void setUp() {
        TestUtils.forDirectory("economicindicator");
    }

    @Test
    public void testEconomicIndicatorUnit() {
        EconomicIndicatorUnit unit = new EconomicIndicatorUnit("2022-05-02", 2000D);
        assertEquals(unit.getDate(), "2022-05-02");
        assertEquals(unit.getValue(), 2000D, 0.0);
    }

    @Test
    public void testEconomicIndicatorResponse() throws IOException {
        EconomicIndicatorResponse response = EconomicIndicatorResponse.of(json("realgdp"));
        assertNotNull(response.getInterval());
        assertNotNull(response.getName());
        assertNotNull(response.getUnit());
        assertEquals(response.getData().size(), 2);
        assertNull(response.getErrorMessage());

    }

    @Test
    public void testEconomicIndicatorResponseError() throws IOException {
        EconomicIndicatorResponse response = EconomicIndicatorResponse.of(error());
        assertNotNull(response.getErrorMessage());
        assertNotNull(response.toString());
    }

    @Test
    public void testEconomicIndicatorResponseEmptyResponse() throws IOException {
        EconomicIndicatorResponse response = EconomicIndicatorResponse.of(empty());
        assertNotNull(response.getErrorMessage());
        assertNotNull(response.toString());
    }
}
