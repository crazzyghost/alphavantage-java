/*
 *
 * Copyright (c) 2020 Sylvester Sefa-Yeboah
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.crazzyghost.alphavantage.cryptocurrency.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

/**
 * Crypto Currency Response
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public class CryptoResponse {

    private final List<CryptoUnit> cryptoUnits;
    private final MetaData metaData;
    private final String errorMessage;

    private CryptoResponse(MetaData metaData, List<CryptoUnit> cryptoUnits) {
        this.metaData = metaData;
        this.cryptoUnits = cryptoUnits;
        this.errorMessage = null;
    }

    private CryptoResponse(String errorMessage) {
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

    public static CryptoResponse of(Map<String, Object> stringObjectMap, String market) {
        Parser<CryptoResponse> parser = new CryptoParser(market);
        return parser.parse(stringObjectMap);
    }

    public static class CryptoParser extends DefaultParser<CryptoResponse> {
        
        private final String market;
        
        public CryptoParser(String market){
            this.market = market;
        }

        @Override
        public CryptoResponse onParseError(String error) {
            return new CryptoResponse(error);
        }

        @Override
        public CryptoResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> units) {

            String information = metaDataMap.get("1. Information");
            String code =  metaDataMap.get("2. Digital Currency Code");
            String name = metaDataMap.get("3. Digital Currency Name");
            String marketCode = metaDataMap.get("4. Market Code");
            String marketName = metaDataMap.get("5. Market Name");
            String lastRefreshed = metaDataMap.get("6. Last Refreshed");
            String timeZone = metaDataMap.get("7. Time Zone");

            MetaData metaData = new MetaData(
                information,
                code,
                name,
                marketCode,
                marketName,
                lastRefreshed,
                timeZone
            );

            List<CryptoUnit> cryptoUnits =  new ArrayList<>();

            for (Entry<String,Map<String,String>> entry : units.entrySet()){
                Map<String,String> m = entry.getValue();
                CryptoUnit.Builder cryptoUnit = new CryptoUnit.Builder();
                cryptoUnit.date(entry.getKey());
                cryptoUnit.open(Double.parseDouble(m.get("1a. open (" + market + ")" )));
                cryptoUnit.high(Double.parseDouble(m.get("2a. high (" + market + ")" )));
                cryptoUnit.low(Double.parseDouble(m.get("3a. low (" + market + ")" )));
                cryptoUnit.close(Double.parseDouble(m.get("4a. close (" + market + ")")));
                cryptoUnit.openUSD(Double.parseDouble(m.get("1b. open (USD)")));
                cryptoUnit.highUSD(Double.parseDouble(m.get("2b. high (USD)")));
                cryptoUnit.lowUSD(Double.parseDouble(m.get("3b. low (USD)")));
                cryptoUnit.closeUSD(Double.parseDouble(m.get("4b. close (USD)")));
                cryptoUnit.volume(Double.parseDouble(m.get("5. volume")));
                cryptoUnit.marketCap(Double.parseDouble(m.get("6. market cap (USD)")));
                cryptoUnits.add(cryptoUnit.build());
            }

            return new CryptoResponse(metaData, cryptoUnits);
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
