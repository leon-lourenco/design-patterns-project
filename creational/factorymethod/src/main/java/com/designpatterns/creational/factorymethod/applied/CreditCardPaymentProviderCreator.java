package com.designpatterns.creational.factorymethod.applied;

public class CreditCardPaymentProviderCreator extends PaymentProviderCreator {

    @Override
    protected PaymentProvider createProvider() {
        return new CreditCardPaymentProvider();
    }
}
