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

import com.crazzyghost.alphavantage.parser.Parser;

/**
 * Crypto Currency Rating Response
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.0.0
 */
public class RatingResponse {

    private String symbol;
    private String name;
    private String fcasRating;
    private String fcasScore;
    private String developerScore;
    private String marketMaturityScore;
    private String utilityScore;
    private String lastRefreshed;
    private String timeZone;

    private String errorMessage;

    public RatingResponse(
        String symbol,
        String name,
        String fcasRating,
        String fcasScore,
        String developerScore,
        String marketMaturityScore,
        String utilityScore,
        String lastRefreshed,
        String timeZone
    ) {
        this.symbol = symbol;
        this.name = name;
        this.fcasRating = fcasRating;
        this.fcasScore = fcasScore;
        this.developerScore = developerScore;
        this.marketMaturityScore = marketMaturityScore;
        this.utilityScore = utilityScore;
        this.lastRefreshed = lastRefreshed;
        this.timeZone = timeZone;
    }

    private RatingResponse(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public static RatingResponse of(Map<String, Object> stringObjectMap){
        Parser<RatingResponse> parser = new RatingParser();
        return parser.parse(stringObjectMap);
    }

    private static class RatingParser extends Parser<RatingResponse> {

        @SuppressWarnings("unchecked")
        @Override
        public RatingResponse parse(Map<String, Object> stringObjectMap){
            List<String> keys = new ArrayList<>(stringObjectMap.keySet());
            if (keys.isEmpty()) {
                return onParseError("Empty JSON returned by the API, the symbol might not be supported.");
            } else {
                try{
                    Map<String, String> md = (Map<String, String>) stringObjectMap.get(keys.get(0));
                    String symbol = md.get("1. symbol");
                    String name = md.get("2. name");
                    String fcasRating = md.get("3. fcas rating");
                    String fcasScore = md.get("4. fcas score");
                    String developerScore = md.get("5. developer score");
                    String marketMaturityScore = md.get("6. market maturity score");
                    String utilityScore = md.get("7. utility score");
                    String lastRefreshed = md.get("8. last refreshed");
                    String timeZone = md.get("9. timezone");
                    return new RatingResponse(symbol, name, fcasRating, fcasScore, developerScore, marketMaturityScore, utilityScore, lastRefreshed, timeZone);

                }catch (ClassCastException e){
                    return onParseError(stringObjectMap.get(keys.get(0)).toString());
                }
            }
        }


        @Override
        public RatingResponse onParseError(String error) {
            return new RatingResponse(error);
        }

    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getFcasRating() {
        return fcasRating;
    }

    public String getFcasScore() {
        return fcasScore;
    }

    public String getDeveloperScore() {
        return developerScore;
    }

    public String getMarketMaturityScore() {
        return marketMaturityScore;
    }

    public String getUtilityScore() {
        return utilityScore;
    }

    public String getLastRefreshed() {
        return lastRefreshed;
    }

    public String getTimeZone() {
        return timeZone;
    }

    @Override
    public String toString() {
        return "RatingResponse {developerScore=" + developerScore + ", fcasRating=" + fcasRating + ", fcasScore="
                + fcasScore + ", lastRefreshed=" + lastRefreshed + ", marketMaturityScore=" + marketMaturityScore
                + ", name=" + name + ", symbol=" + symbol + ", timeZone=" + timeZone + ", utilityScore=" + utilityScore
                + ", errorMessage" + errorMessage
                + "}";
    }



}
