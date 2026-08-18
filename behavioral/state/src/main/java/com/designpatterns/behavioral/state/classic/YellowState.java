package com.designpatterns.behavioral.state.classic;

public class YellowState implements TrafficLightState {

    @Override
    public TrafficLightState next() {
        return new RedState();
    }

    @Override
    public String color() {
        return "YELLOW";
    }
}
