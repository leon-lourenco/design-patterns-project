package com.designpatterns.structural.proxy.applied;

import java.util.HashMap;
import java.util.Map;

/**
 * Implements the same {@link CreditScoreBureau} contract as the real bureau client, so callers
 * (a loan-approval flow, say) never need to know caching exists. Every request still goes
 * through the proxy - it just doesn't always turn into a real external call.
 */
public class CachingCreditScoreProxy implements CreditScoreBureau {

    private final CreditScoreBureau realBureau;
    private final Map<String, Integer> cache = new HashMap<>();

    public CachingCreditScoreProxy(CreditScoreBureau realBureau) {
        this.realBureau = realBureau;
    }

    @Override
    public int lookupScore(String taxId) {
        return cache.computeIfAbsent(taxId, realBureau::lookupScore);
    }
}
