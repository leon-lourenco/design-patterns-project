package com.designpatterns.behavioral.command.classic;

public class LightOnCommand implements Command {

    private final Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public String execute() {
        return light.turnOn();
    }

    @Override
    public String undo() {
        return light.turnOff();
    }
}
