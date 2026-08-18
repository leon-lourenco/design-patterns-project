package com.designpatterns.structural.proxy.classic;

/**
 * Implements the same {@link Image} interface as the thing it stands in for, so callers can't
 * tell the difference - until the first {@code display()} call, no expensive load has happened
 * at all.
 */
public class ImageProxy implements Image {

    private final String filename;
    private RealImage realImage;

    public ImageProxy(String filename) {
        this.filename = filename;
    }

    @Override
    public String display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        return realImage.display();
    }

    public boolean isLoaded() {
        return realImage != null;
    }
}
