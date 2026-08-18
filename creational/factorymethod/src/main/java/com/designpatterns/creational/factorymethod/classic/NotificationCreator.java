package com.designpatterns.creational.factorymethod.classic;

/**
 * The template step ({@code send}) is fixed and shared by every subclass; only which
 * {@link Notification} gets created varies, decided by the abstract factory method. This is
 * what distinguishes Factory Method from simply picking an interchangeable behavior (Strategy):
 * there's a real shared algorithm here, not just delegation.
 */
public abstract class NotificationCreator {

    public final String send(String recipient, String message) {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("message must not be blank");
        }
        Notification notification = createNotification();
        return notification.deliver(recipient, message);
    }

    protected abstract Notification createNotification();
}
