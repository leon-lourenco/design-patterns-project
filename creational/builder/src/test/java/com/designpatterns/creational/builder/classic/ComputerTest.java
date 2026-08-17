package com.designpatterns.creational.builder.classic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ComputerTest {

    @Test
    void appliesSensibleDefaultsWhenOnlyTheRequiredFieldIsSet() {
        Computer computer = Computer.builder("Ryzen 7").build();

        assertThat(computer.cpu()).isEqualTo("Ryzen 7");
        assertThat(computer.ramGb()).isEqualTo(8);
        assertThat(computer.storageGb()).isEqualTo(256);
        assertThat(computer.hasGraphicsCard()).isFalse();
    }

    @Test
    void overridesOnlyTheFieldsExplicitlySet() {
        Computer computer = Computer.builder("Ryzen 9")
                .ramGb(32)
                .storageGb(1024)
                .withGraphicsCard()
                .build();

        assertThat(computer.ramGb()).isEqualTo(32);
        assertThat(computer.storageGb()).isEqualTo(1024);
        assertThat(computer.hasGraphicsCard()).isTrue();
    }

    @Test
    void rejectsANullCpu() {
        assertThatThrownBy(() -> Computer.builder(null)).isInstanceOf(NullPointerException.class);
    }
}
