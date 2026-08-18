package com.designpatterns.behavioral.chainofresponsibility.applied;

public class LimitHandler extends ComplianceHandler {

    private final long maxAmountCents;

    public LimitHandler(long maxAmountCents) {
        this.maxAmountCents = maxAmountCents;
    }

    @Override
    protected ComplianceResult evaluate(ComplianceTransaction transaction) {
        if (transaction.amountCents() > maxAmountCents) {
            return ComplianceResult.rejected("LIMIT: amount exceeds the " + maxAmountCents + " cent threshold");
        }
        return ComplianceResult.pass();
    }
}
