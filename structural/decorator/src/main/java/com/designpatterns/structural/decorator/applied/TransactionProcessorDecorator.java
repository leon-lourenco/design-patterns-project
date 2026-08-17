package com.designpatterns.structural.decorator.applied;

public abstract class TransactionProcessorDecorator implements TransactionProcessor {

    protected final TransactionProcessor delegate;

    protected TransactionProcessorDecorator(TransactionProcessor delegate) {
        this.delegate = delegate;
    }
}
