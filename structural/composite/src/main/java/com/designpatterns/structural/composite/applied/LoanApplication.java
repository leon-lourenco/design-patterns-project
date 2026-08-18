package com.designpatterns.structural.composite.applied;

public record LoanApplication(long monthlyIncomeCents, long requestedAmountCents, boolean hasActiveDefaults) {
}
