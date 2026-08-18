package com.designpatterns.behavioral.state.applied;

public class PendingState extends TransactionState {

    @Override
    public TransactionState startProcessing() {
        return new ProcessingState();
    }

    @Override
    public String name() {
        return "PENDING";
    }
}
