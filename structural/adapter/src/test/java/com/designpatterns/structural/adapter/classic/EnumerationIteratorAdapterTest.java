package com.designpatterns.structural.adapter.classic;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnumerationIteratorAdapterTest {

    @Test
    void walksEveryElementOfTheAdaptedEnumeration() {
        Enumeration<String> legacyEnumeration = Collections.enumeration(List.of("PIX", "TED", "BOLETO"));
        Iterator<String> iterator = new EnumerationIteratorAdapter<>(legacyEnumeration);

        assertThat(iterator).toIterable().containsExactly("PIX", "TED", "BOLETO");
    }

    @Test
    void throwsNoSuchElementExceptionOnceExhausted() {
        Enumeration<String> emptyEnumeration = Collections.enumeration(List.of());
        Iterator<String> iterator = new EnumerationIteratorAdapter<>(emptyEnumeration);

        assertThat(iterator.hasNext()).isFalse();
        assertThatThrownBy(iterator::next).isInstanceOf(NoSuchElementException.class);
    }
}
