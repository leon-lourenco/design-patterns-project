package com.designpatterns.creational.factorymethod.classic;

public class EmailNotification implements Notification {

    @Override
    public String deliver(String recipient, String message) {
        return "EMAIL to " + recipient + ": " + message;
    }
}
