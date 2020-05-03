import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.crazzyghost.alphavantage.Config;

import org.junit.Test;

public class ConfigTest {
    
    @Test 
    public void testDefaultHttpClient(){
        Config cfg = Config.builder().build();
        assertNotNull(cfg.getOkHttpClient());
    }

    @Test 
    public void testConfigKey(){
        Config cfg = Config.builder().key("demo").build();
        assertEquals(cfg.getKey(), "demo");
    }

    @Test 
    public void testConfigTimeout(){
        Config cfg = Config.builder().timeOut(15).key("demo").build();
        assertEquals(cfg.getTimeOut(), 15);
    }

}