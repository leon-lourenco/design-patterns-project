package com.designpatterns.creational.factorymethod.classic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NotificationCreatorTest {

    @Test
    void emailCreatorRoutesThroughAnEmailNotification() {
        NotificationCreator creator = new EmailNotificationCreator();

        String result = creator.send("alice@example.com", "your order shipped");

        assertThat(result).isEqualTo("EMAIL to alice@example.com: your order shipped");
    }

    @Test
    void smsCreatorRoutesThroughAnSmsNotification() {
        NotificationCreator creator = new SmsNotificationCreator();

        String result = creator.send("+15551234567", "your order shipped");

        assertThat(result).isEqualTo("SMS to +15551234567: your order shipped");
    }

    @Test
    void theSharedValidationInSendAppliesToEveryCreatorSubclass() {
        NotificationCreator emailCreator = new EmailNotificationCreator();
        NotificationCreator smsCreator = new SmsNotificationCreator();

        assertThatThrownBy(() -> emailCreator.send("alice@example.com", " "))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> smsCreator.send("+15551234567", ""))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> emailCreator.send("alice@example.com", null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
