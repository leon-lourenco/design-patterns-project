package com.designpatterns.behavioral.observer.classic;

public class HeatAlertObserver implements WeatherObserver {

    private static final double HEAT_ALERT_THRESHOLD_CELSIUS = 35.0;

    private boolean alertActive;

    @Override
    public void update(double temperatureCelsius, double humidityPercent) {
        this.alertActive = temperatureCelsius >= HEAT_ALERT_THRESHOLD_CELSIUS;
    }

    public boolean isAlertActive() {
        return alertActive;
    }
}
