package com.crazzyghost.alphavantage.fundamentaldata.response;

import com.squareup.moshi.Json;

public class AnnualEarning {

    @Json(name="fiscalDateEnding")
    private String fiscalDateEnding;
    @Json(name="reportedEPS")
    private String reportedEPS;

    public String getFiscalDateEnding() {
        return fiscalDateEnding;
    }

    public String getReportedEPS() {
        return reportedEPS;
    }

    @Override
    public String toString() {
        return "AnnualEarning{" +
                "fiscalDateEnding='" + fiscalDateEnding + '\'' +
                ", reportedEPS='" + reportedEPS + '\'' +
                '}';
    }
}
