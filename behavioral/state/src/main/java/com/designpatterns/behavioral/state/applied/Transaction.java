package com.designpatterns.behavioral.state.applied;

public class Transaction {

    private final String id;
    private TransactionState state = new PendingState();

    public Transaction(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

    public void startProcessing() {
        state = state.startProcessing();
    }

    public void settle() {
        state = state.settle();
    }

    public void fail() {
        state = state.fail();
    }

    public String status() {
        return state.name();
    }
}
