package com.designpatterns.creational.abstractfactory.applied;

/**
 * Depends only on {@link InsuranceProductFactory} — never on a concrete document type or a
 * concrete rate. Swapping the whole product family (domestic vs. international) is one
 * constructor argument, not a chain of conditionals scattered through this class.
 */
public class InsuranceProductIssuer {

    private final InsuranceProductFactory factory;

    public InsuranceProductIssuer(InsuranceProductFactory factory) {
        this.factory = factory;
    }

    public String issuePolicy(String policyholderName, long coverageAmountCents) {
        PolicyDocument document = factory.createPolicyDocument();
        PremiumCalculator calculator = factory.createPremiumCalculator();

        long premiumCents = calculator.calculatePremiumCents(coverageAmountCents);
        return document.render(policyholderName) + " | Premium: " + premiumCents + " cents";
    }
}
