package com.designpatterns.creational.singleton.applied;

/**
 * Same contract as {@link HandRolledLimitRegistry}, but with no singleton machinery
 * at all: a plain class whose single-instance guarantee comes entirely from Spring's
 * default singleton bean scope (see {@link SingletonRegistryConfig}).
 */
public class SpringManagedLimitRegistry implements LimitRegistry {

    private final long dailyLimitCents;
    private final long nightlyLimitCents;

    public SpringManagedLimitRegistry(long dailyLimitCents, long nightlyLimitCents) {
        this.dailyLimitCents = dailyLimitCents;
        this.nightlyLimitCents = nightlyLimitCents;
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
