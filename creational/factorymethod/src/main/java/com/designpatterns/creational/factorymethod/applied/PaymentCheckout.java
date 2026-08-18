package com.designpatterns.creational.factorymethod.applied;

import java.util.Map;

public class PaymentCheckout {

    private final Map<PaymentMethod, PaymentProviderCreator> creators;

    public PaymentCheckout(Map<PaymentMethod, PaymentProviderCreator> creators) {
        this.creators = Map.copyOf(creators);
    }

    public static PaymentCheckout withDefaultCreators() {
        return new PaymentCheckout(Map.of(
                PaymentMethod.PIX, new PixPaymentProviderCreator(),
                PaymentMethod.BOLETO, new BoletoPaymentProviderCreator(),
                PaymentMethod.CREDIT_CARD, new CreditCardPaymentProviderCreator()
        ));
    }

    public String charge(PaymentMethod method, long amountCents) {
        PaymentProviderCreator creator = creators.get(method);
        if (creator == null) {
            throw new IllegalArgumentException("no provider creator registered for " + method);
        }
        return creator.charge(amountCents);
    }
}
