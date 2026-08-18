package com.designpatterns.structural.facade.applied;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SalaryPortabilityFacadeTest {

    private final AccountVerificationService verification = new AccountVerificationService(Set.of("acc-1"));
    private final BacenLookupService bacenLookup = new BacenLookupService(Map.of("111.111.111-11", "Bank A"));
    private final SalaryPortabilityFacade facade =
            new SalaryPortabilityFacade(verification, bacenLookup, new NotificationService());

    @Test
    void schedulesPortabilityWhenEverySubsystemAgrees() {
        PortabilityResult result = facade.requestPortability("acc-1", "111.111.111-11");

        assertThat(result.scheduled()).isTrue();
        assertThat(result.fromBank()).isEqualTo("Bank A");
        assertThat(result.message()).isEqualTo("Notice sent to account acc-1: portability from Bank A scheduled");
    }

    @Test
    void rejectsAnIneligibleAccountWithoutEverCallingBacen() {
        PortabilityResult result = facade.requestPortability("acc-unknown", "111.111.111-11");

        assertThat(result.scheduled()).isFalse();
        assertThat(result.message()).isEqualTo("account not eligible for portability");
    }

    @Test
    void rejectsWhenBacenHasNoPayrollRegistrationForTheTaxId() {
        PortabilityResult result = facade.requestPortability("acc-1", "999.999.999-99");

        assertThat(result.scheduled()).isFalse();
        assertThat(result.message()).isEqualTo("no payroll registration found at BACEN");
    }
}
