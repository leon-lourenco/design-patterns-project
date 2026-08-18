package com.designpatterns.behavioral.observer.classic;

import java.util.ArrayList;
import java.util.List;

public class WeatherStation {

    private final List<WeatherObserver> observers = new ArrayList<>();
    private double temperatureCelsius;
    private double humidityPercent;

    public void subscribe(WeatherObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(WeatherObserver observer) {
        observers.remove(observer);
    }

    public void setMeasurements(double temperatureCelsius, double humidityPercent) {
        this.temperatureCelsius = temperatureCelsius;
        this.humidityPercent = humidityPercent;
        notifyObservers();
    }

    private void notifyObservers() {
        for (WeatherObserver observer : observers) {
            observer.update(temperatureCelsius, humidityPercent);
        }
    }
}
