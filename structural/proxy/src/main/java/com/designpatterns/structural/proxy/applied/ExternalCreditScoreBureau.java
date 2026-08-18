package com.designpatterns.structural.proxy.applied;

/**
 * Stands in for a real external credit-score bureau call: slow and, in production, billed per
 * request. {@code callCount} exists only so tests can prove the proxy actually saved calls -
 * a real bureau client wouldn't expose this.
 */
public class ExternalCreditScoreBureau implements CreditScoreBureau {

    private int callCount = 0;

    @Override
    public int lookupScore(String taxId) {
        callCount++;
        return Math.floorMod(taxId.hashCode(), 300) + 500;
    }

    public int callCount() {
        return callCount;
    }
}
