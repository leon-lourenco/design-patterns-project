package com.designpatterns.structural.decorator.applied;

public class FraudCheckDecorator extends TransactionProcessorDecorator {

    private static final long SUSPICIOUS_AMOUNT_CENTS = 50_000_00L;

    public FraudCheckDecorator(TransactionProcessor delegate) {
        super(delegate);
    }

    @Override
    public ProcessingResult process(Transaction transaction) {
        ProcessingResult result = delegate.process(transaction);
        if (transaction.amountCents() >= SUSPICIOUS_AMOUNT_CENTS) {
            return result.withNoteAndApproval("fraud-check: amount above threshold, held for review", false);
        }
        return result.withNote("fraud-check: amount within normal range");
    }
}
