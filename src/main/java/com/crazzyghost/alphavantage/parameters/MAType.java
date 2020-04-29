package com.crazzyghost.alphavantage.parameters;

public enum MAType {

    SMA(0),
    EMA(1),
    WMA(2),
    DEMA(3),
    TEMA(4),
    TRIMA(5),
    T3(6),
    KAMA(7),
    MAMA(8);

    private int type;

    MAType(int type){
        this.type = type;
    }

    @Override
    public String toString() {
        return "" + this.type;    
    }

}