/*
 *
 * Copyright (c) 2025 Sylvester Sefa-Yeboah
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
package com.crazzyghost.alphavantage.search;

import com.crazzyghost.alphavantage.parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchResponse {
    private final List<Match> bestMatches;
    private final String errorMessage;

    private SearchResponse(List<Match> bestMatches) {
        this.bestMatches = bestMatches;
        this.errorMessage = null;
    }

    private SearchResponse(String errorMessage) {
        this.errorMessage = errorMessage;
        this.bestMatches = new ArrayList<>();
    }

    public List<Match> getBestMatches() {
        return bestMatches;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static SearchResponse of(Map<String, Object> data) {
        MarketStatusParser parser = new MarketStatusParser();
        return parser.parse(data);
    }

    public static class MarketStatusParser extends Parser<SearchResponse> {
        @Override
        public SearchResponse onParseError(String error) {
            return new SearchResponse(error);
        }

        @Override
        public SearchResponse parse(Map<String, Object> object) {
            List<String> keys = new ArrayList<>(object.keySet());
            if (keys.isEmpty()) {
                return onParseError("Empty JSON returned by the API, the symbol might not be supported.");
            }
            try {
                int dataIndex = 0;
                String dataKey = keys.get(dataIndex);
                Object dataOrErrorMessage = object.get(dataKey);
                if (dataOrErrorMessage instanceof String) {
                    return onParseError((String) dataOrErrorMessage);
                }
                List<Match> data = Parser.parseJSONList(object.get(dataKey), Match.class);
                return new SearchResponse(data);
            } catch (ClassCastException | IndexOutOfBoundsException e) {
                return onParseError(object.get(keys.get(0)).toString());
            }
        }
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "bestMatches=" + bestMatches +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
