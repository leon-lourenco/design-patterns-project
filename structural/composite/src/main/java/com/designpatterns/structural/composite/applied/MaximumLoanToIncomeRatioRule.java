package com.designpatterns.structural.composite.applied;

public class MaximumLoanToIncomeRatioRule implements ApprovalRule {

    private final double maxRatio;

    public MaximumLoanToIncomeRatioRule(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public boolean isSatisfied(LoanApplication application) {
        double ratio = (double) application.requestedAmountCents() / application.monthlyIncomeCents();
        return ratio <= maxRatio;
    }

    @Override
    public String description() {
        return "loan-to-income ratio <= " + maxRatio;
    }
}
