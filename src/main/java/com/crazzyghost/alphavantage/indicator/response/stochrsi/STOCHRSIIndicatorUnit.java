package com.crazzyghost.alphavantage.indicator.response.stochrsi;

/**
 * A single date's stochastic relative strength index reading — the fast %K
 * and %D lines computed by applying the stochastic calculation to RSI
 * values instead of price.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.stochrsi.STOCHRSIIndicatorUnit}
 */
@Deprecated
public class STOCHRSIIndicatorUnit {

    /** The date this reading applies to. */
    private String date;

    /** The fast %K line: RSI's position within its recent high-low range, unsmoothed. */
    private double fastKValue;

    /** The fast %D line: fast %K smoothed by {@code fastDPeriod}. */
    private double fastDValue;

    /**
     * Creates a unit.
     *
     * @param date  the date this reading applies to
     * @param fastK the fast %K value
     * @param fastD the fast %D value
     */
    public STOCHRSIIndicatorUnit(String date, double fastK, double fastD) {
        this.date = date;
        this.fastKValue = fastK;
        this.fastDValue = fastD;
    }

    /**
     * Returns the date this reading applies to.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the fast %K value.
     *
     * @return RSI's position within its recent high-low range, unsmoothed
     */
    public double getFastKValue() {
        return fastKValue;
    }

    /**
     * Returns the fast %D value.
     *
     * @return the fast %K smoothed by the fast %D period
     */
    public double getFastDValue() {
        return fastDValue;
    }

    @Override
    public String toString() {
        return "STOCHRSIndicatorUnit {date=" + date + ", fastKValue=" + fastKValue + ", fastDValue=" + fastDValue + "}";
    }
    
}