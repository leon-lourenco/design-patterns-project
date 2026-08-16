package com.designpatterns.behavioral.strategy.classic;

public interface RouteStrategy {

    Route buildRoute(Location origin, Location destination);
}
