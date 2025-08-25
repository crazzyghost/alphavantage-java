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

    public static String emptyMessage = "{}";

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

    public static <U> U parseJSON(String filename, Class<U> c) throws IOException {
        Moshi moshi = new Moshi.Builder().build();
        Type type = Types.getRawType(c);
        JsonAdapter<U> adapter = moshi.adapter(type);
        return adapter.fromJson(getJson(filename));
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
     * Mock an error response
     * @return
     * @throws IOException
     */
    public static Map<String,Object> error() throws IOException {
        return getJsonAdapter().fromJson(errorMessage);
    }

    /**
     * Mock an empty response
     * @return
     * @throws IOException
     */
    public static Map<String,Object> empty() throws IOException {
        return getJsonAdapter().fromJson(emptyMessage);
    }

    public static String sectorUrl(){
        return Config.BASE_URL + "function=SECTOR&apikey=demo";
    }

    public static String exchangeRateUrl(final String toCurrency){
        String currency = toCurrency == null ? "CNY" : toCurrency;
        return Config.BASE_URL + "function=CURRENCY_EXCHANGE_RATE&from_currency=BTC&to_currency="+currency+"&apikey=demo";
    };

    public static String cryptoUrl(final String function, final String symbol){
        String sym = symbol == null ? "BTC" : symbol;
        return Config.BASE_URL + "market=CNY&function=DIGITAL_CURRENCY_"+ function.toUpperCase() + "&symbol=" + sym +"&apikey=demo";
    }

    public static String cryptoIntradayUrl(final String symbol){
        String sym = symbol == null ? "BTC" : symbol;
        return Config.BASE_URL + "market=CNY&function=CRYPTO_INTRADAY" + "&symbol=" + sym +"&apikey=demo";
    }

    public static String cryptoRatingUrl(final String symbol){
        String sym = symbol == null ? "BTC" : symbol;
        return Config.BASE_URL + "function=CRYPTO_RATING&symbol=" + sym + "&apikey=demo";
    };

    public static String getSimpleIndicatorRequestUrl(String function){
        return Config.BASE_URL + "function=" + function + "&symbol=IBM&interval=weekly&datatype=json&apikey=demo";
    }

    public static String getSimpleIndicatorRequestUrl(String function, String symbol){
        return Config.BASE_URL + "function=" + function + "&symbol="+ symbol +"&interval=weekly&datatype=json&apikey=demo";
    }

    public static String getSimpleTechnicalIndicatorRequestUrl(String function){
        return Config.BASE_URL + "function=" + function + "&symbol=IBM&interval=weekly&datatype=json&apikey=demo";
    }

    public static String getSimpleTechnicalIndicatorRequestUrl(String function, String symbol){
        return Config.BASE_URL + "function=" + function + "&symbol="+ symbol +"&interval=weekly&datatype=json&apikey=demo";
    }


    public static String getPeriodicSeriesUrl(String function){
       return Config.BASE_URL + "series_type=open&time_period=60&function=" + function + "&symbol=IBM&interval=weekly&datatype=json&apikey=demo";
    }

    public static String getPeriodicSeriesUrl(String function, String symbol){
        return Config.BASE_URL + "series_type=open&time_period=60&function=" + function + "&symbol=" + symbol + "&interval=weekly&datatype=json&apikey=demo";
     }

    public static String getPeriodicUrl(String function){
        return Config.BASE_URL + "time_period=60&function=" + function + "&symbol=IBM&interval=daily&datatype=json&apikey=demo";
    }
 
    public static String getPeriodicUrl(String function, String symbol){
        return Config.BASE_URL + "time_period=60&function=" + function + "&symbol=" + symbol + "&interval=daily&datatype=json&apikey=demo";
    }

    public static String getSeriesUrl(String function){
        return Config.BASE_URL + "series_type=open&function=" + function + "&symbol=IBM&interval=daily&datatype=json&apikey=demo";
    }
 
    public static String getSeriesUrl(String function, String symbol){
        return Config.BASE_URL + "series_type=open&function=" + function + "&symbol=" + symbol + "&interval=daily&datatype=json&apikey=demo";
    }


    public static String getMAMAUrl(String symbol){
        String sym = symbol == null ? "IBM" : symbol;
        return Config.BASE_URL + "series_type=open&fastlimit=0.1&slowlimit=0.5&function=MAMA&symbol="+sym+"&interval=weekly&datatype=json&apikey=demo";
    }

    public static String getMACDUrl(String symbol){
        String sym = symbol == null ? "IBM" : symbol;
        return Config.BASE_URL + "series_type=open&fastperiod=12&slowperiod=26&signalperiod=9&function=MACD&symbol="+ sym +"&interval=daily&datatype=json&apikey=demo";
    }


    public static String getMACDEXTUrl(final String symbol){
        String sym = symbol == null ? "IBM" : symbol;
        return Config.BASE_URL + "series_type=open&fastperiod=12&slowperiod=26&signalperiod=9&fastmatype=8&slowmatype=0&signalmatype=0&function=MACDEXT&symbol="+sym+"&interval=daily&datatype=json&apikey=demo";
    }

    public static String getSTOCHUrl(final String symbol){
        String sym = symbol == null ? "IBM" : symbol;
        return Config.BASE_URL + "fastkperiod=5&slowkperiod=3&slowdperiod=3&slowkmatype=0&slowdmatype=0&function=STOCH&symbol="+sym+"&interval=60min&datatype=json&apikey=demo";
    }

    public static String getSTOCHFUrl(final String symbol){
        String sym = symbol == null ? "IBM" : symbol;
        return Config.BASE_URL + "fastkperiod=5&fastdperiod=3&fastdmatype=8&function=STOCHF&symbol="+sym+"&interval=60min&datatype=json&apikey=demo";
    }

    public static String getSTOCHRSIUrl(final String symbol){
        String sym = symbol == null ? "IBM" : symbol;
        return Config.BASE_URL + "time_period=60&series_type=open&fastkperiod=5&fastdperiod=3&fastdmatype=8&function=STOCHRSI&symbol="+sym+"&interval=60min&datatype=json&apikey=demo";
    }

    public static String getULTOSCUrl(final String symbol){
        String sym = symbol == null ? "IBM" : symbol;
        return Config.BASE_URL + "timeperiod1=7&timeperiod2=14&timeperiod3=28&function=ULTOSC&symbol="+ sym + "&interval=60min&datatype=json&apikey=demo";
    }

    public static String getPriceOscillatorUrl(String function){
        return Config.BASE_URL + "series_type=open&fastperiod=10&slowperiod=26&matype=8&function=" + function +"&symbol=IBM&interval=daily&datatype=json&apikey=demo";
    }

    public static String getPriceOscillatorUrl(String function, String symbol){
        return Config.BASE_URL + "series_type=open&fastperiod=10&slowperiod=26&matype=8&function=" + function +"&symbol="+ symbol + "&interval=daily&datatype=json&apikey=demo";
    }

    public static String getBBANDSUrl(final String symbol){
        String sym = symbol == null ? "IBM" : symbol;
        return Config.BASE_URL + "series_type=open&time_period=60&nbdevup=4&nbdevdn=4&matype=0&function=BBANDS&symbol="+ sym +"&interval=daily&datatype=json&apikey=demo";    
    }

    public static String getSARUrl(final String symbol){
        String sym = symbol == null ? "IBM" : symbol;
        return Config.BASE_URL + "acceleration=0.02&maximum=0.5&function=SAR&symbol="+ sym +"&interval=daily&datatype=json&apikey=demo";    
    }

    public static String getADOSCUrl(final String symbol){
        String sym = symbol == null ? "IBM" : symbol;
        return Config.BASE_URL + "fastperiod=3&slowperiod=10&function=ADOSC&symbol="+ sym +"&interval=daily&datatype=json&apikey=demo";    
    }

    public static String marketStatusUrl() {
        return  Config.BASE_URL + "function=MARKET_STATUS&apikey=demo";
    }


}

