package com.designpatterns.behavioral.observer.applied;

public interface TransactionStatusObserver {

    void onStatusChanged(String transactionId, TransactionStatus newStatus);
}
