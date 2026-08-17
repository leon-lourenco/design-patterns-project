package com.designpatterns.creational.singleton.applied;

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
 * Ordered on purpose - see {@code LazyThreadSafeSingletonTest} for why: the concurrency test
 * must be the one that actually constructs the singleton under contention, not whichever test
 * happens to call getInstance() first.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HandRolledLimitRegistryTest {

    @Test
    @Order(1)
    void everyConcurrentValidatorSeesTheSameRegistryInstance() throws InterruptedException {
        int threadCount = 50;
        ExecutorService pool = Executors.newFixedThreadPool(threadCount);
        CountDownLatch ready = new CountDownLatch(threadCount);
        CountDownLatch start = new CountDownLatch(1);
        Set<HandRolledLimitRegistry> seenInstances = Collections.newSetFromMap(new ConcurrentHashMap<>());

        try {
            for (int i = 0; i < threadCount; i++) {
                pool.submit(() -> {
                    ready.countDown();
                    awaitUninterruptibly(start);
                    seenInstances.add(HandRolledLimitRegistry.getInstance());
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
    void exposesTheRegulatoryLimits() {
        LimitRegistry registry = HandRolledLimitRegistry.getInstance();

        assertThat(registry.dailyLimitCents()).isEqualTo(100_000_00L);
        assertThat(registry.nightlyLimitCents()).isEqualTo(1_000_00L);
    }

    private static void awaitUninterruptibly(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
