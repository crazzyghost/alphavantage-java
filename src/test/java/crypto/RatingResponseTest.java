package crypto;

import com.crazzyghost.alphavantage.cryptocurrency.response.RatingResponse;
import org.junit.Before;
import org.junit.Test;
import util.TestUtils;

import java.io.IOException;

import static org.junit.Assert.*;
import static util.TestUtils.empty;

public class RatingResponseTest {
    @Before
    public void setUp(){
        TestUtils.forDirectory("crypto");
    }

    @Test
    public void testEmptyRatingResponseError() throws IOException {
        RatingResponse response = RatingResponse.of(empty());
        assertNotNull(response.getErrorMessage());
        assertFalse(response.toString().matches("(.*), errorMessage='null'(.*)"));
    }
}
