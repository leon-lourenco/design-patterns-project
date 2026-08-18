package com.designpatterns.behavioral.observer.applied;

import java.util.ArrayList;
import java.util.List;

public class TransactionStatusPublisher {

    private final List<TransactionStatusObserver> observers = new ArrayList<>();

    public void subscribe(TransactionStatusObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(TransactionStatusObserver observer) {
        observers.remove(observer);
    }

    public void publish(String transactionId, TransactionStatus newStatus) {
        for (TransactionStatusObserver observer : observers) {
            observer.onStatusChanged(transactionId, newStatus);
        }
    }
}
