package com.designpatterns.behavioral.templatemethod.classic;

import java.util.ArrayList;
import java.util.List;

public class Checkers extends Game {

    private final List<String> log = new ArrayList<>();

    @Override
    protected void initialize() {
        log.add("Checkers: setting up 24 pieces");
    }

    @Override
    protected void startPlay() {
        log.add("Checkers: dark pieces move first");
    }

    @Override
    protected void endPlay() {
        log.add("Checkers: no more legal moves for one side");
    }

    // announceWinner() is intentionally not overridden - the default hook (no-op) is fine here.

    public List<String> log() {
        return List.copyOf(log);
    }
}
