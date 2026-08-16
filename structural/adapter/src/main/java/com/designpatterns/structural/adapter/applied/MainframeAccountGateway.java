package com.designpatterns.structural.adapter.applied;

import java.util.Map;

/**
 * Stands in for a real mainframe/COBOL account system: fixed-width positional
 * records (ACCOUNT[10] + NAME[25] + BALANCE_CENTS[10] + STATUS[1]) and a checked
 * exception for failures, with no client-friendly types anywhere.
 */
public class MainframeAccountGateway {

    private static final String UNAVAILABLE_TRIGGER = "9999999999";

    private final Map<String, String> records;

    public MainframeAccountGateway(Map<String, String> records) {
        this.records = records;
    }

    public String queryAccountRecord(String accountNumberPadded) throws MainframeUnavailableException {
        if (UNAVAILABLE_TRIGGER.equals(accountNumberPadded)) {
            throw new MainframeUnavailableException("mainframe session pool exhausted");
        }
        String record = records.get(accountNumberPadded);
        if (record == null) {
            // The real mainframe never returns "not found" — it returns a low-value
            // sentinel record instead. An all-zero, inactive record models that.
            return accountNumberPadded + " ".repeat(25) + "0".repeat(10) + "I";
        }
        return record;
    }
}
