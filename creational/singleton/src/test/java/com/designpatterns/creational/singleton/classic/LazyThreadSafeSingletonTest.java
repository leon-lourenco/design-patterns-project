package com.designpatterns.creational.singleton.classic;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Ordered on purpose: the concurrency test below must be the one that constructs the
 * singleton (all 50 threads racing through the synchronized block), not a coincidence of
 * JUnit's default method order. If {@code exposesTheSettingsLoadedAtConstruction} ran first,
 * it would construct the instance single-threaded, and the "concurrent" test would never
 * actually exercise contention on the lock - it would just find the instance already there.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LazyThreadSafeSingletonTest {

    @Test
    @Order(1)
    void returnsTheSameInstanceUnderConcurrentFirstAccess() throws InterruptedException {
        int threadCount = 50;
        ExecutorService pool = Executors.newFixedThreadPool(threadCount);
        CountDownLatch ready = new CountDownLatch(threadCount);
        CountDownLatch start = new CountDownLatch(1);
        Set<LazyThreadSafeSingleton> seenInstances = Collections.newSetFromMap(new ConcurrentHashMap<>());

        try {
            for (int i = 0; i < threadCount; i++) {
                pool.submit(() -> {
                    ready.countDown();
                    awaitUninterruptibly(start);
                    seenInstances.add(LazyThreadSafeSingleton.getInstance());
                });
            }

            assertThat(ready.await(5, TimeUnit.SECONDS)).isTrue();
            start.countDown();
            pool.shutdown();
            assertThat(pool.awaitTermination(5, TimeUnit.SECONDS)).isTrue();
        } finally {
            pool.shutdownNow();
        }

        assertThat(seenInstances).hasSize(1);
    }

    @Test
    @Order(2)
    void exposesTheSettingsLoadedAtConstruction() {
        assertThat(LazyThreadSafeSingleton.getInstance().getSetting("environment")).isEqualTo("production");
    }

    private static void awaitUninterruptibly(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
