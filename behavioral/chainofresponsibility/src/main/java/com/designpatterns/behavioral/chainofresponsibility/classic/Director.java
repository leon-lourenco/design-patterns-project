package com.designpatterns.behavioral.chainofresponsibility.classic;

public class Director extends Approver {

    private static final long LIMIT_CENTS = 100_000_00L;

    @Override
    protected boolean canApprove(long amountCents) {
        return amountCents <= LIMIT_CENTS;
    }

    @Override
    protected String approvalMessage(long amountCents) {
        return "Director approved " + amountCents + " cents";
    }
}
