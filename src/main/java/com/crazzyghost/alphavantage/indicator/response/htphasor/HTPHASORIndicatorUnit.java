package com.crazzyghost.alphavantage.indicator.response.htphasor;

/**
 * A single date's Hilbert transform phasor reading — the phase and
 * quadrature components the Hilbert transform decomposes the price series
 * into.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.htphasor.HTPHASORIndicatorUnit}
 */
@Deprecated
public class HTPHASORIndicatorUnit {

    /** The date this reading applies to. */
    private String date;

    /** The in-phase ({@code PHASE}) component of the Hilbert transform. */
    private double phaseValue;

    /** The quadrature ({@code QUADRATURE}) component of the Hilbert transform. */
    private double quadratureValue;

    /**
     * Creates a unit.
     *
     * @param date  the date this reading applies to
     * @param leadSine the in-phase ({@code PHASE}) component
     * @param sine     the quadrature ({@code QUADRATURE}) component
     */
    public HTPHASORIndicatorUnit(String date, double leadSine, double sine){
        this.date = date;
        this.phaseValue = leadSine;
        this.quadratureValue = sine;
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
     * Returns the in-phase component of the Hilbert transform.
     *
     * @return the phase value
     */
    public double getPhaseValue() {
        return phaseValue;
    }

    /**
     * Returns the quadrature component of the Hilbert transform.
     *
     * @return the quadrature value
     */
    public double getQuadratureValue() {
        return quadratureValue;
    }

    @Override
    public String toString() {
        return "HTPHASORIndicatorUnit {date=" + date + ", phaseValue=" + phaseValue + ", quadratureValue=" + quadratureValue
                + "}";
    }

    
}