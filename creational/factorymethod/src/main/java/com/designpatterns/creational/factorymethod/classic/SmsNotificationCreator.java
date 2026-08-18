package com.designpatterns.creational.factorymethod.classic;

public class SmsNotificationCreator extends NotificationCreator {

    @Override
    protected Notification createNotification() {
        return new SmsNotification();
    }
}
