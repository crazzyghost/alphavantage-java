package crypto;

import static org.junit.Assert.assertEquals;

import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.UrlExtractor;
import com.crazzyghost.alphavantage.cryptocurrency.request.CryptoRequest;
import com.crazzyghost.alphavantage.cryptocurrency.request.DigitalCurrencyRequest;
import com.crazzyghost.alphavantage.cryptocurrency.request.RatingRequest;
import com.crazzyghost.alphavantage.parameters.Function;

import org.junit.Test;

public class CryptoRequestTest {


    @Test
    public void testRatingRequest(){
        String expected = "https://www.alphavantage.co/query?function=CRYPTO_RATING&symbol=BTC&apikey=demo";
        CryptoRequest request = new RatingRequest.Builder()
            .symbol("BTC")
            .build();
        
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
            
    }
    
    @Test
    public void testDailyRequest(){
        String expected = "https://www.alphavantage.co/query?market=CNY&function=DIGITAL_CURRENCY_DAILY&symbol=BTC&apikey=demo";
        CryptoRequest request = new DigitalCurrencyRequest.Builder()
            .function(Function.DIGITAL_CURRENCY_DAILY)
            .symbol("BTC")
            .market("CNY")
            .build();
        
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
            
    }

    @Test
    public void testWeeklyRequest(){
        String expected = "https://www.alphavantage.co/query?market=CNY&function=DIGITAL_CURRENCY_WEEKLY&symbol=BTC&apikey=demo";
        CryptoRequest request = new DigitalCurrencyRequest.Builder()
            .function(Function.DIGITAL_CURRENCY_WEEKLY)
            .symbol("BTC")
            .market("CNY")
            .build();
        
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
            
    }

    @Test
    public void testMonthlyRequest(){
        String expected = "https://www.alphavantage.co/query?market=CNY&function=DIGITAL_CURRENCY_MONTHLY&symbol=BTC&apikey=demo";
        CryptoRequest request = new DigitalCurrencyRequest.Builder()
            .function(Function.DIGITAL_CURRENCY_MONTHLY)
            .symbol("BTC")
            .market("CNY")
            .build();
        
        assertEquals(expected, Config.BASE_URL + UrlExtractor.extract(request) + "demo");
            
    }


    
}