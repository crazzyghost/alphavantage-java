package com.crazzyghost.alphavantage.indicator.response.bbands;

/**
 * A single date's Bollinger Bands reading — the upper, middle, and lower
 * band values.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.bbands.BBANDSIndicatorUnit}
 */
@Deprecated
public class BBANDSIndicatorUnit {

    /** The date this reading applies to. */
    private String date;

    /** The upper band: the middle band plus the configured number of standard deviations. */
    private double realUpperBandValue;

    /** The lower band: the middle band minus the configured number of standard deviations. */
    private double realLowerBandValue;

    /** The middle band: the moving average the upper and lower bands are offset from. */
    private double realMiddleBandValue;

    /**
     * Creates a unit.
     *
     * @param date                the date this reading applies to
     * @param realUpperBandValue  the upper band value
     * @param realLowerBandValue  the lower band value
     * @param realMiddleBandValue the middle band value
     */
    public BBANDSIndicatorUnit(String date, double realUpperBandValue, double realLowerBandValue, double realMiddleBandValue) {
        this.date = date;
        this.realUpperBandValue = realUpperBandValue;
        this.realLowerBandValue = realLowerBandValue;
        this.realMiddleBandValue = realMiddleBandValue;
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
     * Returns the upper band value.
     *
     * @return the middle band plus the configured number of standard deviations
     */
    public double getRealUpperBandValue() {
        return realUpperBandValue;
    }
    
    /**
     * Returns the lower band value.
     *
     * @return the middle band minus the configured number of standard deviations
     */
    public double getRealLowerBandValue() {
        return realLowerBandValue;
    }
    
    /**
     * Returns the middle band value.
     *
     * @return the moving average the upper and lower bands are offset from
     */
    public double getRealMiddleBandValue() {
        return realMiddleBandValue;
    }

    @Override
    public String toString() {
        return "BBANDSIndicatorUnit {date=" + date + ", realLowerBandValue=" + realLowerBandValue
                + ", realMiddleBandValue=" + realMiddleBandValue + ", realUpperBandValue=" + realUpperBandValue + "}";
    }
 
    
}