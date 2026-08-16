package com.designpatterns.creational.singleton.classic;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class LazyThreadSafeSingletonTest {

    @Test
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
