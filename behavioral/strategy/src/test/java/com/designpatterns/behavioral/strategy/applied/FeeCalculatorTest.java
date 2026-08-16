package com.designpatterns.behavioral.strategy.applied;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FeeCalculatorTest {

    private final FeeCalculator calculator = FeeCalculator.withDefaultStrategies();

    @Test
    void pixTransfersAreFree() {
        assertThat(calculator.calculateFeeCents(TransactionType.PIX, 500_00L)).isZero();
    }

    @Test
    void tedChargesAFlatFeeRegardlessOfAmount() {
        assertThat(calculator.calculateFeeCents(TransactionType.TED, 100_00L)).isEqualTo(1000L);
        assertThat(calculator.calculateFeeCents(TransactionType.TED, 50_000_00L)).isEqualTo(1000L);
    }

    @Test
    void boletoChargesThePercentageFeeAboveTheMinimum() {
        assertThat(calculator.calculateFeeCents(TransactionType.BOLETO, 100_000_00L)).isEqualTo(2_000_00L);
    }

    @Test
    void boletoFallsBackToTheMinimumFeeForSmallAmounts() {
        assertThat(calculator.calculateFeeCents(TransactionType.BOLETO, 1_00L)).isEqualTo(350L);
    }

    @Test
    void rejectsAnUnregisteredTransactionType() {
        FeeCalculator calculatorWithoutBoleto = new FeeCalculator(Map.of(TransactionType.PIX, new PixFeeStrategy()));

        assertThatThrownBy(() -> calculatorWithoutBoleto.calculateFeeCents(TransactionType.BOLETO, 100L))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
