package com.crazzyghost.alphavantage.indicator.request;

/**
 * Request for indicators that take no parameters beyond the shared
 * {@code function}, {@code symbol}, {@code interval}, and {@code datatype},
 * such as {@code VWAP}, {@code BOP}, {@code TRANGE}, {@code AD}, and
 * {@code OBV}.
 *
 * @author Sylvester Sefa-Yeboah
 * @since 1.7.0
 * @deprecated Replaced by {@link com.crazzyghost.alphavantage.technicalindicator.request.SimpleTechnicalIndicatorRequest}
 */
@Deprecated
public class SimpleIndicatorRequest extends IndicatorRequest {

    /**
     * Copies the values assembled by {@code builder} into this request.
     *
     * @param builder the builder holding this request's configured values
     */
    private SimpleIndicatorRequest(Builder builder) {
        super(builder);
    }

    /**
     * Fluent builder for {@link SimpleIndicatorRequest}.
     */
    public static class Builder extends IndicatorRequest.Builder<Builder>{

        /**
         * Builds the configured {@link SimpleIndicatorRequest}.
         *
         * @return the built request
         */
        @Override
        public IndicatorRequest build() {
            return new SimpleIndicatorRequest(this);
        }

    }
}