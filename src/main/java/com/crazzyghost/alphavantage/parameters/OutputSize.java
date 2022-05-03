package com.crazzyghost.alphavantage.parameters;

public enum OutputSize {

    COMPACT("compact"),
    FULL("full");

    private final String outputSize;

    OutputSize(String outputSize){
        this.outputSize = outputSize;
    }

    @Override
    public String toString() {
        return this.outputSize;
    }
}
