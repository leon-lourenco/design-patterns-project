package com.designpatterns.creational.factorymethod.applied;

public class BoletoPaymentProvider implements PaymentProvider {

    @Override
    public String processPayment(long amountCents) {
        return "Boleto issued for " + amountCents + " cents, due in 3 business days";
    }
}
