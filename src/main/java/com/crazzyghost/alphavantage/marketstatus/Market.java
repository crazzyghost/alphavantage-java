package com.crazzyghost.alphavantage.marketstatus;

import com.squareup.moshi.Json;

public class Market {
    @Json(name = "market_type")
    private String marketType;
    @Json(name = "region")
    private String region;
    @Json(name = "primary_exchanges")
    private String primaryExchanges;
    @Json(name = "local_open")
    private String localOpen;
    @Json(name = "local_close")
    private String localClose;
    @Json(name = "current_status")
    private String currentStatus;
    @Json(name = "notes")
    private String notes;

    public String getMarketType() {
        return marketType;
    }

    public String getRegion() {
        return region;
    }

    public String getPrimaryExchanges() {
        return primaryExchanges;
    }

    public String getLocalOpen() {
        return localOpen;
    }

    public String getLocalClose() {
        return localClose;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return "GlobalMarketStatus{" +
                "marketType='" + marketType + '\'' +
                ", region='" + region + '\'' +
                ", primaryExchanges='" + primaryExchanges + '\'' +
                ", localOpen='" + localOpen + '\'' +
                ", localClose='" + localClose + '\'' +
                ", currentStatus='" + currentStatus + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
