package com.designpatterns.structural.decorator.classic;

public class Espresso implements Beverage {

    @Override
    public String description() {
        return "Espresso";
    }

    @Override
    public long costCents() {
        return 250L;
    }
}
