package com.designpatterns.creational.abstractfactory.applied;

public class DomesticInsuranceProductFactory implements InsuranceProductFactory {

    @Override
    public PolicyDocument createPolicyDocument() {
        return new DomesticPolicyDocument();
    }

    @Override
    public PremiumCalculator createPremiumCalculator() {
        return new DomesticPremiumCalculator();
    }
}
