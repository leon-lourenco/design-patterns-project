package com.designpatterns.behavioral.chainofresponsibility.classic;

/**
 * Each link only knows its own approval limit and the next link in the chain - never the
 * whole chain's shape. Adding a new approval tier means adding a new link and wiring it in,
 * not editing an existing one.
 */
public abstract class Approver {

    private Approver next;

    public Approver next(Approver next) {
        this.next = next;
        return this;
    }

    public final String approve(long amountCents) {
        if (canApprove(amountCents)) {
            return approvalMessage(amountCents);
        }
        if (next != null) {
            return next.approve(amountCents);
        }
        return "No approver available for " + amountCents + " cents";
    }

    protected abstract boolean canApprove(long amountCents);

    protected abstract String approvalMessage(long amountCents);
}
