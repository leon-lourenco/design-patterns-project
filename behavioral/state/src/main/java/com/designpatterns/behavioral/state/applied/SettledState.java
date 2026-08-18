package com.designpatterns.behavioral.state.applied;

/**
 * Terminal state - overrides nothing, so every transition attempt inherits the base class's
 * rejection. There is no code path back to PENDING or PROCESSING from here.
 */
public class SettledState extends TransactionState {

    @Override
    public String name() {
        return "SETTLED";
    }
}
