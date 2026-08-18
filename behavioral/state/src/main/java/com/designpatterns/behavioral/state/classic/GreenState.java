package com.designpatterns.behavioral.state.classic;

public class GreenState implements TrafficLightState {

    @Override
    public TrafficLightState next() {
        return new YellowState();
    }

    @Override
    public String color() {
        return "GREEN";
    }
}
