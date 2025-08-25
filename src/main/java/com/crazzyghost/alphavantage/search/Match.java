package com.crazzyghost.alphavantage.search;

import com.squareup.moshi.Json;

public class Match {
    @Json(name = "1. symbol")
    private String symbol;
    @Json(name = "2. name")
    private String name;
    @Json(name = "3. type")
    private String type;
    @Json(name = "4. region")
    private String region;
    @Json(name = "5. marketOpen")
    private String marketOpen;
    @Json(name = "6. marketClose")
    private String marketClose;
    @Json(name = "7. timezone")
    private String timezone;
    @Json(name = "8. currency")
    private String currency;
    @Json(name = "9. matchScore")
    private String matchScore;

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getRegion() {
        return region;
    }

    public String getMarketOpen() {
        return marketOpen;
    }

    public String getMarketClose() {
        return marketClose;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getCurrency() {
        return currency;
    }

    public String getMatchScore() {
        return matchScore;
    }

    @Override
    public String toString() {
        return "Match{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", region='" + region + '\'' +
                ", marketOpen='" + marketOpen + '\'' +
                ", marketClose='" + marketClose + '\'' +
                ", timezone='" + timezone + '\'' +
                ", currency='" + currency + '\'' +
                ", matchScore='" + matchScore + '\'' +
                '}';
    }
}
