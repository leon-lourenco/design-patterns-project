package com.designpatterns.structural.composite.applied;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A composite rule: it satisfies LoanApplication the same way a leaf rule does, but delegates
 * to every child rule underneath it - and a child can itself be another rule group, letting
 * groups nest to any depth without the caller ever needing to know it's looking at a group
 * instead of a single rule.
 */
public class AllOfRuleGroup implements ApprovalRule {

    private final List<ApprovalRule> rules;

    public AllOfRuleGroup(List<ApprovalRule> rules) {
        this.rules = List.copyOf(rules);
    }

    @Override
    public boolean isSatisfied(LoanApplication application) {
        return rules.stream().allMatch(rule -> rule.isSatisfied(application));
    }

    @Override
    public String description() {
        String joined = rules.stream().map(ApprovalRule::description).collect(Collectors.joining(", "));
        return "ALL OF (" + joined + ")";
    }
}
