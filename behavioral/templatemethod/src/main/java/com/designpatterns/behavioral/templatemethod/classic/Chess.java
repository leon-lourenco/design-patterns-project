package com.designpatterns.behavioral.templatemethod.classic;

import java.util.ArrayList;
import java.util.List;

public class Chess extends Game {

    private final List<String> log = new ArrayList<>();

    @Override
    protected void initialize() {
        log.add("Chess: setting up 32 pieces");
    }

    @Override
    protected void startPlay() {
        log.add("Chess: white moves first");
    }

    @Override
    protected void endPlay() {
        log.add("Chess: checkmate declared");
    }

    @Override
    protected void announceWinner() {
        log.add("Chess: white wins by checkmate");
    }

    public List<String> log() {
        return List.copyOf(log);
    }
}
