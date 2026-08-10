package com.crazzyghost.alphavantage.indicator.response.aroon;

/**
 * A single date's Aroon reading — the Aroon-Up and Aroon-Down lines, each
 * measuring the number of periods (as a percentage of the look-back window)
 * since the most recent high or low.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.aroon.AROONIndicatorUnit}
 */
@Deprecated
public class AROONIndicatorUnit {

    /** The date this reading applies to. */
    private String date;

    /** The Aroon-Up value: how recently price made a new high, as a percentage of the time period. */
    private double aroonUp;

    /** The Aroon-Down value: how recently price made a new low, as a percentage of the time period. */
    private double aroonDown;

    /**
     * Creates a unit.
     *
     * @param date      the date this reading applies to
     * @param aroonUp   the Aroon-Up value
     * @param aroonDown the Aroon-Down value
     */
    public AROONIndicatorUnit(String date, double aroonUp, double aroonDown) {
        this.date = date;
        this.aroonUp = aroonUp;
        this.aroonDown = aroonDown;
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
     * Returns the Aroon-Up value.
     *
     * @return how recently price made a new high, as a percentage of the time period
     */
    public double getAroonUpValue() {
        return aroonUp;
    }

    /**
     * Returns the Aroon-Down value.
     *
     * @return how recently price made a new low, as a percentage of the time period
     */
    public double getAroonDownValue() {
        return aroonDown;
    }

    @Override
    public String toString() {
        return "AROONIndicatorUnit {date=" + date + ", aroonUp=" + aroonUp + ", aroonDown=" + aroonDown + "}";
    }
    
}