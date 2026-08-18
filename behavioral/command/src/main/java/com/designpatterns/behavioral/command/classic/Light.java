package com.designpatterns.behavioral.command.classic;

public class Light {

    private boolean on = false;

    public String turnOn() {
        on = true;
        return "Light is ON";
    }

    public String turnOff() {
        on = false;
        return "Light is OFF";
    }

    public boolean isOn() {
        return on;
    }
}
