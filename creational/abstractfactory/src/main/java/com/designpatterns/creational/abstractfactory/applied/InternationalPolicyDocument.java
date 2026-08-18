package com.designpatterns.creational.abstractfactory.applied;

public class InternationalPolicyDocument implements PolicyDocument {

    @Override
    public String render(String policyholderName) {
        return "INTERNATIONAL POLICY - Insured: " + policyholderName;
    }
}
