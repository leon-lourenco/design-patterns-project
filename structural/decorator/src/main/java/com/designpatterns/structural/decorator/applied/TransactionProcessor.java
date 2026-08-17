package com.designpatterns.structural.decorator.applied;

public interface TransactionProcessor {

    ProcessingResult process(Transaction transaction);
}
