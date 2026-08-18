package com.designpatterns.structural.composite.applied;

public class NoActiveDefaultsRule implements ApprovalRule {

    @Override
    public boolean isSatisfied(LoanApplication application) {
        return !application.hasActiveDefaults();
    }

    @Override
    public String description() {
        return "no active defaults";
    }
}
