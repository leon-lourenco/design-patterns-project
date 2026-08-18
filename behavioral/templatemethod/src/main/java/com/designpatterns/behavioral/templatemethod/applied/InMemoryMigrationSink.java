package com.designpatterns.behavioral.templatemethod.applied;

import java.util.ArrayList;
import java.util.List;

public class InMemoryMigrationSink implements MigrationSink {

    private final List<String> records = new ArrayList<>();

    @Override
    public void write(String modernRecord) {
        records.add(modernRecord);
    }

    public List<String> records() {
        return List.copyOf(records);
    }
}
