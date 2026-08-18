package com.designpatterns.structural.facade.applied;

/**
 * The caller asks for one thing ("port this account's salary") instead of knowing that
 * eligibility has to be checked, the payer's current payroll bank has to be looked up at
 * BACEN, and a notice has to go out - in that order, short-circuiting the moment any step
 * fails. None of the three subsystem services know about each other; only the facade does.
 */
public class SalaryPortabilityFacade {

    private final AccountVerificationService accountVerification;
    private final BacenLookupService bacenLookup;
    private final NotificationService notification;

    public SalaryPortabilityFacade(
            AccountVerificationService accountVerification,
            BacenLookupService bacenLookup,
            NotificationService notification) {
        this.accountVerification = accountVerification;
        this.bacenLookup = bacenLookup;
        this.notification = notification;
    }

    public PortabilityResult requestPortability(String accountId, String taxId) {
        if (!accountVerification.isAccountEligible(accountId)) {
            return PortabilityResult.rejected("account not eligible for portability");
        }
        String currentBank = bacenLookup.lookupCurrentPayrollBank(taxId);
        if (currentBank == null) {
            return PortabilityResult.rejected("no payroll registration found at BACEN");
        }
        String notice = notification.notifyPortabilityScheduled(accountId, currentBank);
        return PortabilityResult.scheduled(currentBank, notice);
    }
}
