package com.designpatterns.behavioral.command.applied;

public class RecordProcessingCommand {

    private final String recordId;
    private final RecordProcessor processor;

    public RecordProcessingCommand(String recordId, RecordProcessor processor) {
        this.recordId = recordId;
        this.processor = processor;
    }

    public String recordId() {
        return recordId;
    }

    public void execute() {
        processor.process(recordId);
    }
}
