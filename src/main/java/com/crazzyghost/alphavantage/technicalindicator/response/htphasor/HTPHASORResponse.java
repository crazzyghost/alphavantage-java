package com.crazzyghost.alphavantage.technicalindicator.response.htphasor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

public class HTPHASORResponse {

    private MetaData metaData;
    private List<HTPHASORIndicatorUnit> indicatorUnits;
    private String errorMessage;

    private HTPHASORResponse(List<HTPHASORIndicatorUnit> indicatorUnits, MetaData metaData) {
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    private HTPHASORResponse(String errorMessage) {
        this.metaData = new MetaData();
        this.indicatorUnits = new ArrayList<>();
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<HTPHASORIndicatorUnit> getIndicatorUnits() {
        return indicatorUnits;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public static HTPHASORResponse of(Map<String, Object> stringObjectMap) {
        Parser<HTPHASORResponse> parser = new HTPHASORParser();
        return parser.parse(stringObjectMap);
    }

    public static class HTPHASORParser extends DefaultParser<HTPHASORResponse> {

        @Override
        public HTPHASORResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> indicatorData) {

            MetaData metaData = new MetaData(
                    String.valueOf(metaDataMap.get("1: Symbol")),
                    String.valueOf(metaDataMap.get("2: Indicator")),
                    String.valueOf(metaDataMap.get("3: Last Refreshed")),
                    String.valueOf(metaDataMap.get("4: Interval")),
                    String.valueOf(metaDataMap.get("5: Series Type")),
                    String.valueOf(metaDataMap.get("6: Time Zone")));

            List<HTPHASORIndicatorUnit> indicatorUnits = new ArrayList<>();

            for (Map.Entry<String, Map<String, String>> e : indicatorData.entrySet()) {
                Map<String, String> m = e.getValue();
                HTPHASORIndicatorUnit indicatorUnit = new HTPHASORIndicatorUnit(
                        e.getKey(),
                        Double.parseDouble(m.get("PHASE")),
                        Double.parseDouble(m.get("QUADRATURE")));
                indicatorUnits.add(indicatorUnit);
            }
            return new HTPHASORResponse(indicatorUnits, metaData);
        }

        @Override
        public HTPHASORResponse onParseError(String error) {
            return new HTPHASORResponse(error);
        }
    }

    @Override
    public String toString() {
        return "HTPHASORResponse{" +
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
