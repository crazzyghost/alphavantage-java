package com.crazzyghost.alphavantage.technicalindicator.response;

import com.crazzyghost.alphavantage.parser.DefaultParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class SeriesResponse {

    protected MetaData metaData;
    protected List<SimpleTechnicalIndicatorUnit> indicatorUnits;
    protected String errorMessage;

    protected SeriesResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    protected SeriesResponse(String errorMessage) {
        this.metaData = new MetaData();
        this.indicatorUnits = new ArrayList<>();
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<SimpleTechnicalIndicatorUnit> getIndicatorUnits() {
        return indicatorUnits;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public static abstract class SeriesParser<T> extends DefaultParser<T> {

        protected SeriesParser() {

        }

        @Override
        public T parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> indicatorData) {
            MetaData metaData = new MetaData(
                    String.valueOf(metaDataMap.get("1: Symbol")),
                    String.valueOf(metaDataMap.get("2: Indicator")),
                    String.valueOf(metaDataMap.get("3: Last Refreshed")),
                    String.valueOf(metaDataMap.get("4: Interval")),
                    String.valueOf(metaDataMap.get("5: Series Type")),
                    String.valueOf(metaDataMap.get("6: Time Zone")));

            List<SimpleTechnicalIndicatorUnit> indicatorUnits = new ArrayList<>();

            for (Map.Entry<String, Map<String, String>> e : indicatorData.entrySet()) {
                Map<String, String> m = e.getValue();
                SimpleTechnicalIndicatorUnit indicatorUnit = new SimpleTechnicalIndicatorUnit(
                        e.getKey(),
                        Double.parseDouble(m.get(getTechnicalIndicatorKey())),
                        getTechnicalIndicatorKey());
                indicatorUnits.add(indicatorUnit);
            }
            return get(indicatorUnits, metaData);
        }

        @Override
        public T onParseError(String error) {
            return get(error);
        }

        public abstract T get(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData);

        public abstract T get(String error);

        public abstract String getTechnicalIndicatorKey();
    }

    @Override
    public String toString() {
        return "SeriesResponse{" +
                "metaData=" + metaData +
                ",indicatorUnits=" + indicatorUnits.size() +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    public static class MetaData {

        private String symbol;
        private String indicator;
        private String lastRefreshed;
        private String interval;
        private String seriesType;
        private String timeZone;

        public MetaData() {
            this("", "", "", "", "", "");
        }

        public MetaData(
                String symbol,
                String indicator,
                String lastRefreshed,
                String interval,
                String seriesType,
                String timeZone) {
            this.symbol = symbol;
            this.indicator = indicator;
            this.lastRefreshed = lastRefreshed;
            this.interval = interval;
            this.seriesType = seriesType;
            this.timeZone = timeZone;
        }

        public String getSymbol() {
            return symbol;
        }

        public String getIndicator() {
            return indicator;
        }

        public String getLastRefreshed() {
            return lastRefreshed;
        }

        public String getInterval() {
            return interval;
        }

        public String getTimeZone() {
            return timeZone;
        }

        public String getSeriesType() {
            return seriesType;
        }

        @Override
        public String toString() {
            return "MetaData {indicator=" + indicator + ", interval=" + interval + ", lastRefreshed=" + lastRefreshed
                    + ", seriesType=" + seriesType + ", symbol=" + symbol + ", timeZone=" + timeZone + "}";
        }

    }
}
