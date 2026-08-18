package com.designpatterns.structural.composite.applied;

public class MinimumIncomeRule implements ApprovalRule {

    private final long minimumIncomeCents;

    public MinimumIncomeRule(long minimumIncomeCents) {
        this.minimumIncomeCents = minimumIncomeCents;
    }

    @Override
    public boolean isSatisfied(LoanApplication application) {
        return application.monthlyIncomeCents() >= minimumIncomeCents;
    }

    @Override
    public String description() {
        return "monthly income >= " + minimumIncomeCents + " cents";
    }
}
