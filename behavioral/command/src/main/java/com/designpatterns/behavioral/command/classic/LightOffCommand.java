package com.designpatterns.behavioral.command.classic;

public class LightOffCommand implements Command {

    private final Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public String execute() {
        return light.turnOff();
    }

    @Override
    public String undo() {
        return light.turnOn();
    }
}
