package com.designpatterns.structural.decorator.applied;

public class LgpdAuditDecorator extends TransactionProcessorDecorator {

    public LgpdAuditDecorator(TransactionProcessor delegate) {
        super(delegate);
    }

    @Override
    public ProcessingResult process(Transaction transaction) {
        ProcessingResult result = delegate.process(transaction);
        return result.withNote("lgpd-audit: access to payer " + transaction.payerId() + " logged for compliance");
    }
}
