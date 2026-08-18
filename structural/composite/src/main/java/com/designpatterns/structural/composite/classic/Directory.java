package com.designpatterns.structural.composite.classic;

import java.util.ArrayList;
import java.util.List;

/**
 * A Directory's sizeBytes() sums its children's sizeBytes() - and since a child can itself be
 * a Directory, that sum recurses naturally through however many levels of nesting exist. The
 * caller never needs to know or check whether a given component is a file or a directory.
 */
public class Directory implements FileSystemComponent {

    private final String name;
    private final List<FileSystemComponent> children = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public long sizeBytes() {
        return children.stream().mapToLong(FileSystemComponent::sizeBytes).sum();
    }
}
