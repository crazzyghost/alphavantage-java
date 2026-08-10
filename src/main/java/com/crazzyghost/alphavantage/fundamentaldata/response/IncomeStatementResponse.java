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

/**
 * The {@code INCOME_STATEMENT} endpoint's response: a company's annual and
 * quarterly income statements, or the error returned in their place.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 */
public class IncomeStatementResponse {

    private final String symbol;
    private final List<IncomeStatement> annualReports;
    private final List<IncomeStatement> quarterlyReports;
    private final String errorMessage;

    private IncomeStatementResponse(String error) {
        this.errorMessage = error;
        this.annualReports = new ArrayList<>();
        this.quarterlyReports = new ArrayList<>();
        this.symbol = null;
    }

    private IncomeStatementResponse(String symbol, List<IncomeStatement> annualReports, List<IncomeStatement> quarterlyReports) {
        this.symbol = symbol;
        this.annualReports = annualReports;
        this.quarterlyReports = quarterlyReports;
        this.errorMessage = null;
    }

    /**
     * Parses a raw {@code INCOME_STATEMENT} API response into an
     * {@code IncomeStatementResponse}.
     *
     * @param  objectMap the parsed JSON response body
     * @return the parsed response, or an error response if parsing fails
     */
    public static IncomeStatementResponse of(Map<String, Object> objectMap) {
        Parser<IncomeStatementResponse> parser = new IncomeStatementParser();
        return parser.parse(objectMap);
    }

    /**
     * Returns the error message returned by the API, if the request failed.
     *
     * @return the error message, or {@code null} if the request succeeded
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Returns the ticker symbol this response is for.
     *
     * @return the ticker symbol, or {@code null} if the request failed
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns the company's annual income statements, most recent first.
     *
     * @return the annual income statement reports, or an empty list if the
     *         request failed
     */
    public List<IncomeStatement> getAnnualReports() {
        return annualReports;
    }

    /**
     * Returns the company's quarterly income statements, most recent first.
     *
     * @return the quarterly income statement reports, or an empty list if
     *         the request failed
     */
    public List<IncomeStatement> getQuarterlyReports() {
        return quarterlyReports;
    }

    /** Parses a raw {@code INCOME_STATEMENT} API response into an {@link IncomeStatementResponse}. */
    public static class IncomeStatementParser extends Parser<IncomeStatementResponse> {

        @Override
        public IncomeStatementResponse onParseError(String error) {
            return new IncomeStatementResponse(error);
        }

        @Override
        @SuppressWarnings("unchecked")
        public IncomeStatementResponse parse(Map<String, Object> object) {
            List<String> keys = new ArrayList<>(object.keySet());
            if (keys.isEmpty()) {
                return onParseError("Empty JSON returned by the API, the symbol might not be supported.");
            }
            try {
                String symbol = (String)object.get(keys.get(0));
                List<IncomeStatement> annualReports = Parser.parseJSONList(object.get(keys.get(1)), IncomeStatement.class);
                List<IncomeStatement> quarterlyReports = Parser.parseJSONList(object.get(keys.get(2)), IncomeStatement.class);
                return new IncomeStatementResponse(symbol, annualReports, quarterlyReports);
            } catch (ClassCastException | IndexOutOfBoundsException e) {
                return onParseError(object.get(keys.get(0)).toString());
            }
        }
    }

    @Override
    public String toString() {
        return "IncomeStatementResponse{" +
                "symbol='" + symbol + '\'' +
                ", annualReports=" + annualReports +
                ", quarterlyReports=" + quarterlyReports +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
