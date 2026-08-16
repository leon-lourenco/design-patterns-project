package com.designpatterns.structural.adapter.applied;

public interface AccountLookupPort {

    AccountSnapshot findByAccountNumber(String accountNumber);
}
