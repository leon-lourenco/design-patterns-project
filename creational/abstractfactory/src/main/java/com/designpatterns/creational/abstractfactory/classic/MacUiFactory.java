package com.designpatterns.creational.abstractfactory.classic;

public class MacUiFactory implements UiFactory {

    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}
