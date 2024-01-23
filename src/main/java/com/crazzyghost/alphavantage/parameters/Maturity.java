package com.crazzyghost.alphavantage.parameters;

public enum Maturity {
    THREE_MONTH("3month"),
    TWO_YEAR("2year"),
    FIVE_YEAR("5year"),
    SEVEN_YEAR("7year"),
    TEN_YEAR("10year"),
    THIRTY_YEAR("30year");

    private final String maturity;

    Maturity(String maturity){
        this.maturity = maturity;
    }


    @Override
    public String toString() {
        return this.maturity;
    }
}
