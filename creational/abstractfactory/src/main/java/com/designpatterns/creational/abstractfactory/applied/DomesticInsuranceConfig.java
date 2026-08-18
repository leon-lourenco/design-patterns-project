package com.designpatterns.creational.abstractfactory.applied;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * A Spring {@code @Configuration} class is, in practice, an Abstract Factory: each
 * {@code @Bean} method is a creation method, and the whole class is a factory of one coherent
 * product family. Swapping the domestic family for the international one is a matter of
 * activating a different configuration class, exactly like swapping
 * {@link DomesticInsuranceProductFactory} for {@link InternationalInsuranceProductFactory}
 * in the hand-rolled version above — the container just does the instantiation and the wiring
 * for you.
 */
@Configuration
public class DomesticInsuranceConfig {

    @Bean
    public PolicyDocument policyDocument() {
        return new DomesticPolicyDocument();
    }

    @Bean
    public PremiumCalculator premiumCalculator() {
        return new DomesticPremiumCalculator();
    }
}
