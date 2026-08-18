package com.designpatterns.creational.factorymethod.applied;

public class PixPaymentProviderCreator extends PaymentProviderCreator {

    @Override
    protected PaymentProvider createProvider() {
        return new PixPaymentProvider();
    }
}
