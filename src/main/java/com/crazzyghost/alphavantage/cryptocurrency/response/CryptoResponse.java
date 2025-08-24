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

import com.crazzyghost.alphavantage.parameters.Interval;
import com.crazzyghost.alphavantage.parameters.OutputSize;
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

    public static CryptoResponse of(Map<String, Object> stringObjectMap) {
        Parser<CryptoResponse> parser = new CryptoParser();
        return parser.parse(stringObjectMap);
    }

    public static class CryptoParser extends DefaultParser<CryptoResponse> {
        public CryptoParser() {
        }

        @Override
        public CryptoResponse onParseError(String error) {
            return new CryptoResponse(error);
        }

        @Override
        public CryptoResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> units) {

            String informationKey = "1. Information";
            String digitalCurrencyCodeKey = "2. Digital Currency Code";
            String digitalCurrencyNameKey = "3. Digital Currency Name";
            String marketCodeKey = "4. Market Code";
            String marketNameKey = "5. Market Name";
            String lastRefreshedKey = "6. Last Refreshed";
            String timeZoneKey = "7. Time Zone";
            String intervalKey = "7. Interval";
            String outputSizeKey = "8. Output Size";
            String intradayTimeZoneKey = "9. Time Zone";

            String information = metaDataMap.get(informationKey);
            String code = metaDataMap.get(digitalCurrencyCodeKey);
            String name = metaDataMap.get(digitalCurrencyNameKey);
            String marketCode = metaDataMap.get(marketCodeKey);
            String marketName = metaDataMap.get(marketNameKey);
            String lastRefreshed = metaDataMap.get(lastRefreshedKey);
            String timeZone = null;
            String interval = null;
            String outputSize = null;

            if (metaDataMap.containsKey(intradayTimeZoneKey)) {
                timeZone = metaDataMap.get(intradayTimeZoneKey);
                interval = metaDataMap.get(intervalKey);
                outputSize = metaDataMap.get(outputSizeKey);
            }

            if (metaDataMap.containsKey(timeZoneKey)) {
                timeZone = metaDataMap.get(timeZoneKey);
            }

            MetaData metaData = MetaData.builder()
                    .information(information)
                    .digitalCurrencyCode(code)
                    .digitalCurrencyName(name)
                    .marketCode(marketCode)
                    .marketName(marketName)
                    .lastRefreshed(lastRefreshed)
                    .timeZone(timeZone)
                    .interval(interval)
                    .outputSize(outputSize)
                    .build();

            List<CryptoUnit> cryptoUnits = new ArrayList<>();

            for (Entry<String, Map<String, String>> entry : units.entrySet()) {
                Map<String, String> m = entry.getValue();
                CryptoUnit.Builder cryptoUnit = new CryptoUnit.Builder();
                cryptoUnit.open(Double.parseDouble(m.get("1. open")));
                cryptoUnit.high(Double.parseDouble(m.get("2. high")));
                cryptoUnit.low(Double.parseDouble(m.get("3. low")));
                cryptoUnit.close(Double.parseDouble(m.get("4. close")));

                Object volume = m.get("5. volume");
                if (volume instanceof String) {
                    cryptoUnit.volume(Double.parseDouble((String) volume));
                } else {
                    cryptoUnit.volume((Double) volume);
                }
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
