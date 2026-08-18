package com.designpatterns.creational.abstractfactory.applied;

public class DomesticPremiumCalculator implements PremiumCalculator {

    private static final double RATE = 0.02;

    @Override
    public long calculatePremiumCents(long coverageAmountCents) {
        return Math.round(coverageAmountCents * RATE);
    }
}
