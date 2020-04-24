import java.util.concurrent.CountDownLatch;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;

import org.junit.BeforeClass;

public class TestIndicator {

    // static Config config;

    private CountDownLatch lock = new CountDownLatch(1);

    @BeforeClass
    public static void setUp(){
        Config config = Config
            .builder()
            .timeOut(10)
            .key("M77PQNYAVBG0MB5N")
            .build();

        AlphaVantage.api().init(config);
    }

 

}