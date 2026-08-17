package com.designpatterns.structural.decorator.classic;

public class Sugar extends CondimentDecorator {

    public Sugar(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String description() {
        return beverage.description() + " + Sugar";
    }

    @Override
    public long costCents() {
        return beverage.costCents() + 20L;
    }
}
