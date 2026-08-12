package com.crazzyghost.alphavantage.indicator.response.mama;

/**
 * A single date's MESA adaptive moving average reading — the MAMA value and
 * its slower-following companion, FAMA.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.mama.MAMAIndicatorUnit}
 */
@Deprecated
public class MAMAIndicatorUnit {

    /** The date this reading applies to. */
    private String date;

    /** The following adaptive moving average (FAMA), which trails MAMA to help confirm crossovers. */
    private double famaValue;

    /** The MESA adaptive moving average (MAMA) value. */
    private double mamaValue;

    /**
     * Creates a unit.
     *
     * @param date the date this reading applies to
     * @param fama the FAMA value
     * @param mama the MAMA value
     */
    public MAMAIndicatorUnit(String date, double fama, double mama) {
        this.date = date;
        this.famaValue = fama;
        this.mamaValue = mama;
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
     * Returns the FAMA value.
     *
     * @return the following adaptive moving average
     */
    public double getFamaValue() {
        return famaValue;
    }

    /**
     * Returns the MAMA value.
     *
     * @return the MESA adaptive moving average
     */
    public double getMamaValue() {
        return mamaValue;
    }

    @Override
    public String toString() {
        return "MAMAIndicatorUnit {date=" + date + ", famaValue=" + famaValue + ", mamaValue=" + mamaValue + "}";
    }


    
    
}