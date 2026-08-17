package com.designpatterns.structural.decorator.applied;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionProcessorDecoratorTest {

    @Test
    void approvesANormalTransactionAndRecordsEachLayersNoteInWrappingOrder() {
        TransactionProcessor pipeline = new LgpdAuditDecorator(new FraudCheckDecorator(new CoreTransactionProcessor()));
        Transaction transaction = new Transaction("tx-1", 10_000_00L, "payer-1");

        ProcessingResult result = pipeline.process(transaction);

        assertThat(result.approved()).isTrue();
        assertThat(result.auditTrail()).containsExactly(
                "core: transaction accepted",
                "fraud-check: amount within normal range",
                "lgpd-audit: access to payer payer-1 logged for compliance"
        );
    }

    @Test
    void flagsATransactionAboveTheFraudThreshold() {
        TransactionProcessor pipeline = new FraudCheckDecorator(new CoreTransactionProcessor());
        Transaction transaction = new Transaction("tx-2", 60_000_00L, "payer-2");

        ProcessingResult result = pipeline.process(transaction);

        assertThat(result.approved()).isFalse();
        assertThat(result.auditTrail()).anyMatch(note -> note.contains("fraud-check"));
    }

    @Test
    void rateLimitDecoratorShortCircuitsWithoutCallingTheRestOfThePipelineOnceTheQuotaIsExceeded() {
        TransactionProcessor pipeline = new RateLimitDecorator(new CoreTransactionProcessor(), 2);
        Transaction transaction = new Transaction("tx-3", 1_00L, "payer-3");

        pipeline.process(transaction);
        pipeline.process(transaction);
        ProcessingResult thirdCall = pipeline.process(transaction);

        assertThat(thirdCall.approved()).isFalse();
        assertThat(thirdCall.auditTrail()).containsExactly("rate-limit: payer exceeded 2 requests");
    }

    @Test
    void rateLimitDecoratorPassesThroughAndAnnotatesCallsWithinQuota() {
        TransactionProcessor pipeline = new RateLimitDecorator(new CoreTransactionProcessor(), 5);
        Transaction transaction = new Transaction("tx-4", 1_00L, "payer-4");

        ProcessingResult result = pipeline.process(transaction);

        assertThat(result.approved()).isTrue();
        assertThat(result.auditTrail()).containsExactly(
                "core: transaction accepted",
                "rate-limit: within quota (1/5)"
        );
    }
}
