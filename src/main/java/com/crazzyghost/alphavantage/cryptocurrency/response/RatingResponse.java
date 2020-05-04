package com.crazzyghost.alphavantage.cryptocurrency.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.AlphaVantageException;

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
        Parser parser = new Parser();
        return parser.parse(stringObjectMap);
    }

    private static class Parser{

        RatingResponse parse(Map<String, Object> stringObjectMap){
            List<String> keys = new ArrayList<>(stringObjectMap.keySet());
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
               return new RatingResponse((String)stringObjectMap.get(keys.get(0)));

            }
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
                + "}";
    }
 
    
    
}