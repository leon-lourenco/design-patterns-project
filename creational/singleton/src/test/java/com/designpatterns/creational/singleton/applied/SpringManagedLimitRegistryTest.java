package com.designpatterns.creational.singleton.applied;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class SpringManagedLimitRegistryTest {

    private AnnotationConfigApplicationContext context;

    @AfterEach
    void closeContext() {
        if (context != null) {
            context.close();
        }
    }

    @Test
    void springReturnsTheSameBeanInstanceOnEveryLookup() {
        context = new AnnotationConfigApplicationContext(SingletonRegistryConfig.class);

        SpringManagedLimitRegistry first = context.getBean(SpringManagedLimitRegistry.class);
        SpringManagedLimitRegistry second = context.getBean(SpringManagedLimitRegistry.class);

        assertThat(first).isSameAs(second);
        assertThat(first.dailyLimitCents()).isEqualTo(100_000_00L);
    }
}
