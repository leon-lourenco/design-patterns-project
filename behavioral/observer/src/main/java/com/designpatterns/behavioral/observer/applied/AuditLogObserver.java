package com.designpatterns.behavioral.observer.applied;

import java.util.ArrayList;
import java.util.List;

public class AuditLogObserver implements TransactionStatusObserver {

    private final List<String> entries = new ArrayList<>();

    @Override
    public void onStatusChanged(String transactionId, TransactionStatus newStatus) {
        entries.add("transaction " + transactionId + " moved to " + newStatus);
    }

    public List<String> entries() {
        return List.copyOf(entries);
    }
}
