package com.designpatterns.behavioral.state.classic;

public class RedState implements TrafficLightState {

    @Override
    public TrafficLightState next() {
        return new GreenState();
    }

    @Override
    public String color() {
        return "RED";
    }
}
