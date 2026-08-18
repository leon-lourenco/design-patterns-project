package com.designpatterns.structural.facade.classic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HomeTheaterFacadeTest {

    @Test
    void watchMovieOrchestratesEverySubsystemInTheRightOrder() {
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(new Amplifier(), new DvdPlayer(), new Projector());

        var log = homeTheater.watchMovie("The Matrix");

        assertThat(log).containsExactly(
                "Projector on",
                "Projector in widescreen mode",
                "Amplifier on",
                "Amplifier volume set to 5",
                "DVD player on",
                "Playing \"The Matrix\""
        );
    }
}
