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

import com.crazzyghost.alphavantage.Response;
import com.crazzyghost.alphavantage.parser.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The {@code OVERVIEW} endpoint's response: a company's profile and headline financial metrics, or
 * the error returned in their place.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 */
public class CompanyOverviewResponse implements Response {

    private final CompanyOverview overview;
    private final String errorMessage;

    private CompanyOverviewResponse(String error) {
        this.errorMessage = error;
        this.overview = null;
    }

    private CompanyOverviewResponse(CompanyOverview overview) {
        this.overview = overview;
        this.errorMessage = null;
    }

    /**
     * Parses a raw {@code OVERVIEW} API response into a {@code CompanyOverviewResponse}.
     *
     * @param objectMap the parsed JSON response body
     * @return the parsed response, or an error response if parsing fails
     */
    public static CompanyOverviewResponse of(Map<String, Object> objectMap) {
        Parser<CompanyOverviewResponse> parser = new CompanyOverviewParser();
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
     * Returns the parsed company overview.
     *
     * @return the company overview, or {@code null} if the request failed
     */
    public CompanyOverview getOverview() {
        return overview;
    }

    /** Parses a raw {@code OVERVIEW} API response into a {@link CompanyOverviewResponse}. */
    public static class CompanyOverviewParser extends Parser<CompanyOverviewResponse> {

        @Override
        public CompanyOverviewResponse onParseError(String error) {
            return new CompanyOverviewResponse(error);
        }

        @Override
        @SuppressWarnings("unchecked")
        public CompanyOverviewResponse parse(Map<String, Object> data) {
            List<String> keys = new ArrayList<>(data.keySet());
            if (keys.isEmpty()) {
                return onParseError(
                        "Empty JSON returned by the API, the symbol might not be supported.");
            }
            try {
                // data doesn't have a Symbol key meaning an error was returned
                Object symbol = data.getOrDefault("Symbol", null);
                if (symbol == null) throw new ClassCastException();
                CompanyOverview overview =
                        Parser.parseJSON(Parser.toJSON(data), CompanyOverview.class);
                return new CompanyOverviewResponse(overview);
            } catch (ClassCastException | IOException e) {
                return onParseError(data.get(keys.get(0)).toString());
            }
        }
    }

    @Override
    public String toString() {
        return "CompanyOverviewResponse{"
                + "overview="
                + overview
                + ", errorMessage='"
                + errorMessage
                + '\''
                + '}';
    }
}
