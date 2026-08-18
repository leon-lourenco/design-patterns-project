package com.designpatterns.behavioral.observer.classic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WeatherStationTest {

    @Test
    void everySubscribedObserverReactsIndependentlyToTheSameMeasurement() {
        WeatherStation station = new WeatherStation();
        CurrentConditionsDisplay display = new CurrentConditionsDisplay();
        HeatAlertObserver alert = new HeatAlertObserver();
        station.subscribe(display);
        station.subscribe(alert);

        station.setMeasurements(36.5, 40.0);

        assertThat(display.currentConditions()).isEqualTo("Temp: 36.5C, Humidity: 40.0%");
        assertThat(alert.isAlertActive()).isTrue();
    }

    @Test
    void anUnsubscribedObserverStopsReceivingUpdates() {
        WeatherStation station = new WeatherStation();
        CurrentConditionsDisplay display = new CurrentConditionsDisplay();
        station.subscribe(display);
        station.setMeasurements(20.0, 50.0);

        station.unsubscribe(display);
        station.setMeasurements(30.0, 60.0);

        assertThat(display.currentConditions()).isEqualTo("Temp: 20.0C, Humidity: 50.0%");
    }

    @Test
    void theHeatAlertClearsWhenTheTemperatureDropsBackDown() {
        WeatherStation station = new WeatherStation();
        HeatAlertObserver alert = new HeatAlertObserver();
        station.subscribe(alert);

        station.setMeasurements(40.0, 30.0);
        assertThat(alert.isAlertActive()).isTrue();

        station.setMeasurements(22.0, 30.0);
        assertThat(alert.isAlertActive()).isFalse();
    }
}
