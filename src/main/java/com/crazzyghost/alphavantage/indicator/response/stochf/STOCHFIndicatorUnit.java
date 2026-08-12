package com.crazzyghost.alphavantage.indicator.response.stochf;

/**
 * A single date's stochastic fast reading — the unsmoothed %K and lightly
 * smoothed %D lines.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.stochf.STOCHFIndicatorUnit}
 */
@Deprecated
public class STOCHFIndicatorUnit {

    /** The date this reading applies to. */
    private String date;

    /** The fast %K line: the raw stochastic value, unsmoothed. */
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
    public STOCHFIndicatorUnit(String date, double fastK, double fastD) {
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
     * @return the raw stochastic value, unsmoothed
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
        return "STOCHFIndicatorUnit {date=" + date + ", fastKValue=" + fastKValue + ", fastDValue=" + fastDValue + "}";
    }


    
    
}