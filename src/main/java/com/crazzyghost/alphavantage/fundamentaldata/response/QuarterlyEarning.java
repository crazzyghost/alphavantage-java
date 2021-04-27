package com.crazzyghost.alphavantage.fundamentaldata.response;

import com.squareup.moshi.Json;

public class QuarterlyEarning {
    @Json(name="fiscalDateEnding")
    private String fiscalDateEnding;
    @Json(name="reportedDate")
    private String reportedDate;
    @Json(name="reportedEPS")
    private String reportedEPS;
    @Json(name="estimatedEPS")
    private String estimatedEPS;
    @Json(name="surprise")
    private String surprise;
    @Json(name="surprisePercentage")
    private String surprisePercentage;

    public String getFiscalDateEnding() {
        return fiscalDateEnding;
    }

    public String getReportedDate() {
        return reportedDate;
    }

    public String getReportedEPS() {
        return reportedEPS;
    }

    public String getEstimatedEPS() {
        return estimatedEPS;
    }

    public String getSurprise() {
        return surprise;
    }

    public String getSurprisePercentage() {
        return surprisePercentage;
    }

    @Override
    public String toString() {
        return "QuarterlyEarning{" +
                "fiscalDateEnding='" + fiscalDateEnding + '\'' +
                ", reportedDate='" + reportedDate + '\'' +
                ", reportedEPS='" + reportedEPS + '\'' +
                ", estimatedEPS='" + estimatedEPS + '\'' +
                ", surprise='" + surprise + '\'' +
                ", surprisePercentage='" + surprisePercentage + '\'' +
                '}';
    }
}
