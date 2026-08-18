package com.designpatterns.structural.facade.applied;

import java.util.Set;

public class AccountVerificationService {

    private final Set<String> eligibleAccountIds;

    public AccountVerificationService(Set<String> eligibleAccountIds) {
        this.eligibleAccountIds = Set.copyOf(eligibleAccountIds);
    }

    public boolean isAccountEligible(String accountId) {
        return eligibleAccountIds.contains(accountId);
    }
}
