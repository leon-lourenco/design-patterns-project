package com.designpatterns.behavioral.state.applied;

public class ProcessingState extends TransactionState {

    @Override
    public TransactionState settle() {
        return new SettledState();
    }

    @Override
    public TransactionState fail() {
        return new FailedState();
    }

    @Override
    public String name() {
        return "PROCESSING";
    }
}
