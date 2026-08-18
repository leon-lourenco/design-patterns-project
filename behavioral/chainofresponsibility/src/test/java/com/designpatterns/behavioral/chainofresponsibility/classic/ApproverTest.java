package com.designpatterns.behavioral.chainofresponsibility.classic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApproverTest {

    private final Approver chain = new Supervisor().next(new Manager().next(new Director()));

    @Test
    void aSmallAmountStopsAtTheSupervisor() {
        assertThat(chain.approve(500_00L)).isEqualTo("Supervisor approved 50000 cents");
    }

    @Test
    void aMidSizedAmountEscalatesPastTheSupervisorToTheManager() {
        assertThat(chain.approve(5_000_00L)).isEqualTo("Manager approved 500000 cents");
    }

    @Test
    void aLargeAmountEscalatesAllTheWayToTheDirector() {
        assertThat(chain.approve(50_000_00L)).isEqualTo("Director approved 5000000 cents");
    }

    @Test
    void anAmountBeyondEveryLinksLimitFallsOffTheEndOfTheChain() {
        assertThat(chain.approve(1_000_000_00L)).isEqualTo("No approver available for 100000000 cents");
    }
}
