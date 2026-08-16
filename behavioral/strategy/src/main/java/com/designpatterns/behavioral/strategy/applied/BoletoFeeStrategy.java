package com.designpatterns.behavioral.strategy.applied;

public class BoletoFeeStrategy implements FeeCalculationStrategy {

    private static final long MINIMUM_FEE_CENTS = 350L;
    private static final double RATE = 0.02;

    @Override
    public long calculateFeeCents(long amountCents) {
        long percentageFee = Math.round(amountCents * RATE);
        return Math.max(MINIMUM_FEE_CENTS, percentageFee);
    }
}
