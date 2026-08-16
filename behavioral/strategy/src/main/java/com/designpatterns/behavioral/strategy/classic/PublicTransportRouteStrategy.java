package com.designpatterns.behavioral.strategy.classic;

public class PublicTransportRouteStrategy extends AbstractRouteStrategy {

    private static final double DETOUR_FACTOR = 1.5;
    private static final double AVERAGE_SPEED_KMH = 25.0;
    private static final int FIXED_WAIT_MINUTES = 10;

    @Override
    public Route buildRoute(Location origin, Location destination) {
        double distanceKm = straightLineDistanceKm(origin, destination) * DETOUR_FACTOR;
        int minutes = (int) Math.round(distanceKm / AVERAGE_SPEED_KMH * 60) + FIXED_WAIT_MINUTES;
        return new Route(distanceKm, minutes, "public transport");
    }
}
