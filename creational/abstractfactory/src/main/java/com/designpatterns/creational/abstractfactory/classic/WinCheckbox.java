package com.designpatterns.creational.abstractfactory.classic;

public class WinCheckbox implements Checkbox {

    @Override
    public String render() {
        return "[Windows Checkbox]";
    }
}
