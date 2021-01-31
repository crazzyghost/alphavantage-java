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

import com.crazzyghost.alphavantage.parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BalanceSheetResponse {

    private final String symbol;
    private final List<BalanceSheetUnit> annualReports;
    private final List<BalanceSheetUnit> quarterlyReports;
    private final String errorMessage;

    private BalanceSheetResponse(String error) {
        this.errorMessage = error;
        this.annualReports = new ArrayList<>();
        this.quarterlyReports = new ArrayList<>();
        this.symbol = null;
    }

    private BalanceSheetResponse(String symbol, List<BalanceSheetUnit> annualReports, List<BalanceSheetUnit> quarterlyReports) {
        this.symbol = symbol;
        this.annualReports = annualReports;
        this.quarterlyReports = quarterlyReports;
        this.errorMessage = null;
    }

    public static BalanceSheetResponse of(Map<String, Object> objectMap) {
        Parser<BalanceSheetResponse> parser = new BalanceSheetParser();
        return parser.parse(objectMap);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getSymbol() {
        return symbol;
    }

    public List<BalanceSheetUnit> getAnnualReports() {
        return annualReports;
    }

    public List<BalanceSheetUnit> getQuarterlyReports() {
        return quarterlyReports;
    }

    public static class BalanceSheetParser extends Parser<BalanceSheetResponse> {

        @Override
        public BalanceSheetResponse onParseError(String error) {
            return new BalanceSheetResponse(error);
        }

        @Override
        @SuppressWarnings("unchecked")
        public BalanceSheetResponse parse(Map<String, Object> object) {
            List<String> keys = new ArrayList<>(object.keySet());
            if (keys.isEmpty()) {
                return onParseError("Empty JSON returned by the API, the symbol might not be supported.");
            }
            try {
                String symbol = (String)object.get(keys.get(0));
                List<BalanceSheetUnit> annualReports = (List<BalanceSheetUnit>)object.get(keys.get(1));
                List<BalanceSheetUnit> quarterlyReports = (List<BalanceSheetUnit>)object.get(keys.get(2));
                return new BalanceSheetResponse(symbol, annualReports, quarterlyReports);
            } catch (ClassCastException e) {
                return onParseError(object.get(keys.get(0)).toString());
            }
        }
    }
}
