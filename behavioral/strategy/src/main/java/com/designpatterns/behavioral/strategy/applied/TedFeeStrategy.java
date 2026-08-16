package com.designpatterns.behavioral.strategy.applied;

public class TedFeeStrategy implements FeeCalculationStrategy {

    private static final long FLAT_FEE_CENTS = 1000L;

    @Override
    public long calculateFeeCents(long amountCents) {
        return FLAT_FEE_CENTS;
    }
}
