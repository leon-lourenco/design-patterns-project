package com.designpatterns.behavioral.strategy.classic;

abstract class AbstractRouteStrategy implements RouteStrategy {

    protected double straightLineDistanceKm(Location origin, Location destination) {
        double dx = destination.x() - origin.x();
        double dy = destination.y() - origin.y();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
