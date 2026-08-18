package com.designpatterns.creational.abstractfactory.applied;

/**
 * Every method here returns a piece of the same regulatory family: a domestic policy document
 * paired with a domestic premium rate, never a domestic document with an international rate
 * (or vice versa). The factory is what guarantees that coherence, not something callers have
 * to remember to check.
 */
public interface InsuranceProductFactory {

    PolicyDocument createPolicyDocument();

    PremiumCalculator createPremiumCalculator();
}
