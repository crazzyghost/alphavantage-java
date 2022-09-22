package com.crazzyghost.alphavantage.technicalindicator.response.ultosc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crazzyghost.alphavantage.technicalindicator.response.SimpleTechnicalIndicatorUnit;
import com.crazzyghost.alphavantage.parser.DefaultParser;
import com.crazzyghost.alphavantage.parser.Parser;

public class ULTOSCResponse {

    private MetaData metaData;
    private List<SimpleTechnicalIndicatorUnit> indicatorUnits;
    private String errorMessage;

    private ULTOSCResponse(List<SimpleTechnicalIndicatorUnit> indicatorUnits, MetaData metaData) {
        this.metaData = metaData;
        this.indicatorUnits = indicatorUnits;
        this.errorMessage = null;
    }

    private ULTOSCResponse(String errorMessage) {
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

    public static ULTOSCResponse of(Map<String, Object> stringObjectMap) {
        Parser<ULTOSCResponse> parser = new ULTOSCParser();
        return parser.parse(stringObjectMap);
    }

    public static class ULTOSCParser extends DefaultParser<ULTOSCResponse> {

        @Override
        public ULTOSCResponse parse(Map<String, String> metaDataMap, Map<String, Map<String, String>> indicatorData) {

            MetaData metaData = new MetaData(
                    metaDataMap.get("1: Symbol").toString(),
                    metaDataMap.get("2: Indicator").toString(),
                    metaDataMap.get("3: Last Refreshed").toString(),
                    metaDataMap.get("4: Interval").toString(),
                    Double.valueOf(String.valueOf(metaDataMap.get("5.1: Time Period 1"))).intValue(),
                    Double.valueOf(String.valueOf(metaDataMap.get("5.2: Time Period 2"))).intValue(),
                    Double.valueOf(String.valueOf(metaDataMap.get("5.3: Time Period 3"))).intValue(),
                    metaDataMap.get("6: Time Zone").toString());

            List<SimpleTechnicalIndicatorUnit> indicatorUnits = new ArrayList<>();

            for (Map.Entry<String, Map<String, String>> e : indicatorData.entrySet()) {
                Map<String, String> m = e.getValue();
                SimpleTechnicalIndicatorUnit indicatorUnit = new SimpleTechnicalIndicatorUnit(
                        e.getKey(),
                        Double.parseDouble(m.get("ULTOSC")),
                        "ULTOSC");
                indicatorUnits.add(indicatorUnit);
            }
            return new ULTOSCResponse(indicatorUnits, metaData);

        }

        @Override
        public ULTOSCResponse onParseError(String error) {
            return new ULTOSCResponse(error);
        }

    }

    @Override
    public String toString() {
        return "ULTOSCResponse{" +
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
        private int timePeriod1;
        private int timePeriod2;
        private int timePeriod3;
        private String timeZone;

        public MetaData() {
            this("", "", "", "", 0, 0, 0, "");
        }

        public MetaData(
                String symbol,
                String indicator,
                String lastRefreshed,
                String interval,
                int timePeriod1,
                int timePeriod2,
                int timePeriod3,
                String timeZone) {
            this.symbol = symbol;
            this.indicator = indicator;
            this.lastRefreshed = lastRefreshed;
            this.interval = interval;
            this.timePeriod1 = timePeriod1;
            this.timePeriod2 = timePeriod2;
            this.timePeriod3 = timePeriod3;
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

        public int getTimePeriod1() {
            return timePeriod1;
        }

        public int getTimePeriod2() {
            return timePeriod2;
        }

        public int getTimePeriod3() {
            return timePeriod3;
        }

        @Override
        public String toString() {
            return "MetaData {indicator=" + indicator + ", interval=" + interval + ", lastRefreshed=" + lastRefreshed
                    + ", symbol=" + symbol + ", timePeriod1=" + timePeriod1 + ", timePeriod2=" + timePeriod2
                    + ", timePeriod3=" + timePeriod3 + ", timeZone=" + timeZone + "}";
        }

    }

}
