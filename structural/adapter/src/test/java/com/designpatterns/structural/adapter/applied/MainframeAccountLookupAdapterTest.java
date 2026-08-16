package com.designpatterns.structural.adapter.applied;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MainframeAccountLookupAdapterTest {

    private static final String ACCOUNT_NUMBER = "1234567";
    private static final String PADDED_ACCOUNT_NUMBER = "0001234567";

    @Test
    void parsesTheLegacyFixedWidthRecordIntoAModernSnapshot() {
        String record = PADDED_ACCOUNT_NUMBER + leftJustify("JOAO DA SILVA", 25) + "0000015000" + "A";
        MainframeAccountGateway gateway = new MainframeAccountGateway(Map.of(PADDED_ACCOUNT_NUMBER, record));
        MainframeAccountLookupAdapter adapter = new MainframeAccountLookupAdapter(gateway);

        AccountSnapshot snapshot = adapter.findByAccountNumber(ACCOUNT_NUMBER);

        assertThat(snapshot.accountNumber()).isEqualTo(PADDED_ACCOUNT_NUMBER);
        assertThat(snapshot.holderName()).isEqualTo("JOAO DA SILVA");
        assertThat(snapshot.balanceCents()).isEqualTo(15_000L);
        assertThat(snapshot.active()).isTrue();
    }

    @Test
    void returnsAnInactiveZeroBalanceSnapshotWhenTheAccountIsUnknown() {
        MainframeAccountGateway gateway = new MainframeAccountGateway(Map.of());
        MainframeAccountLookupAdapter adapter = new MainframeAccountLookupAdapter(gateway);

        AccountSnapshot snapshot = adapter.findByAccountNumber(ACCOUNT_NUMBER);

        assertThat(snapshot.balanceCents()).isZero();
        assertThat(snapshot.active()).isFalse();
    }

    @Test
    void translatesTheLegacyCheckedExceptionIntoAnUncheckedLookupException() {
        MainframeAccountGateway gateway = new MainframeAccountGateway(Map.of());
        MainframeAccountLookupAdapter adapter = new MainframeAccountLookupAdapter(gateway);

        assertThatThrownBy(() -> adapter.findByAccountNumber("9999999999"))
                .isInstanceOf(AccountLookupException.class)
                .hasCauseInstanceOf(MainframeUnavailableException.class);
    }

    private static String leftJustify(String value, int width) {
        return String.format("%-" + width + "s", value);
    }
}
