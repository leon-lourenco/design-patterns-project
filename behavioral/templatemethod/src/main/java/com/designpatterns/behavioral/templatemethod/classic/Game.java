package com.designpatterns.behavioral.templatemethod.classic;

/**
 * {@code play()} is the template: the order of steps is fixed and final, so no subclass can
 * reorder or skip a required step. {@code announceWinner()} is a hook, not a required step -
 * it has a default no-op implementation, so subclasses only override it if they actually need
 * to customize that point.
 */
public abstract class Game {

    public final void play() {
        initialize();
        startPlay();
        endPlay();
        announceWinner();
    }

    protected abstract void initialize();

    protected abstract void startPlay();

    protected abstract void endPlay();

    protected void announceWinner() {
        // Hook: subclasses may override this; doing nothing is a valid default.
    }
}
