package com.designpatterns.behavioral.chainofresponsibility.applied;

public class KycHandler extends ComplianceHandler {

    @Override
    protected ComplianceResult evaluate(ComplianceTransaction transaction) {
        if (!transaction.payerVerified()) {
            return ComplianceResult.rejected("KYC: payer not verified");
        }
        return ComplianceResult.pass();
    }
}
