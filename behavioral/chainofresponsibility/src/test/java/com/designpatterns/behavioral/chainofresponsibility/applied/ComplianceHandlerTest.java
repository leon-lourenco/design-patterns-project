package com.designpatterns.behavioral.chainofresponsibility.applied;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ComplianceHandlerTest {

    private static final long LIMIT_CENTS = 50_000_00L;

    private final ComplianceHandler chain =
            new KycHandler().next(new AmlHandler().next(new LimitHandler(LIMIT_CENTS).next(new FraudHandler())));

    @Test
    void approvesATransactionThatClearsEveryCheck() {
        ComplianceTransaction transaction = new ComplianceTransaction("payer-1", 10_000_00L, true, false, false);

        ComplianceResult result = chain.check(transaction);

        assertThat(result.approved()).isTrue();
        assertThat(result.reason()).isNull();
    }

    @Test
    void anUnverifiedPayerIsRejectedByKycBeforeAnyLaterCheckRuns() {
        ComplianceTransaction transaction = new ComplianceTransaction("payer-2", 10_000_00L, false, true, true);

        ComplianceResult result = chain.check(transaction);

        assertThat(result.approved()).isFalse();
        assertThat(result.reason()).isEqualTo("KYC: payer not verified");
    }

    @Test
    void aWatchlistedPayerIsRejectedByAml() {
        ComplianceTransaction transaction = new ComplianceTransaction("payer-3", 10_000_00L, true, true, false);

        ComplianceResult result = chain.check(transaction);

        assertThat(result.reason()).isEqualTo("AML: payer is on a watchlist");
    }

    @Test
    void anAmountAboveTheThresholdIsRejectedByTheLimitHandler() {
        ComplianceTransaction transaction = new ComplianceTransaction("payer-4", 60_000_00L, true, false, false);

        ComplianceResult result = chain.check(transaction);

        assertThat(result.reason()).isEqualTo("LIMIT: amount exceeds the 5000000 cent threshold");
    }

    @Test
    void aHighRiskFlaggedTransactionThatClearsEverythingElseIsRejectedByFraud() {
        ComplianceTransaction transaction = new ComplianceTransaction("payer-5", 10_000_00L, true, false, true);

        ComplianceResult result = chain.check(transaction);

        assertThat(result.reason()).isEqualTo("FRAUD: transaction flagged as high risk");
    }
}
