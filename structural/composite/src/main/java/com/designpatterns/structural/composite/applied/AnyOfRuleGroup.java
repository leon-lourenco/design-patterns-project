package com.designpatterns.structural.composite.applied;

import java.util.List;
import java.util.stream.Collectors;

public class AnyOfRuleGroup implements ApprovalRule {

    private final List<ApprovalRule> rules;

    public AnyOfRuleGroup(List<ApprovalRule> rules) {
        this.rules = List.copyOf(rules);
    }

    @Override
    public boolean isSatisfied(LoanApplication application) {
        return rules.stream().anyMatch(rule -> rule.isSatisfied(application));
    }

    @Override
    public String description() {
        String joined = rules.stream().map(ApprovalRule::description).collect(Collectors.joining(", "));
        return "ANY OF (" + joined + ")";
    }
}
