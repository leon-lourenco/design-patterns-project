package com.designpatterns.behavioral.state.applied;

/**
 * Each transition method has a safe default (reject) here, so a concrete state only needs to
 * override the transitions it genuinely allows. A terminal state (settled, failed) overrides
 * nothing at all - every transition attempt from it fails, which is exactly correct.
 */
public abstract class TransactionState {

    public TransactionState startProcessing() {
        throw new IllegalStateException("cannot start processing from " + name());
    }

    public TransactionState settle() {
        throw new IllegalStateException("cannot settle from " + name());
    }

    public TransactionState fail() {
        throw new IllegalStateException("cannot fail from " + name());
    }

    public abstract String name();
}
