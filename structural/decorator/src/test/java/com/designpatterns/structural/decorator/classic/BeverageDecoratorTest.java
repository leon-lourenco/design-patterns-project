package com.designpatterns.structural.decorator.classic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BeverageDecoratorTest {

    @Test
    void aPlainBeverageHasNoCondiments() {
        Beverage order = new Espresso();

        assertThat(order.description()).isEqualTo("Espresso");
        assertThat(order.costCents()).isEqualTo(250L);
    }

    @Test
    void stacksDescriptionAndCostForEachCondimentInWrappingOrder() {
        Beverage order = new Sugar(new Milk(new Espresso()));

        assertThat(order.description()).isEqualTo("Espresso + Milk + Sugar");
        assertThat(order.costCents()).isEqualTo(320L);
    }

    @Test
    void theSameCondimentCanBeAppliedMoreThanOnce() {
        Beverage order = new Sugar(new Sugar(new Espresso()));

        assertThat(order.description()).isEqualTo("Espresso + Sugar + Sugar");
        assertThat(order.costCents()).isEqualTo(290L);
    }
}
