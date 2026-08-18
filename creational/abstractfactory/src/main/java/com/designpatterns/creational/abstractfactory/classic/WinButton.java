package com.designpatterns.creational.abstractfactory.classic;

public class WinButton implements Button {

    @Override
    public String render() {
        return "[Windows Button]";
    }
}
