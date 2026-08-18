package com.designpatterns.behavioral.chainofresponsibility.applied;

public class AmlHandler extends ComplianceHandler {

    @Override
    protected ComplianceResult evaluate(ComplianceTransaction transaction) {
        if (transaction.payerOnWatchlist()) {
            return ComplianceResult.rejected("AML: payer is on a watchlist");
        }
        return ComplianceResult.pass();
    }
}
