package com.designpatterns.structural.facade.classic;

public class Amplifier {

    public String on() {
        return "Amplifier on";
    }

    public String setVolume(int level) {
        return "Amplifier volume set to " + level;
    }
}
