package com.designpatterns.structural.adapter.applied;

public class AccountLookupException extends RuntimeException {

    public AccountLookupException(String message, Throwable cause) {
        super(message, cause);
    }
}
