package com.designpatterns.behavioral.state.applied;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TransactionTest {

    @Test
    void startsPendingAndFollowsTheHappyPathToSettled() {
        Transaction transaction = new Transaction("tx-1");
        assertThat(transaction.id()).isEqualTo("tx-1");
        assertThat(transaction.status()).isEqualTo("PENDING");

        transaction.startProcessing();
        assertThat(transaction.status()).isEqualTo("PROCESSING");

        transaction.settle();
        assertThat(transaction.status()).isEqualTo("SETTLED");
    }

    @Test
    void followsTheAlternatePathToFailed() {
        Transaction transaction = new Transaction("tx-2");

        transaction.startProcessing();
        transaction.fail();

        assertThat(transaction.status()).isEqualTo("FAILED");
    }

    @Test
    void rejectsSettlingBeforeProcessingStarted() {
        Transaction transaction = new Transaction("tx-3");

        assertThatThrownBy(transaction::settle).isInstanceOf(IllegalStateException.class);
        assertThat(transaction.status()).isEqualTo("PENDING");
    }

    @Test
    void rejectsAnyTransitionOnceSettledIsTerminal() {
        Transaction transaction = new Transaction("tx-4");
        transaction.startProcessing();
        transaction.settle();

        assertThatThrownBy(transaction::startProcessing).isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(transaction::settle).isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(transaction::fail).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void rejectsAnyTransitionOnceFailedIsTerminal() {
        Transaction transaction = new Transaction("tx-5");
        transaction.startProcessing();
        transaction.fail();

        assertThatThrownBy(transaction::startProcessing).isInstanceOf(IllegalStateException.class);
    }
}
