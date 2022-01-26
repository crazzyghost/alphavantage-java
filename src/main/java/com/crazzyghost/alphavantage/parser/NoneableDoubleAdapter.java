package com.crazzyghost.alphavantage.parser;

import com.crazzyghost.alphavantage.parser.util.ParserUtil;
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
        return ParserUtil.getNumberFromString(s, Double::parseDouble);
    }

}
