package com.designpatterns.behavioral.state.classic;

public interface TrafficLightState {

    TrafficLightState next();

    String color();
}
