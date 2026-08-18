package com.designpatterns.structural.proxy.classic;

public class RealImage implements Image {

    private final String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    private void loadFromDisk() {
        // Stands in for a genuinely expensive load - reading a large file, decoding it, etc.
    }

    @Override
    public String display() {
        return "Displaying " + filename;
    }
}
