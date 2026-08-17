package com.designpatterns.structural.decorator.applied;

import java.util.ArrayList;
import java.util.List;

public record ProcessingResult(String transactionId, boolean approved, List<String> auditTrail) {

    public ProcessingResult {
        auditTrail = List.copyOf(auditTrail);
    }

    public ProcessingResult withNote(String note) {
        return withNoteAndApproval(note, approved);
    }

    public ProcessingResult withNoteAndApproval(String note, boolean newApproved) {
        List<String> updated = new ArrayList<>(auditTrail);
        updated.add(note);
        return new ProcessingResult(transactionId, newApproved, updated);
    }
}
