package com.designpatterns.structural.composite.applied;

public interface ApprovalRule {

    boolean isSatisfied(LoanApplication application);

    String description();
}
