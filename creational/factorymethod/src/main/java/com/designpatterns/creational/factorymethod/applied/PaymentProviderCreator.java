package com.designpatterns.creational.factorymethod.applied;

/**
 * The shared step (amount validation) lives here once; only which {@link PaymentProvider} gets
 * created varies by subclass. New payment methods add a new creator, they never touch this
 * validation.
 */
public abstract class PaymentProviderCreator {

    public final String charge(long amountCents) {
        if (amountCents <= 0) {
            throw new IllegalArgumentException("amountCents must be positive");
        }
        PaymentProvider provider = createProvider();
        return provider.processPayment(amountCents);
    }

    protected abstract PaymentProvider createProvider();
}
