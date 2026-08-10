package com.crazzyghost.alphavantage.indicator.response.stoch;

/**
 * A single date's stochastic oscillator reading — the smoothed %K and %D
 * lines.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.stoch.STOCHIndicatorUnit}
 */
@Deprecated
public class STOCHIndicatorUnit {

    /** The date this reading applies to. */
    private String date;

    /** The slow %K line: the raw %K smoothed by {@code slowKPeriod}. */
    private double slowKValue;

    /** The slow %D line: slow %K smoothed further by {@code slowDPeriod}. */
    private double slowDValue;

    /**
     * Creates a unit.
     *
     * @param date  the date this reading applies to
     * @param slowK the slow %K value
     * @param slowD the slow %D value
     */
    public STOCHIndicatorUnit(String date, double slowK, double slowD) {
        this.date = date;
        this.slowKValue = slowK;
        this.slowDValue = slowD;
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
     * Returns the slow %K value.
     *
     * @return the raw %K smoothed by the slow %K period
     */
    public double getSlowKValue() {
        return slowKValue;
    }

    /**
     * Returns the slow %D value.
     *
     * @return the slow %K smoothed further by the slow %D period
     */
    public double getSlowDValue() {
        return slowDValue;
    }

    @Override
    public String toString() {
        return "STOCHIndicatorUnit {date=" + date + ", slowKValue=" + slowKValue + ", slowDValue=" + slowDValue + "}";
    }


    
    
}