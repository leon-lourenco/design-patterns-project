package com.designpatterns.creational.factorymethod.classic;

public class SmsNotification implements Notification {

    @Override
    public String deliver(String recipient, String message) {
        return "SMS to " + recipient + ": " + message;
    }
}
