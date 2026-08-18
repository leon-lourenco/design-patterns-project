package com.designpatterns.creational.factorymethod.applied;

public class CreditCardPaymentProvider implements PaymentProvider {

    @Override
    public String processPayment(long amountCents) {
        return "Credit card charge of " + amountCents + " cents authorized";
    }
}
