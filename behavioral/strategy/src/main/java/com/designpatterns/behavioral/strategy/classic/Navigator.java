package com.designpatterns.behavioral.strategy.classic;

public class Navigator {

    private RouteStrategy strategy;

    public Navigator(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    public Route route(Location origin, Location destination) {
        return strategy.buildRoute(origin, destination);
    }
}
