package com.designpatterns.structural.decorator.classic;

public class Milk extends CondimentDecorator {

    public Milk(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String description() {
        return beverage.description() + " + Milk";
    }

    @Override
    public long costCents() {
        return beverage.costCents() + 50L;
    }
}
