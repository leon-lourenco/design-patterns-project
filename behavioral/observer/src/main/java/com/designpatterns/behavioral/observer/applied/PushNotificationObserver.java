package com.designpatterns.behavioral.observer.applied;

import java.util.ArrayList;
import java.util.List;

public class PushNotificationObserver implements TransactionStatusObserver {

    private final List<String> pushedMessages = new ArrayList<>();

    @Override
    public void onStatusChanged(String transactionId, TransactionStatus newStatus) {
        if (newStatus == TransactionStatus.SETTLED || newStatus == TransactionStatus.FAILED) {
            pushedMessages.add("Your transaction " + transactionId + " is " + newStatus.name().toLowerCase());
        }
    }

    public List<String> pushedMessages() {
        return List.copyOf(pushedMessages);
    }
}
