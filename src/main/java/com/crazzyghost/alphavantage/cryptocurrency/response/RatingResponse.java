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
 * The FCAS health index rating for a digital currency, or an error message if the
 * API rejected the request.
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

    /**
     * Creates a rating response from its already-parsed FCAS fields.
     *
     * @param symbol              the digital currency's symbol
     * @param name                the digital currency's full name
     * @param fcasRating          the categorical FCAS grade
     * @param fcasScore           the numeric FCAS score
     * @param developerScore      the developer-activity component score
     * @param marketMaturityScore the market-maturity component score
     * @param utilityScore        the utility component score
     * @param lastRefreshed       the date the rating was last refreshed
     * @param timeZone            the time zone {@code lastRefreshed} is
     *                            expressed in
     */
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

    /**
     * Parses a raw JSON response from the {@code CRYPTO_RATING} endpoint.
     *
     * @param stringObjectMap the response body, decoded from JSON
     * @return the parsed rating, or one carrying an error message if parsing
     *         failed
     */
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

    /**
     * Returns the error message the API returned, if the request failed.
     *
     * @return the error message, or {@code null} if the request succeeded
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Returns the digital currency's symbol.
     *
     * @return the digital currency symbol this rating was requested for
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns the digital currency's full name.
     *
     * @return the digital currency name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the FCAS (Fundamental Crypto Asset Score) categorical grade, for
     * example {@code "Attractive"} or {@code "Caution"}. This is Alpha Vantage's
     * proprietary crypto health index rating, licensed from Flipside Crypto; the
     * grade is derived from the {@link #getFcasScore() FCAS score}.
     *
     * @return the categorical FCAS grade
     */
    public String getFcasRating() {
        return fcasRating;
    }

    /**
     * Returns the numeric FCAS (Fundamental Crypto Asset Score), on a scale of
     * 0 to 1000. This combines the {@link #getDeveloperScore() developer},
     * {@link #getMarketMaturityScore() market maturity} and
     * {@link #getUtilityScore() utility} component scores into a single measure
     * of a digital currency's fundamental health.
     *
     * @return the numeric FCAS score
     */
    public String getFcasScore() {
        return fcasScore;
    }

    /**
     * Returns the developer-activity component of the FCAS score, on a scale of
     * 0 to 1000, reflecting code contribution and developer community
     * involvement.
     *
     * @return the developer score
     */
    public String getDeveloperScore() {
        return developerScore;
    }

    /**
     * Returns the market-maturity component of the FCAS score, on a scale of 0
     * to 1000, reflecting market-related fundamentals such as liquidity and
     * trading activity.
     *
     * @return the market maturity score
     */
    public String getMarketMaturityScore() {
        return marketMaturityScore;
    }

    /**
     * Returns the utility component of the FCAS score, on a scale of 0 to 1000,
     * reflecting user activity and network utilization.
     *
     * @return the utility score
     */
    public String getUtilityScore() {
        return utilityScore;
    }

    /**
     * Returns the date the rating was last refreshed.
     *
     * @return the last-refreshed date, in the time zone given by
     *         {@link #getTimeZone()}
     */
    public String getLastRefreshed() {
        return lastRefreshed;
    }

    /**
     * Returns the time zone {@link #getLastRefreshed()} is expressed in.
     *
     * @return the time zone name
     */
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
