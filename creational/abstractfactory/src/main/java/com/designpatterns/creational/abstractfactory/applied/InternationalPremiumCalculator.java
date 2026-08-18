package com.designpatterns.creational.abstractfactory.applied;

public class InternationalPremiumCalculator implements PremiumCalculator {

    private static final double RATE = 0.035;

    @Override
    public long calculatePremiumCents(long coverageAmountCents) {
        return Math.round(coverageAmountCents * RATE);
    }
}
