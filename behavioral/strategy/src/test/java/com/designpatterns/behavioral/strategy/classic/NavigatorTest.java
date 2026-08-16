package com.designpatterns.behavioral.strategy.classic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NavigatorTest {

    private final Location origin = new Location(0, 0);
    private final Location destination = new Location(3, 4); // straight-line distance = 5km

    @Test
    void drivingRouteAppliesTheRoadDetourFactor() {
        Navigator navigator = new Navigator(new DrivingRouteStrategy());

        Route route = navigator.route(origin, destination);

        assertThat(route.distanceKm()).isEqualTo(5 * 1.3);
        assertThat(route.description()).isEqualTo("driving");
    }

    @Test
    void swappingTheStrategyChangesTheRouteForTheSameTrip() {
        Navigator navigator = new Navigator(new WalkingRouteStrategy());
        Route walking = navigator.route(origin, destination);

        navigator.setStrategy(new PublicTransportRouteStrategy());
        Route transit = navigator.route(origin, destination);

        assertThat(walking.distanceKm()).isNotEqualTo(transit.distanceKm());
        assertThat(walking.estimatedMinutes()).isNotEqualTo(transit.estimatedMinutes());
    }
}
