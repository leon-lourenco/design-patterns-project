package com.designpatterns.structural.composite.applied;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ApprovalRuleTest {

    private final ApprovalRule standardRules = new AllOfRuleGroup(List.of(
            new MinimumIncomeRule(5_000_00L),
            new MaximumLoanToIncomeRatioRule(5.0),
            new NoActiveDefaultsRule()
    ));

    @Test
    void anApplicationThatClearsEveryRuleIsApproved() {
        LoanApplication application = new LoanApplication(10_000_00L, 30_000_00L, false);

        assertThat(standardRules.isSatisfied(application)).isTrue();
    }

    @Test
    void anApplicationBelowTheMinimumIncomeIsRejected() {
        LoanApplication application = new LoanApplication(1_000_00L, 3_000_00L, false);

        assertThat(standardRules.isSatisfied(application)).isFalse();
    }

    @Test
    void anApplicationWithActiveDefaultsIsRejectedEvenIfEverythingElsePasses() {
        LoanApplication application = new LoanApplication(10_000_00L, 30_000_00L, true);

        assertThat(standardRules.isSatisfied(application)).isFalse();
    }

    @Test
    void ruleGroupsCanNestOtherRuleGroups() {
        ApprovalRule nestedRules = new AllOfRuleGroup(List.of(
                new MinimumIncomeRule(5_000_00L),
                new AnyOfRuleGroup(List.of(
                        new MaximumLoanToIncomeRatioRule(1.0),
                        new NoActiveDefaultsRule()
                ))
        ));
        // Ratio is too high (3.0 > 1.0) but the "no active defaults" alternative still holds,
        // so the nested AnyOf is satisfied, and so is the outer AllOf.
        LoanApplication application = new LoanApplication(10_000_00L, 30_000_00L, false);

        assertThat(nestedRules.isSatisfied(application)).isTrue();
    }

    @Test
    void aNestedAnyOfGroupFailsWhenNoAlternativeHolds() {
        ApprovalRule nestedRules = new AnyOfRuleGroup(List.of(
                new MaximumLoanToIncomeRatioRule(1.0),
                new NoActiveDefaultsRule()
        ));
        LoanApplication application = new LoanApplication(10_000_00L, 30_000_00L, true);

        assertThat(nestedRules.isSatisfied(application)).isFalse();
    }

    @Test
    void groupDescriptionsJoinEveryChildRulesDescription() {
        ApprovalRule group = new AllOfRuleGroup(List.of(
                new MinimumIncomeRule(5_000_00L),
                new NoActiveDefaultsRule()
        ));

        assertThat(group.description()).isEqualTo("ALL OF (monthly income >= 500000 cents, no active defaults)");
    }

    @Test
    void anyOfGroupDescriptionJoinsItsChildRulesDescriptionsToo() {
        ApprovalRule group = new AnyOfRuleGroup(List.of(
                new MaximumLoanToIncomeRatioRule(5.0),
                new NoActiveDefaultsRule()
        ));

        assertThat(group.description()).isEqualTo("ANY OF (loan-to-income ratio <= 5.0, no active defaults)");
    }
}
