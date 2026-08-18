package com.designpatterns.creational.abstractfactory.applied;

public class DomesticPolicyDocument implements PolicyDocument {

    @Override
    public String render(String policyholderName) {
        return "APOLICE NACIONAL - Segurado: " + policyholderName;
    }
}
