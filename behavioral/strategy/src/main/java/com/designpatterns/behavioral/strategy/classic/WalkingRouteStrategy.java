package com.designpatterns.behavioral.strategy.classic;

public class WalkingRouteStrategy extends AbstractRouteStrategy {

    private static final double PATH_DETOUR_FACTOR = 1.1;
    private static final double AVERAGE_SPEED_KMH = 5.0;

    @Override
    public Route buildRoute(Location origin, Location destination) {
        double distanceKm = straightLineDistanceKm(origin, destination) * PATH_DETOUR_FACTOR;
        int minutes = (int) Math.round(distanceKm / AVERAGE_SPEED_KMH * 60);
        return new Route(distanceKm, minutes, "walking");
    }
}
