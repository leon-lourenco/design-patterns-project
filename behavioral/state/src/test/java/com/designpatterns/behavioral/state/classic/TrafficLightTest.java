package com.designpatterns.behavioral.state.classic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TrafficLightTest {

    @Test
    void cyclesThroughRedGreenYellowAndBackToRed() {
        TrafficLight light = new TrafficLight();

        assertThat(light.currentColor()).isEqualTo("RED");

        light.advance();
        assertThat(light.currentColor()).isEqualTo("GREEN");

        light.advance();
        assertThat(light.currentColor()).isEqualTo("YELLOW");

        light.advance();
        assertThat(light.currentColor()).isEqualTo("RED");
    }
}
