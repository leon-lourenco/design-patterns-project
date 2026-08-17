package com.designpatterns.creational.builder.applied;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AutoLoanProposalTest {

    @Test
    void appliesTheDefaultTermWhenNoAddOnsAreRequested() {
        AutoLoanProposal proposal = AutoLoanProposal.builder("applicant-1", 80_000_00L).build();

        assertThat(proposal.applicantId()).isEqualTo("applicant-1");
        assertThat(proposal.vehiclePriceCents()).isEqualTo(80_000_00L);
        assertThat(proposal.installments()).isEqualTo(48);
        assertThat(proposal.insuranceIncluded()).isFalse();
        assertThat(proposal.hasCollateral()).isFalse();
        assertThat(proposal.promotionalRate()).isFalse();
    }

    @Test
    void combinesOnlyTheAddOnsExplicitlyRequested() {
        AutoLoanProposal proposal = AutoLoanProposal.builder("applicant-2", 120_000_00L)
                .installments(60)
                .withInsurance()
                .withCollateral("ABC1D23")
                .promotionalRate()
                .build();

        assertThat(proposal.installments()).isEqualTo(60);
        assertThat(proposal.insuranceIncluded()).isTrue();
        assertThat(proposal.hasCollateral()).isTrue();
        assertThat(proposal.collateralVehiclePlate()).isEqualTo("ABC1D23");
        assertThat(proposal.promotionalRate()).isTrue();
    }

    @Test
    void rejectsANonPositiveVehiclePrice() {
        assertThatThrownBy(() -> AutoLoanProposal.builder("applicant-3", 0L))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rejectsANonPositiveInstallmentCount() {
        AutoLoanProposal.Builder builder = AutoLoanProposal.builder("applicant-4", 50_000_00L);

        assertThatThrownBy(() -> builder.installments(0)).isInstanceOf(IllegalArgumentException.class);
    }
}
