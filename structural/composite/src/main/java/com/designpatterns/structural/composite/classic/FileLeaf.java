package com.designpatterns.structural.composite.classic;

public class FileLeaf implements FileSystemComponent {

    private final String name;
    private final long sizeBytes;

    public FileLeaf(String name, long sizeBytes) {
        this.name = name;
        this.sizeBytes = sizeBytes;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public long sizeBytes() {
        return sizeBytes;
    }
}
