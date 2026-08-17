package com.designpatterns.structural.decorator.classic;

public abstract class CondimentDecorator implements Beverage {

    protected final Beverage beverage;

    protected CondimentDecorator(Beverage beverage) {
        this.beverage = beverage;
    }
}
