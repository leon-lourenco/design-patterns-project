package com.designpatterns.structural.facade.applied;

public class NotificationService {

    public String notifyPortabilityScheduled(String accountId, String fromBank) {
        return "Notice sent to account " + accountId + ": portability from " + fromBank + " scheduled";
    }
}
