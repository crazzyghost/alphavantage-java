package com.crazzyghost.alphavantage.parser;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

public class NoneableDoubleAdapter {

    @ToJson
    public Double toJson(@NoneableDouble Double l) {
        throw new UnsupportedOperationException();
    }

    @FromJson
    @NoneableDouble
    public Double fromJson(String s) {
        return s.equalsIgnoreCase("none") ? null: Double.valueOf(s);
    }

}
