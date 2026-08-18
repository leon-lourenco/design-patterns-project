package com.designpatterns.creational.abstractfactory.classic;

public class MacButton implements Button {

    @Override
    public String render() {
        return "(Mac Button)";
    }
}
