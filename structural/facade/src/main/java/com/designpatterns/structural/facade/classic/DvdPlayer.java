package com.designpatterns.structural.facade.classic;

public class DvdPlayer {

    public String on() {
        return "DVD player on";
    }

    public String play(String movie) {
        return "Playing \"" + movie + "\"";
    }
}
