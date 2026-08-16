package com.designpatterns.behavioral.strategy.applied;

public class PixFeeStrategy implements FeeCalculationStrategy {

    @Override
    public long calculateFeeCents(long amountCents) {
        // BACEN mandates free PIX transfers between individuals.
        return 0L;
    }
}
