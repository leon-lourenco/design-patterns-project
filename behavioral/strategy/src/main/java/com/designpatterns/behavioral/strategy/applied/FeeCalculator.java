package com.designpatterns.behavioral.strategy.applied;

import java.util.Map;

public class FeeCalculator {

    private final Map<TransactionType, FeeCalculationStrategy> strategies;

    public FeeCalculator(Map<TransactionType, FeeCalculationStrategy> strategies) {
        this.strategies = Map.copyOf(strategies);
    }

    public static FeeCalculator withDefaultStrategies() {
        return new FeeCalculator(Map.of(
                TransactionType.PIX, new PixFeeStrategy(),
                TransactionType.TED, new TedFeeStrategy(),
                TransactionType.BOLETO, new BoletoFeeStrategy()
        ));
    }

    public long calculateFeeCents(TransactionType type, long amountCents) {
        FeeCalculationStrategy strategy = strategies.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("no fee strategy registered for " + type);
        }
        return strategy.calculateFeeCents(amountCents);
    }
}
