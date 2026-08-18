package com.designpatterns.behavioral.templatemethod.classic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {

    @Test
    void chessRunsEveryStepInOrderIncludingTheOverriddenHook() {
        Chess chess = new Chess();

        chess.play();

        assertThat(chess.log()).containsExactly(
                "Chess: setting up 32 pieces",
                "Chess: white moves first",
                "Chess: checkmate declared",
                "Chess: white wins by checkmate"
        );
    }

    @Test
    void checkersRunsTheRequiredStepsAndSkipsTheUnoverriddenHook() {
        Checkers checkers = new Checkers();

        checkers.play();

        assertThat(checkers.log()).containsExactly(
                "Checkers: setting up 24 pieces",
                "Checkers: dark pieces move first",
                "Checkers: no more legal moves for one side"
        );
    }
}
