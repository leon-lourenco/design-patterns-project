package com.designpatterns.behavioral.chainofresponsibility.classic;

public class Manager extends Approver {

    private static final long LIMIT_CENTS = 10_000_00L;

    @Override
    protected boolean canApprove(long amountCents) {
        return amountCents <= LIMIT_CENTS;
    }

    @Override
    protected String approvalMessage(long amountCents) {
        return "Manager approved " + amountCents + " cents";
    }
}
