package com.designpatterns.structural.adapter.classic;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Adapts the pre-Java-2 {@link Enumeration} contract to the modern {@link Iterator}
 * contract, so legacy APIs that only expose an Enumeration can be used anywhere an
 * Iterator is expected (for-each, streams, etc.) without touching the legacy code.
 */
public final class EnumerationIteratorAdapter<T> implements Iterator<T> {

    private final Enumeration<T> adaptee;

    public EnumerationIteratorAdapter(Enumeration<T> adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public boolean hasNext() {
        return adaptee.hasMoreElements();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return adaptee.nextElement();
    }
}
