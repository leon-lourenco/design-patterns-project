package com.designpatterns.creational.singleton.applied;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SingletonRegistryConfig {

    @Bean
    public SpringManagedLimitRegistry springManagedLimitRegistry() {
        return new SpringManagedLimitRegistry(100_000_00L, 1_000_00L);
    }
}
