package com.crazzyghost.alphavantage.parser;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

public class NoneableLongAdapter {

    @ToJson
    public Long toJson(@NoneableLong Long l) {
        throw new UnsupportedOperationException();
    }

    @FromJson
    @NoneableLong
    public Long fromJson(String s) {
        return Parser.getNumberFromString(s, Long::parseLong);
    }

}
