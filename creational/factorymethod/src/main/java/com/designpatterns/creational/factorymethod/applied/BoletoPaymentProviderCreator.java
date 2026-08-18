package com.designpatterns.creational.factorymethod.applied;

public class BoletoPaymentProviderCreator extends PaymentProviderCreator {

    @Override
    protected PaymentProvider createProvider() {
        return new BoletoPaymentProvider();
    }
}
