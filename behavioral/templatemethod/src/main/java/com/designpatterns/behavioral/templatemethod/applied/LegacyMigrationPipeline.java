package com.designpatterns.behavioral.templatemethod.applied;

/**
 * The skeleton (read → validate → transform → write) is fixed and shared by every legacy
 * source format; only how each step is done varies. A validation failure stops the pipeline
 * before anything reaches the sink - the template method itself enforces that ordering, no
 * subclass can accidentally write before validating.
 */
public abstract class LegacyMigrationPipeline {

    private final MigrationSink sink;

    protected LegacyMigrationPipeline(MigrationSink sink) {
        this.sink = sink;
    }

    public final void migrate(String rawRecord) {
        String parsed = read(rawRecord);
        validate(parsed);
        String transformed = transform(parsed);
        sink.write(transformed);
    }

    protected abstract String read(String rawRecord);

    protected abstract void validate(String parsed);

    protected abstract String transform(String parsed);
}
