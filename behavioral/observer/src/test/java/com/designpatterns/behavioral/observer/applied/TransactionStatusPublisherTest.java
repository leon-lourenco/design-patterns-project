package com.designpatterns.behavioral.observer.applied;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionStatusPublisherTest {

    @Test
    void everyObserverReceivesEveryStatusChangeButOnlyPushNotifiesOnTerminalStates() {
        TransactionStatusPublisher publisher = new TransactionStatusPublisher();
        WebhookNotifierObserver webhook = new WebhookNotifierObserver();
        AuditLogObserver audit = new AuditLogObserver();
        PushNotificationObserver push = new PushNotificationObserver();
        publisher.subscribe(webhook);
        publisher.subscribe(audit);
        publisher.subscribe(push);

        publisher.publish("tx-1", TransactionStatus.PENDING);
        publisher.publish("tx-1", TransactionStatus.PROCESSING);
        publisher.publish("tx-1", TransactionStatus.SETTLED);

        assertThat(webhook.deliveredWebhooks()).containsExactly(
                "tx-1:PENDING", "tx-1:PROCESSING", "tx-1:SETTLED"
        );
        assertThat(audit.entries()).containsExactly(
                "transaction tx-1 moved to PENDING",
                "transaction tx-1 moved to PROCESSING",
                "transaction tx-1 moved to SETTLED"
        );
        assertThat(push.pushedMessages()).containsExactly("Your transaction tx-1 is settled");
    }

    @Test
    void pushNotifiesOnFailedToo() {
        TransactionStatusPublisher publisher = new TransactionStatusPublisher();
        PushNotificationObserver push = new PushNotificationObserver();
        publisher.subscribe(push);

        publisher.publish("tx-2", TransactionStatus.FAILED);

        assertThat(push.pushedMessages()).containsExactly("Your transaction tx-2 is failed");
    }

    @Test
    void anUnsubscribedObserverStopsReceivingStatusChanges() {
        TransactionStatusPublisher publisher = new TransactionStatusPublisher();
        AuditLogObserver audit = new AuditLogObserver();
        publisher.subscribe(audit);
        publisher.publish("tx-3", TransactionStatus.PENDING);

        publisher.unsubscribe(audit);
        publisher.publish("tx-3", TransactionStatus.SETTLED);

        assertThat(audit.entries()).containsExactly("transaction tx-3 moved to PENDING");
    }
}
