package com.designpatterns.behavioral.strategy.classic;

public class DrivingRouteStrategy extends AbstractRouteStrategy {

    private static final double ROAD_DETOUR_FACTOR = 1.3;
    private static final double AVERAGE_SPEED_KMH = 60.0;

    @Override
    public Route buildRoute(Location origin, Location destination) {
        double distanceKm = straightLineDistanceKm(origin, destination) * ROAD_DETOUR_FACTOR;
        int minutes = (int) Math.round(distanceKm / AVERAGE_SPEED_KMH * 60);
        return new Route(distanceKm, minutes, "driving");
    }
}
