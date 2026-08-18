package com.designpatterns.structural.proxy.classic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ImageProxyTest {

    @Test
    void theRealImageIsNotLoadedUntilTheFirstDisplayCall() {
        ImageProxy proxy = new ImageProxy("photo.png");

        assertThat(proxy.isLoaded()).isFalse();

        String result = proxy.display();

        assertThat(proxy.isLoaded()).isTrue();
        assertThat(result).isEqualTo("Displaying photo.png");
    }

    @Test
    void repeatedDisplayCallsReuseTheAlreadyLoadedImage() {
        ImageProxy proxy = new ImageProxy("photo.png");

        proxy.display();
        String secondResult = proxy.display();

        assertThat(secondResult).isEqualTo("Displaying photo.png");
        assertThat(proxy.isLoaded()).isTrue();
    }
}
