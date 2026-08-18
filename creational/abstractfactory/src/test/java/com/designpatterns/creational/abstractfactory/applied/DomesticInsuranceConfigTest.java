package com.designpatterns.creational.abstractfactory.applied;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class DomesticInsuranceConfigTest {

    private AnnotationConfigApplicationContext context;

    @AfterEach
    void closeContext() {
        if (context != null) {
            context.close();
        }
    }

    @Test
    void theConfigurationClassProducesACoherentDomesticBeanFamily() {
        context = new AnnotationConfigApplicationContext(DomesticInsuranceConfig.class);

        PolicyDocument document = context.getBean(PolicyDocument.class);
        PremiumCalculator calculator = context.getBean(PremiumCalculator.class);

        assertThat(document).isInstanceOf(DomesticPolicyDocument.class);
        assertThat(calculator).isInstanceOf(DomesticPremiumCalculator.class);
        assertThat(calculator.calculatePremiumCents(100_000_00L)).isEqualTo(200000L);
    }
}
