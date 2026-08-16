package com.designpatterns.structural.adapter.applied;

public record AccountSnapshot(String accountNumber, String holderName, long balanceCents, boolean active) {
}
