package com.designpatterns.behavioral.chainofresponsibility.applied;

/**
 * The first handler to reject stops the chain right there - later handlers never even see the
 * transaction. KYC before AML before the limit check before fraud heuristics mirrors a real
 * compliance pipeline's order: verify who they are before screening them, screen before
 * checking business limits, limits before the more expensive fraud heuristics.
 */
public abstract class ComplianceHandler {

    private ComplianceHandler next;

    public ComplianceHandler next(ComplianceHandler next) {
        this.next = next;
        return this;
    }

    public final ComplianceResult check(ComplianceTransaction transaction) {
        ComplianceResult result = evaluate(transaction);
        if (!result.approved()) {
            return result;
        }
        if (next != null) {
            return next.check(transaction);
        }
        return ComplianceResult.pass();
    }

    protected abstract ComplianceResult evaluate(ComplianceTransaction transaction);
}
