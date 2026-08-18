package com.designpatterns.behavioral.templatemethod.applied;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LegacyMigrationPipelineTest {

    @Test
    void migratesAFixedWidthCobolRecordIntoTheModernFormat() {
        InMemoryMigrationSink sink = new InMemoryMigrationSink();
        LegacyMigrationPipeline pipeline = new CobolFixedWidthMigrationPipeline(sink);
        String rawRecord = String.format("%-20s%-10s", "JOAO DA SILVA", "15000");

        pipeline.migrate(rawRecord);

        assertThat(sink.records()).containsExactly("{\"name\":\"JOAO DA SILVA\",\"amountCents\":15000}");
    }

    @Test
    void migratesACsvRecordIntoTheSameModernFormat() {
        InMemoryMigrationSink sink = new InMemoryMigrationSink();
        LegacyMigrationPipeline pipeline = new CsvLegacyMigrationPipeline(sink);

        pipeline.migrate("Maria Souza, 8000");

        assertThat(sink.records()).containsExactly("{\"name\":\"Maria Souza\",\"amountCents\":8000}");
    }

    @Test
    void aValidationFailureStopsThePipelineBeforeAnythingReachesTheSink() {
        InMemoryMigrationSink sink = new InMemoryMigrationSink();
        LegacyMigrationPipeline pipeline = new CsvLegacyMigrationPipeline(sink);

        assertThatThrownBy(() -> pipeline.migrate("no-comma-here"))
                .isInstanceOf(IllegalStateException.class);
        assertThat(sink.records()).isEmpty();
    }

    @Test
    void theCobolPipelineRejectsARecordWithNoName() {
        InMemoryMigrationSink sink = new InMemoryMigrationSink();
        LegacyMigrationPipeline pipeline = new CobolFixedWidthMigrationPipeline(sink);
        String rawRecord = String.format("%-20s%-10s", "", "15000");

        assertThatThrownBy(() -> pipeline.migrate(rawRecord))
                .isInstanceOf(IllegalStateException.class);
        assertThat(sink.records()).isEmpty();
    }
}
