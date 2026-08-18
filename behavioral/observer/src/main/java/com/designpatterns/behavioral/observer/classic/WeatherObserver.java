package com.designpatterns.behavioral.observer.classic;

public interface WeatherObserver {

    void update(double temperatureCelsius, double humidityPercent);
}
