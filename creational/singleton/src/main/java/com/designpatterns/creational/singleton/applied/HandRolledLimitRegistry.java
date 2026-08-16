package com.designpatterns.creational.singleton.applied;

/**
 * Central PIX regulatory limit table (BACEN), read by every concurrent transaction
 * validator. One instance per JVM: reloading these limits on every validation call
 * would be wasteful, and concurrent validators must all observe the same values.
 */
public final class HandRolledLimitRegistry implements LimitRegistry {

    private static volatile HandRolledLimitRegistry instance;

    private final long dailyLimitCents;
    private final long nightlyLimitCents;

    private HandRolledLimitRegistry() {
        // In production this would be loaded from the BACEN limits feed/config.
        this.dailyLimitCents = 100_000_00L;
        this.nightlyLimitCents = 1_000_00L;
    }

    public static HandRolledLimitRegistry getInstance() {
        HandRolledLimitRegistry result = instance;
        if (result == null) {
            synchronized (HandRolledLimitRegistry.class) {
                result = instance;
                if (result == null) {
                    instance = result = new HandRolledLimitRegistry();
                }
            }
        }
        return result;
    }

    @Override
    public long dailyLimitCents() {
        return dailyLimitCents;
    }

    @Override
    public long nightlyLimitCents() {
        return nightlyLimitCents;
    }
}
