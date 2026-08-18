package com.designpatterns.creational.factorymethod.classic;

public class EmailNotificationCreator extends NotificationCreator {

    @Override
    protected Notification createNotification() {
        return new EmailNotification();
    }
}
