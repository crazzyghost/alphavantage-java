package com.crazzyghost.alphavantage.parser;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

public class NoneableAdapter {

    @ToJson
    public Long toJson(@Noneable Long l) {
        throw new UnsupportedOperationException();
    }

    @FromJson
    @Noneable
    public Long fromJson(String s) {
        return s.equalsIgnoreCase("none") ? null: Long.valueOf(s);
    }

}
