package com.designpatterns.behavioral.templatemethod.applied;

/**
 * Legacy source: comma-separated "name,amountCents" records - a different legacy export format
 * from the same era, going through the exact same read → validate → transform → write skeleton.
 */
public class CsvLegacyMigrationPipeline extends LegacyMigrationPipeline {

    public CsvLegacyMigrationPipeline(MigrationSink sink) {
        super(sink);
    }

    @Override
    protected String read(String rawRecord) {
        return rawRecord;
    }

    @Override
    protected void validate(String parsed) {
        if (!parsed.contains(",")) {
            throw new IllegalStateException("malformed CSV record: " + parsed);
        }
    }

    @Override
    protected String transform(String parsed) {
        String[] parts = parsed.split(",", -1);
        return "{\"name\":\"" + parts[0].trim() + "\",\"amountCents\":" + parts[1].trim() + "}";
    }
}
