package com.crazzyghost.alphavantage.indicator.response.htsine;

/**
 * A single date's Hilbert transform sine wave reading — the Sine and Lead
 * Sine lines used to spot cycle turning points.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.htsine.HTSINEIndicatorUnit}
 */
@Deprecated
public class HTSINEIndicatorUnit {

    /** The date this reading applies to. */
    private String date;

    /** The Lead Sine line: the Sine line advanced to anticipate the next cycle turn. */
    private double leadSineValue;

    /** The Sine line: the sine of the price series' dominant cycle phase. */
    private double sineValue;

    /**
     * Creates a unit.
     *
     * @param date     the date this reading applies to
     * @param leadSine the Lead Sine value
     * @param sine     the Sine value
     */
    public HTSINEIndicatorUnit(String date, double leadSine, double sine){
        this.date = date;
        this.leadSineValue = leadSine;
        this.sineValue = sine;
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
     * Returns the Lead Sine value.
     *
     * @return the Sine line advanced to anticipate the next cycle turn
     */
    public double getLeadSineValue() {
        return leadSineValue;
    }

    /**
     * Returns the Sine value.
     *
     * @return the sine of the price series' dominant cycle phase
     */
    public double getSineValue() {
        return sineValue;
    }

    @Override
    public String toString() {
        return "HTSINEIndicatorUnit {date=" + date + ", leadSineValue=" + leadSineValue + ", sineValue=" + sineValue
                + "}";
    }

    
}