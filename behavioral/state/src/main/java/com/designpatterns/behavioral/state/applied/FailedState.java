package com.designpatterns.behavioral.state.applied;

public class FailedState extends TransactionState {

    @Override
    public String name() {
        return "FAILED";
    }
}
