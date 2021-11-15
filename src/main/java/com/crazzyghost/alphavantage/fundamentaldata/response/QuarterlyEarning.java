package com.crazzyghost.alphavantage.fundamentaldata.response;

import com.crazzyghost.alphavantage.parser.NoneableDouble;
import com.squareup.moshi.Json;

public class QuarterlyEarning {
    @Json(name="fiscalDateEnding")
    private String fiscalDateEnding;
    @Json(name="reportedDate")
    private String reportedDate;
    @Json(name="reportedEPS")
    @NoneableDouble
    private Double reportedEPS;
    @Json(name="estimatedEPS")
    @NoneableDouble
    private Double estimatedEPS;
    @Json(name="surprise")
    @NoneableDouble
    private Double surprise;
    @Json(name="surprisePercentage")
    @NoneableDouble
    private Double surprisePercentage;

    public String getFiscalDateEnding() {
        return fiscalDateEnding;
    }

    public String getReportedDate() {
        return reportedDate;
    }

    public Double getReportedEPS() {
        return reportedEPS;
    }

    public Double getEstimatedEPS() {
        return estimatedEPS;
    }

    public Double getSurprise() {
        return surprise;
    }

    public Double getSurprisePercentage() {
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
