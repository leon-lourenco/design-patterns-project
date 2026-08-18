package com.designpatterns.behavioral.chainofresponsibility.applied;

public class FraudHandler extends ComplianceHandler {

    @Override
    protected ComplianceResult evaluate(ComplianceTransaction transaction) {
        if (transaction.flaggedHighRisk()) {
            return ComplianceResult.rejected("FRAUD: transaction flagged as high risk");
        }
        return ComplianceResult.pass();
    }
}
