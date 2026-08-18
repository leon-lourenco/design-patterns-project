package com.designpatterns.creational.factorymethod.applied;

public class PixPaymentProvider implements PaymentProvider {

    @Override
    public String processPayment(long amountCents) {
        return "PIX charge of " + amountCents + " cents processed instantly";
    }
}
