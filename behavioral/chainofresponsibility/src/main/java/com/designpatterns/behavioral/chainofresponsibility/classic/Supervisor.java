package com.designpatterns.behavioral.chainofresponsibility.classic;

public class Supervisor extends Approver {

    private static final long LIMIT_CENTS = 1_000_00L;

    @Override
    protected boolean canApprove(long amountCents) {
        return amountCents <= LIMIT_CENTS;
    }

    @Override
    protected String approvalMessage(long amountCents) {
        return "Supervisor approved " + amountCents + " cents";
    }
}
