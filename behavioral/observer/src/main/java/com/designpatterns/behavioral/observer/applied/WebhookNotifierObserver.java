package com.designpatterns.behavioral.observer.applied;

import java.util.ArrayList;
import java.util.List;

public class WebhookNotifierObserver implements TransactionStatusObserver {

    private final List<String> deliveredWebhooks = new ArrayList<>();

    @Override
    public void onStatusChanged(String transactionId, TransactionStatus newStatus) {
        deliveredWebhooks.add(transactionId + ":" + newStatus);
    }

    public List<String> deliveredWebhooks() {
        return List.copyOf(deliveredWebhooks);
    }
}
