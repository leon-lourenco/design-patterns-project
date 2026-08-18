package com.designpatterns.creational.abstractfactory.applied;

public class InternationalInsuranceProductFactory implements InsuranceProductFactory {

    @Override
    public PolicyDocument createPolicyDocument() {
        return new InternationalPolicyDocument();
    }

    @Override
    public PremiumCalculator createPremiumCalculator() {
        return new InternationalPremiumCalculator();
    }
}
