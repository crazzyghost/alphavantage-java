/*
 *
 * Copyright (c) 2020 Sylvester Sefa-Yeboah
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.crazzyghost.alphavantage.fundamentaldata.response;

import com.crazzyghost.alphavantage.parser.SimpleParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompanyOverviewResponse {

    private final String symbol;
    private final CompanyOverviewUnit overview;
    private final String errorMessage;

    private CompanyOverviewResponse(String error) {
        this.errorMessage = error;
        this.overview = null;
        this.symbol = null;
    }

    private CompanyOverviewResponse(String symbol, CompanyOverviewUnit overview) {
        this.symbol = symbol;
        this.overview = overview;
        this.errorMessage = null;
    }

    public static CompanyOverviewResponse of(Object objectMap) {
        SimpleParser<CompanyOverviewResponse, Object> parser = new CompanyOverviewParser();
        return parser.parse(objectMap);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getSymbol() {
        return symbol;
    }

    public CompanyOverviewUnit getOverview() {
        return overview;
    }

    public static class CompanyOverviewParser extends SimpleParser<CompanyOverviewResponse, Object> {

        @Override
        public CompanyOverviewResponse onParseError(String error) {
            return new CompanyOverviewResponse(error);
        }

        @Override
        @SuppressWarnings("unchecked")
        public CompanyOverviewResponse parse(Object data) {
            try {
                CompanyOverviewUnit unit = (CompanyOverviewUnit)data;
                return new CompanyOverviewResponse(unit.getSymbol(), unit);
            } catch (ClassCastException e) {
                Map<String, String> map = (Map<String, String>) data;
                List<String> error = new ArrayList<>(map.keySet());
                if (error.size() == 0) {
                    return onParseError("Empty JSON returned by the API, the symbol might not be supported.");
                }
                return onParseError(error.get(0));
            }
        }
    }

    @Override
    public String toString() {
        return "CompanyOverviewResponse{" +
                "symbol='" + symbol + '\'' +
                ", overview=" + overview +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
