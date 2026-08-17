package com.designpatterns.structural.decorator.applied;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimitDecorator extends TransactionProcessorDecorator {

    private final int maxCallsPerPayer;
    private final Map<String, Integer> callCounts = new ConcurrentHashMap<>();

    public RateLimitDecorator(TransactionProcessor delegate, int maxCallsPerPayer) {
        super(delegate);
        this.maxCallsPerPayer = maxCallsPerPayer;
    }

    @Override
    public ProcessingResult process(Transaction transaction) {
        int count = callCounts.merge(transaction.payerId(), 1, Integer::sum);
        if (count > maxCallsPerPayer) {
            String note = "rate-limit: payer exceeded " + maxCallsPerPayer + " requests";
            return new ProcessingResult(transaction.id(), false, List.of(note));
        }
        return delegate.process(transaction).withNote("rate-limit: within quota (" + count + "/" + maxCallsPerPayer + ")");
    }
}
