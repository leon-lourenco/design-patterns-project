package com.designpatterns.behavioral.observer.classic;

import java.util.Locale;

public class CurrentConditionsDisplay implements WeatherObserver {

    private double lastTemperatureCelsius;
    private double lastHumidityPercent;

    @Override
    public void update(double temperatureCelsius, double humidityPercent) {
        this.lastTemperatureCelsius = temperatureCelsius;
        this.lastHumidityPercent = humidityPercent;
    }

    public String currentConditions() {
        // Locale.ROOT on purpose: this is a display string used in tests/output, and must not
        // vary with the JVM's default locale (e.g. pt-BR formats decimals with a comma).
        return String.format(Locale.ROOT, "Temp: %.1fC, Humidity: %.1f%%", lastTemperatureCelsius, lastHumidityPercent);
    }
}
