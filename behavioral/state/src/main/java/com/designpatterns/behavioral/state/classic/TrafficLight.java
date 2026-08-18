package com.designpatterns.behavioral.state.classic;

/**
 * The context never contains a color/state conditional itself - it just holds whatever state
 * object is current and delegates to it. Adding a new phase means adding a new state class,
 * never touching this one.
 */
public class TrafficLight {

    private TrafficLightState state = new RedState();

    public void advance() {
        state = state.next();
    }

    public String currentColor() {
        return state.color();
    }
}
