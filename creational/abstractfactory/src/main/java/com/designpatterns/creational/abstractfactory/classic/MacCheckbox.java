package com.designpatterns.creational.abstractfactory.classic;

public class MacCheckbox implements Checkbox {

    @Override
    public String render() {
        return "(Mac Checkbox)";
    }
}
