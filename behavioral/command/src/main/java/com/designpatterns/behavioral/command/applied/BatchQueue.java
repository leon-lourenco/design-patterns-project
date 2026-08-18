package com.designpatterns.behavioral.command.applied;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Each unit of work is queued as an object instead of executed immediately, which is what
 * makes replay possible: a failed command is the exact same object, re-queued, not a
 * re-derived request. Without Command, "retry this specific unit of work" would need to
 * reconstruct the original request from scratch every time.
 */
public class BatchQueue {

    private static final int MAX_ATTEMPTS = 3;

    private final Deque<RecordProcessingCommand> pending = new ArrayDeque<>();
    private final Map<String, Integer> attempts = new HashMap<>();
    private final List<String> succeeded = new ArrayList<>();
    private final List<String> failed = new ArrayList<>();

    public void submit(RecordProcessingCommand command) {
        pending.add(command);
    }

    public void runAll() {
        while (!pending.isEmpty()) {
            RecordProcessingCommand command = pending.poll();
            int attempt = attempts.merge(command.recordId(), 1, Integer::sum);
            try {
                command.execute();
                succeeded.add(command.recordId());
            } catch (RuntimeException e) {
                if (attempt < MAX_ATTEMPTS) {
                    pending.add(command);
                } else {
                    failed.add(command.recordId());
                }
            }
        }
    }

    public List<String> succeeded() {
        return List.copyOf(succeeded);
    }

    public List<String> failed() {
        return List.copyOf(failed);
    }
}
