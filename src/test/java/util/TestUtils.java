package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.Map;

import com.crazzyghost.alphavantage.Config;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import okio.BufferedSource;
import okio.Okio;

public class TestUtils {
    
    private static String directory;

    private TestUtils(){

    }

    public static String errorMessage = "{" + 
        "\"Information\":" + "\"The **demo** API key is for demo purposes only. Please claim your free API key at (https://www.alphavantage.co/support/#api-key) to explore our full API offerings. It takes fewer than 20 seconds, and we are committed to making it free forever.\"" +
    "}";

    /**
     * Get json file as {@link BufferedSource}
     * @param filename
     * @return
     * @throws FileNotFoundException
     */
    private static BufferedSource getJson(String filename) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream(Paths.get("src","test","java", directory, "data", filename + ".json").toFile());
        return Okio.buffer(Okio.source(stream));
    }

    /**
     * Get a moshi json adapter
     * @return
     */
    private static JsonAdapter<Map<String,Object>> getJsonAdapter() {
        final Moshi moshi = new Moshi.Builder().build();
        final Type type = Types.newParameterizedType(Map.class, String.class, Object.class);
        return moshi.adapter(type);
    }

    /**
     * Set the directory to fetch files from
     * @param directory
     */
    public static void forDirectory(String directory){
        TestUtils.directory = directory;
    }

    /**
     * Get json file as a {@link Map}
     * @param filename
     * @return
     * @throws IOException
     */
    public static Map<String,Object> json(String filename) throws IOException {
        return getJsonAdapter().fromJson(getJson(filename));
    }

    /**
     * Get json file as {@link InputStream}
     * @param filename
     * @return
     * @throws IOException
     */
    public static InputStream stream(String filename) throws IOException {
        FileInputStream stream = new FileInputStream(Paths.get("src", "test", "java", directory, "data", filename + ".json").toFile());
        return stream;
    }

    /**
     * Mock and error response
     * @return
     * @throws IOException
     */
    public static Map<String,Object> error() throws IOException {
        return getJsonAdapter().fromJson(errorMessage);
    }

    
    public static String exchangeRateUrl(final String toCurrency){
        String currency = toCurrency == null ? "CNY" : toCurrency;
        return Config.BASE_URL + "function=CURRENCY_EXCHANGE_RATE&from_currency=BTC&to_currency="+currency+"&apikey=demo";    
    };

    public static String cryptoUrl(final String function, final String symbol){
        String sym = symbol == null ? "BTC" : symbol;
        return Config.BASE_URL + "market=CNY&function=DIGITAL_CURRENCY_"+ function.toUpperCase() + "&symbol=" + sym +"&apikey=demo";    
    };

    public static String cryptoRatingUrl(final String symbol){
        String sym = symbol == null ? "BTC" : symbol;
        return Config.BASE_URL + "function=CRYPTO_RATING&symbol=" + sym + "&apikey=demo";    
    };

}