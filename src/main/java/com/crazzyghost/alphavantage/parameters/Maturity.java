/*
 *
 * Copyright (c) 2022 Sylvester Sefa-Yeboah
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
package com.crazzyghost.alphavantage.parameters;

/**
 * The {@code maturity} API parameter, specifying the US Treasury bond maturity
 * a {@code TREASURY_YIELD} request returns the yield for.
 * <p>
 * Accepted by
 * {@link com.crazzyghost.alphavantage.economicindicator.request.TreasuryYieldRequest.Builder}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 */
public enum Maturity {

    /** A three-month maturity. Serializes to the wire value {@code "3month"}. */
    THREE_MONTH("3month"),

    /** A two-year maturity. Serializes to the wire value {@code "2year"}. */
    TWO_YEAR("2year"),

    /** A five-year maturity. Serializes to the wire value {@code "5year"}. */
    FIVE_YEAR("5year"),

    /** A seven-year maturity. Serializes to the wire value {@code "7year"}. */
    SEVEN_YEAR("7year"),

    /** A ten-year maturity. Serializes to the wire value {@code "10year"}. */
    TEN_YEAR("10year"),

    /** A thirty-year maturity. Serializes to the wire value {@code "30year"}. */
    THIRTY_YEAR("30year");

    private final String maturity;

    Maturity(String maturity){
        this.maturity = maturity;
    }


    @Override
    public String toString() {
        return this.maturity;
    }
}
