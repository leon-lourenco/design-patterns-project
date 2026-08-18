package com.designpatterns.structural.facade.classic;

import java.util.ArrayList;
import java.util.List;

/**
 * The caller asks for one thing ("watch a movie") instead of knowing that a projector, an
 * amplifier, and a DVD player each need to be powered on and configured in a specific order.
 * The subsystem classes are unchanged and still usable directly - the facade just adds a
 * simpler entry point on top.
 */
public class HomeTheaterFacade {

    private final Amplifier amplifier;
    private final DvdPlayer dvdPlayer;
    private final Projector projector;

    public HomeTheaterFacade(Amplifier amplifier, DvdPlayer dvdPlayer, Projector projector) {
        this.amplifier = amplifier;
        this.dvdPlayer = dvdPlayer;
        this.projector = projector;
    }

    public List<String> watchMovie(String movie) {
        List<String> log = new ArrayList<>();
        log.add(projector.on());
        log.add(projector.wideScreenMode());
        log.add(amplifier.on());
        log.add(amplifier.setVolume(5));
        log.add(dvdPlayer.on());
        log.add(dvdPlayer.play(movie));
        return log;
    }
}
