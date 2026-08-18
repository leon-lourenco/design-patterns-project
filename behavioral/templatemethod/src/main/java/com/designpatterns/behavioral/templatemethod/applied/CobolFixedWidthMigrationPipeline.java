package com.designpatterns.behavioral.templatemethod.applied;

/**
 * Legacy source: fixed-width COBOL-style records, NAME[20] + AMOUNT_CENTS[10].
 */
public class CobolFixedWidthMigrationPipeline extends LegacyMigrationPipeline {

    private static final int NAME_WIDTH = 20;
    private static final int AMOUNT_WIDTH = 10;

    public CobolFixedWidthMigrationPipeline(MigrationSink sink) {
        super(sink);
    }

    @Override
    protected String read(String rawRecord) {
        String name = rawRecord.substring(0, NAME_WIDTH).trim();
        String amountCents = rawRecord.substring(NAME_WIDTH, NAME_WIDTH + AMOUNT_WIDTH).trim();
        return name + "|" + amountCents;
    }

    @Override
    protected void validate(String parsed) {
        String[] parts = parsed.split("\\|", -1);
        if (parts[0].isBlank()) {
            throw new IllegalStateException("missing name in COBOL record");
        }
    }

    @Override
    protected String transform(String parsed) {
        String[] parts = parsed.split("\\|", -1);
        return "{\"name\":\"" + parts[0] + "\",\"amountCents\":" + Long.parseLong(parts[1]) + "}";
    }
}
