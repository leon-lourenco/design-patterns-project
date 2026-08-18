package com.designpatterns.creational.abstractfactory.classic;

public class WinUiFactory implements UiFactory {

    @Override
    public Button createButton() {
        return new WinButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WinCheckbox();
    }
}
