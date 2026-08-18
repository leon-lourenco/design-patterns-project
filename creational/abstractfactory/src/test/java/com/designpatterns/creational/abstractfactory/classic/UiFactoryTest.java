package com.designpatterns.creational.abstractfactory.classic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UiFactoryTest {

    @Test
    void theWindowsFactoryProducesOnlyWindowsComponents() {
        UiFactory factory = new WinUiFactory();

        assertThat(factory.createButton().render()).isEqualTo("[Windows Button]");
        assertThat(factory.createCheckbox().render()).isEqualTo("[Windows Checkbox]");
    }

    @Test
    void theMacFactoryProducesOnlyMacComponents() {
        UiFactory factory = new MacUiFactory();

        assertThat(factory.createButton().render()).isEqualTo("(Mac Button)");
        assertThat(factory.createCheckbox().render()).isEqualTo("(Mac Checkbox)");
    }
}
