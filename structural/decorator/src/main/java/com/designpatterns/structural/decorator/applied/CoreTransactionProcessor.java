package com.designpatterns.structural.decorator.applied;

import java.util.List;

public class CoreTransactionProcessor implements TransactionProcessor {

    @Override
    public ProcessingResult process(Transaction transaction) {
        return new ProcessingResult(transaction.id(), true, List.of("core: transaction accepted"));
    }
}
