package com.crazzyghost.alphavantage.parameters;

public enum DataType {

    JSON("json"),
    CSV("csv");

    private String dataType;

    DataType(String dataType){
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return dataType;
    }
}
