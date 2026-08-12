package com.crazzyghost.alphavantage.indicator.response;

/**
 * A single date/value pair for any indicator that reports one numeric value
 * per date — every family built on {@link PeriodicResponse}, {@link
 * PeriodicSeriesResponse}, {@link SeriesResponse}, {@link
 * SimpleIndicatorResponse}, and {@link PriceOscillatorResponse}
 * reuses this class rather than defining its own unit type, unlike
 * multi-field indicators such as {@code BBANDS} or {@code STOCH}, which
 * define their own {@code *IndicatorUnit} classes.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.1.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit}
 */
@Deprecated
public class SimpleIndicatorUnit {

    /** The date this value applies to. */
    String date;

    /** The indicator's value on {@link #date}. */
    Double value;

    /** The JSON key this value was parsed from, used only to identify the indicator in {@link #toString()}. */
    String indicatorKey;

 /**
  * Creates a unit without an indicator key.
  *
  * @param date  the date this value applies to
  * @param value the indicator's value on that date
  */
	public SimpleIndicatorUnit(String date, Double value) {
        this.date = date;
        this.value = value;
    }

    /**
     * Creates a unit.
     *
     * @param date         the date this value applies to
     * @param value        the indicator's value on that date
     * @param indicatorKey the JSON key this value was parsed from
     */
    public SimpleIndicatorUnit(String date, Double value, String indicatorKey) {
        this(date, value);
        this.indicatorKey = indicatorKey;
    }


    /**
     * Returns the date this value applies to.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the indicator's value on this unit's date.
     *
     * @return the value
     */
    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        String key = indicatorKey == null ? "SimpleIndicator" : indicatorKey; 
        return key + "Unit {date=" + date + ", value=" + value + "}";
    }
    
    
}