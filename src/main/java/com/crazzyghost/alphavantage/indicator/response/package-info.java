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

/**
 * The response DTOs shared across Alpha Vantage's technical indicator endpoints.
 * <p>
 * Most indicators report one numeric value per date and reuse
 * {@link com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorUnit} for
 * that value rather than defining their own unit type; which base response class an
 * indicator builds on —
 * {@link com.crazzyghost.alphavantage.indicator.response.SimpleIndicatorResponse},
 * {@link com.crazzyghost.alphavantage.indicator.response.PeriodicResponse},
 * {@link com.crazzyghost.alphavantage.indicator.response.PeriodicSeriesResponse},
 * {@link com.crazzyghost.alphavantage.indicator.response.SeriesResponse} or
 * {@link com.crazzyghost.alphavantage.indicator.response.PriceOscillatorResponse} —
 * depends on which request parameters it takes, not on its output shape.
 * Multi-field indicators such as {@code BBANDS} and {@code STOCH} are the
 * exception: each defines its own {@code *Response}/{@code *IndicatorUnit} pair, one
 * level deeper in a same-named subpackage such as {@code indicator.response.bbands}.
 *
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.response}.
 */
@Deprecated
package com.crazzyghost.alphavantage.indicator.response;
