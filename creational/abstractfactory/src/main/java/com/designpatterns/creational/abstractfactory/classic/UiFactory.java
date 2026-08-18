package com.designpatterns.creational.abstractfactory.classic;

/**
 * Every method here returns a piece of the same "look and feel" family. There is no way to
 * call this interface and get a WinButton back alongside a MacCheckbox - the factory is what
 * guarantees the family stays consistent, not caller discipline.
 */
public interface UiFactory {

    Button createButton();

    Checkbox createCheckbox();
}
