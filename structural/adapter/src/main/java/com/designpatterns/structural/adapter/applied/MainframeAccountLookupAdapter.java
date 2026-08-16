package com.designpatterns.structural.adapter.applied;

/**
 * Exposes the fixed-width, checked-exception {@link MainframeAccountGateway} behind
 * the modern {@link AccountLookupPort} contract new microservices depend on. New code
 * never learns about record offsets or the legacy exception type.
 */
public class MainframeAccountLookupAdapter implements AccountLookupPort {

    private static final int ACCOUNT_NUMBER_WIDTH = 10;
    private static final int HOLDER_NAME_WIDTH = 25;
    private static final int BALANCE_CENTS_WIDTH = 10;

    private final MainframeAccountGateway gateway;

    public MainframeAccountLookupAdapter(MainframeAccountGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public AccountSnapshot findByAccountNumber(String accountNumber) {
        String padded = zeroPad(accountNumber);
        try {
            return parse(gateway.queryAccountRecord(padded));
        } catch (MainframeUnavailableException e) {
            throw new AccountLookupException("mainframe unavailable for account " + accountNumber, e);
        }
    }

    private AccountSnapshot parse(String record) {
        int nameStart = ACCOUNT_NUMBER_WIDTH;
        int balanceStart = nameStart + HOLDER_NAME_WIDTH;
        int statusIndex = balanceStart + BALANCE_CENTS_WIDTH;

        String accountNumber = record.substring(0, ACCOUNT_NUMBER_WIDTH).trim();
        String holderName = record.substring(nameStart, balanceStart).trim();
        long balanceCents = Long.parseLong(record.substring(balanceStart, statusIndex));
        boolean active = record.charAt(statusIndex) == 'A';

        return new AccountSnapshot(accountNumber, holderName, balanceCents, active);
    }

    private String zeroPad(String accountNumber) {
        return String.format("%" + ACCOUNT_NUMBER_WIDTH + "s", accountNumber).replace(' ', '0');
    }
}
