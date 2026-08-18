package com.designpatterns.behavioral.chainofresponsibility.applied;

public record ComplianceTransaction(
        String payerId,
        long amountCents,
        boolean payerVerified,
        boolean payerOnWatchlist,
        boolean flaggedHighRisk) {
}
