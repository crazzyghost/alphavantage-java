package com.crazzyghost.alphavantage.cryptocurrency.response;

public class MetaData {

    private String information;
    private String digitalCurrencyCode;
    private String digitalCurrencyName;
    private String marketCode;
    private String marketName;
    private String lastRefreshed;
    private String timeZone;

    public MetaData(
        String information,
        String digitalCurrencyCode,
        String digitalCurrencyName,
        String marketCode,
        String marketName,
        String lastRefreshed,
        String timeZone
    ) {
        this.information = information;
        this.digitalCurrencyCode = digitalCurrencyCode;
        this.digitalCurrencyName = digitalCurrencyName;
        this.marketCode = marketCode;
        this.marketName = marketName;
        this.lastRefreshed = lastRefreshed;
        this.timeZone = timeZone;
    }


    public static MetaData empty(){
        return new MetaData("","","","", "", "", "");
    }

    public String getDigitalCurrencyCode() {
        return digitalCurrencyCode;
    }

    public String getDigitalCurrencyName() {
        return digitalCurrencyName;
    }

    public String getInformation() {
        return information;
    }
    
    public String getLastRefreshed() {
        return lastRefreshed;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public String getMarketCode() {
        return marketCode;
    }
    
    public String getMarketName() {
        return marketName;
    }
    

    @Override
    public String toString() {
        return "MetaData{" +
                "information='" + information + '\'' +
                ", digitalCurrencyCode='" + digitalCurrencyCode + '\'' +
                ", digitalCurrencyName='" + digitalCurrencyName + '\'' +
                ", marketCode='" + marketCode + '\'' +
                ", marketName='" + marketName + '\'' +
                ", lastRefreshed='" + lastRefreshed + '\'' +
                ", timeZone='" + timeZone + '\'' +
                '}';
    }
}
