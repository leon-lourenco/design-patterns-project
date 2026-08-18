package com.designpatterns.structural.facade.applied;

public record PortabilityResult(boolean scheduled, String fromBank, String message) {

    public static PortabilityResult scheduled(String fromBank, String notice) {
        return new PortabilityResult(true, fromBank, notice);
    }

    public static PortabilityResult rejected(String reason) {
        return new PortabilityResult(false, null, reason);
    }
}
