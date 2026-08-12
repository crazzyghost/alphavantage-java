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
package com.crazzyghost.alphavantage.fundamentaldata.response;

import com.crazzyghost.alphavantage.parser.NoneableDouble;
import com.squareup.moshi.Json;

/**
 * A company's reported versus estimated earnings per share (EPS) for a
 * single fiscal quarter, and the resulting earnings surprise.
 * <p>
 * Unlike {@link AnnualEarning}, this record covers a single fiscal quarter
 * and additionally carries the analyst estimate and the surprise it produced
 * — figures only tracked at quarterly granularity by the {@code EARNINGS}
 * endpoint.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 */
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
    @Json(name="reportTime")
    private String reportTime;

    /**
     * Returns the closing date of the fiscal quarter this record covers.
     *
     * @return the fiscal quarter end date, in {@code yyyy-MM-dd} form
     */
    public String getFiscalDateEnding() {
        return fiscalDateEnding;
    }

    /**
     * Returns the date the company actually reported this quarter's earnings,
     * which may fall after {@link #getFiscalDateEnding()}.
     *
     * @return the earnings report date, in {@code yyyy-MM-dd} form
     */
    public String getReportedDate() {
        return reportedDate;
    }

    /**
     * Returns actual reported earnings per share for the quarter.
     * {@code null} if not yet reported (see {@link NoneableDouble}).
     *
     * @return reported EPS
     */
    public Double getReportedEPS() {
        return reportedEPS;
    }

    /**
     * Returns the consensus analyst EPS estimate for the quarter, ahead of
     * the actual report. {@code null} if no estimate is available (see
     * {@link NoneableDouble}).
     *
     * @return estimated EPS
     */
    public Double getEstimatedEPS() {
        return estimatedEPS;
    }

    /**
     * Returns the earnings surprise: reported EPS minus estimated EPS.
     * {@code null} if either figure is unavailable (see
     * {@link NoneableDouble}).
     *
     * @return the earnings surprise
     */
    public Double getSurprise() {
        return surprise;
    }

    /**
     * Returns the earnings surprise expressed as a percentage of estimated
     * EPS. {@code null} if either figure is unavailable (see
     * {@link NoneableDouble}).
     *
     * @return the earnings surprise percentage
     */
    public Double getSurprisePercentage() {
        return surprisePercentage;
    }

    /**
     * Returns whether this quarter's earnings were reported before market
     * open or after market close, for example {@code pre-market} or
     * {@code post-market}. {@code null} if not reported.
     *
     * @return the earnings report time token
     */
    public String getReportTime() {
        return reportTime;
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
                ", reportTime='" + reportTime + '\'' +
                '}';
    }
}
