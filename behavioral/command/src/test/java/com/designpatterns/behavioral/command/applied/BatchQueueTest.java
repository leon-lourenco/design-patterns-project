package com.designpatterns.behavioral.command.applied;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BatchQueueTest {

    @Test
    void everyRecordThatProcessesCleanlySucceeds() {
        BatchQueue queue = new BatchQueue();
        RecordProcessor alwaysSucceeds = recordId -> { };
        queue.submit(new RecordProcessingCommand("rec-1", alwaysSucceeds));
        queue.submit(new RecordProcessingCommand("rec-2", alwaysSucceeds));

        queue.runAll();

        assertThat(queue.succeeded()).containsExactlyInAnyOrder("rec-1", "rec-2");
        assertThat(queue.failed()).isEmpty();
    }

    @Test
    void aRecordThatFailsTwiceThenSucceedsIsReplayedUntilItWorks() {
        BatchQueue queue = new BatchQueue();
        Map<String, Integer> attemptsSoFar = new HashMap<>();
        RecordProcessor failsTwice = recordId -> {
            int attempt = attemptsSoFar.merge(recordId, 1, Integer::sum);
            if (attempt < 3) {
                throw new RuntimeException("transient failure on attempt " + attempt);
            }
        };
        queue.submit(new RecordProcessingCommand("rec-flaky", failsTwice));

        queue.runAll();

        assertThat(queue.succeeded()).containsExactly("rec-flaky");
        assertThat(queue.failed()).isEmpty();
        assertThat(attemptsSoFar.get("rec-flaky")).isEqualTo(3);
    }

    @Test
    void aRecordThatNeverSucceedsEndsUpInTheFailedListAfterExhaustingRetries() {
        BatchQueue queue = new BatchQueue();
        RecordProcessor alwaysFails = recordId -> {
            throw new RuntimeException("permanent failure");
        };
        queue.submit(new RecordProcessingCommand("rec-doomed", alwaysFails));

        queue.runAll();

        assertThat(queue.failed()).containsExactly("rec-doomed");
        assertThat(queue.succeeded()).isEmpty();
    }
}
