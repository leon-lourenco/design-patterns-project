package com.designpatterns.behavioral.command.classic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RemoteControlTest {

    @Test
    void pressingTheOnButtonTurnsTheLightOn() {
        Light light = new Light();
        RemoteControl remote = new RemoteControl();

        String result = remote.pressButton(new LightOnCommand(light));

        assertThat(result).isEqualTo("Light is ON");
        assertThat(light.isOn()).isTrue();
    }

    @Test
    void undoReversesTheLastCommandRegardlessOfWhichOneItWas() {
        Light light = new Light();
        RemoteControl remote = new RemoteControl();
        remote.pressButton(new LightOnCommand(light));

        String result = remote.pressUndo();

        assertThat(result).isEqualTo("Light is OFF");
        assertThat(light.isOn()).isFalse();
    }

    @Test
    void undoingTheOffCommandTurnsTheLightBackOn() {
        Light light = new Light();
        light.turnOn();
        RemoteControl remote = new RemoteControl();
        remote.pressButton(new LightOffCommand(light));

        remote.pressUndo();

        assertThat(light.isOn()).isTrue();
    }

    @Test
    void undoWithNothingPressedYetIsANoOp() {
        RemoteControl remote = new RemoteControl();

        assertThat(remote.pressUndo()).isEqualTo("Nothing to undo");
    }
}
