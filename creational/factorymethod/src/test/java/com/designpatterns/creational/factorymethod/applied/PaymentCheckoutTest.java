package com.designpatterns.creational.factorymethod.applied;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaymentCheckoutTest {

    private final PaymentCheckout checkout = PaymentCheckout.withDefaultCreators();

    @Test
    void routesToThePixProvider() {
        assertThat(checkout.charge(PaymentMethod.PIX, 5_000L))
                .isEqualTo("PIX charge of 5000 cents processed instantly");
    }

    @Test
    void routesToTheBoletoProvider() {
        assertThat(checkout.charge(PaymentMethod.BOLETO, 12_000L))
                .isEqualTo("Boleto issued for 12000 cents, due in 3 business days");
    }

    @Test
    void routesToTheCreditCardProvider() {
        assertThat(checkout.charge(PaymentMethod.CREDIT_CARD, 8_000L))
                .isEqualTo("Credit card charge of 8000 cents authorized");
    }

    @Test
    void theSharedValidationAppliesRegardlessOfMethod() {
        assertThatThrownBy(() -> checkout.charge(PaymentMethod.PIX, 0L))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rejectsAnUnregisteredPaymentMethod() {
        PaymentCheckout partialCheckout = new PaymentCheckout(Map.of(PaymentMethod.PIX, new PixPaymentProviderCreator()));

        assertThatThrownBy(() -> partialCheckout.charge(PaymentMethod.BOLETO, 1_000L))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
