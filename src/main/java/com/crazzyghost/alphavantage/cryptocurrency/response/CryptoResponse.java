package com.crazzyghost.alphavantage.cryptocurrency.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CryptoResponse {


    private List<CryptoUnit> cryptoUnits;
    private MetaData metaData;
    private String errorMessage;


    public CryptoResponse(MetaData metaData, List<CryptoUnit> cryptoUnits){
        this.metaData = metaData;
        this.cryptoUnits = cryptoUnits;
        this.errorMessage = null;
    }

    public CryptoResponse(String errorMessage){
        this.metaData = MetaData.empty();
        this.cryptoUnits = new ArrayList<>();
        this.errorMessage = errorMessage;
    }


    public List<CryptoUnit> getCryptoUnits() {
        return cryptoUnits;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static CryptoResponse of(Map<String, Object> stringObjectMap, String market){
        Parser parser = new Parser();
        return parser.parse(stringObjectMap, market);
    }

    public static class Parser {


        CryptoResponse parse(Map<String, Object> stringObjectMap, String market) {

            //get the keys
            List<String> keys = new ArrayList<>(stringObjectMap.keySet());

            Map<String, String> md;
            Map<String, Map<String, String>> stockData;

            try{
                md = (Map<String, String>) stringObjectMap.get(keys.get(0));
                stockData = (Map<String, Map<String,String>>) stringObjectMap.get(keys.get(1));

            }catch (ClassCastException e){
                return new CryptoResponse((String)stringObjectMap.get(keys.get(0)));
            }


            MetaData metaData = new MetaData(
                    md.get("1. Information"),
                    md.get("2. Digital Currency Code"),
                    md.get("3. Digital Currency Name"),
                    md.get("4. Market Code"),
                    md.get("5. Market Name"),
                    md.get("6. Last Refreshed"),
                    md.get("7. Time Zone")
            );

            List<CryptoUnit> cryptoUnits =  new ArrayList<>();

            for (Map<String,String> m: stockData.values()) {

                CryptoUnit.Builder unit = CryptoUnit.builder();
                unit.open(Double.parseDouble(m.get("1a. open (" + market + ")" )));
                unit.high(Double.parseDouble(m.get("2a. high (" + market + ")" )));
                unit.low(Double.parseDouble(m.get("3a. low (" + market + ")" )));
                unit.close(Double.parseDouble(m.get("4a. close (" + market + ")")));
                unit.openUSD(Double.parseDouble(m.get("1b. open (USD)")));
                unit.highUSD(Double.parseDouble(m.get("2b. high (USD)")));
                unit.lowUSD(Double.parseDouble(m.get("3b. low (USD)")));
                unit.closeUSD(Double.parseDouble(m.get("4b. close (USD)")));
                unit.volume(Double.parseDouble(m.get("5. volume")));
                unit.marketCap(Double.parseDouble(m.get("6. market cap (" + market + ")")));

                cryptoUnits.add(unit.build());
            }
            return  new CryptoResponse(metaData, cryptoUnits);
        }
    }


    @Override
    public String toString() {
        return "CryptoResponse{" +
                "cryptoUnits=" + cryptoUnits.size() +
                ", metaData=" + metaData +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
