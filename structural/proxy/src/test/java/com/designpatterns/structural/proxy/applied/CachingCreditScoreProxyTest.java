package com.designpatterns.structural.proxy.applied;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CachingCreditScoreProxyTest {

    @Test
    void repeatedLookupsForTheSameTaxIdHitTheRealBureauOnlyOnce() {
        ExternalCreditScoreBureau realBureau = new ExternalCreditScoreBureau();
        CachingCreditScoreProxy proxy = new CachingCreditScoreProxy(realBureau);

        int first = proxy.lookupScore("111.111.111-11");
        int second = proxy.lookupScore("111.111.111-11");
        int third = proxy.lookupScore("111.111.111-11");

        assertThat(first).isEqualTo(second).isEqualTo(third);
        assertThat(realBureau.callCount()).isEqualTo(1);
    }

    @Test
    void differentTaxIdsEachTriggerTheirOwnRealBureauCall() {
        ExternalCreditScoreBureau realBureau = new ExternalCreditScoreBureau();
        CachingCreditScoreProxy proxy = new CachingCreditScoreProxy(realBureau);

        proxy.lookupScore("111.111.111-11");
        proxy.lookupScore("222.222.222-22");

        assertThat(realBureau.callCount()).isEqualTo(2);
    }

    @Test
    void theProxyReturnsExactlyWhatTheRealBureauWouldHaveReturned() {
        ExternalCreditScoreBureau realBureau = new ExternalCreditScoreBureau();
        CachingCreditScoreProxy proxy = new CachingCreditScoreProxy(realBureau);
        String taxId = "333.333.333-33";

        int viaProxy = proxy.lookupScore(taxId);
        int direct = realBureau.lookupScore(taxId);

        assertThat(viaProxy).isEqualTo(direct);
    }
}
