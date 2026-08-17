package com.designpatterns.creational.builder.applied;

import java.util.Objects;

/**
 * A vehicle-financing proposal, the kind assembled at a bank's point of sale: a required
 * applicant and vehicle price, plus several independent optional add-ons (installment count,
 * insurance, a trade-in as collateral, a promotional rate) that don't all apply to every deal.
 */
public final class AutoLoanProposal {

    private static final int DEFAULT_INSTALLMENTS = 48;

    private final String applicantId;
    private final long vehiclePriceCents;
    private final int installments;
    private final boolean insuranceIncluded;
    private final String collateralVehiclePlate;
    private final boolean promotionalRate;

    private AutoLoanProposal(Builder builder) {
        this.applicantId = builder.applicantId;
        this.vehiclePriceCents = builder.vehiclePriceCents;
        this.installments = builder.installments;
        this.insuranceIncluded = builder.insuranceIncluded;
        this.collateralVehiclePlate = builder.collateralVehiclePlate;
        this.promotionalRate = builder.promotionalRate;
    }

    public String applicantId() {
        return applicantId;
    }

    public long vehiclePriceCents() {
        return vehiclePriceCents;
    }

    public int installments() {
        return installments;
    }

    public boolean insuranceIncluded() {
        return insuranceIncluded;
    }

    public String collateralVehiclePlate() {
        return collateralVehiclePlate;
    }

    public boolean hasCollateral() {
        return collateralVehiclePlate != null;
    }

    public boolean promotionalRate() {
        return promotionalRate;
    }

    public static Builder builder(String applicantId, long vehiclePriceCents) {
        return new Builder(applicantId, vehiclePriceCents);
    }

    public static final class Builder {

        private final String applicantId;
        private final long vehiclePriceCents;
        private int installments = DEFAULT_INSTALLMENTS;
        private boolean insuranceIncluded = false;
        private String collateralVehiclePlate;
        private boolean promotionalRate = false;

        private Builder(String applicantId, long vehiclePriceCents) {
            if (vehiclePriceCents <= 0) {
                throw new IllegalArgumentException("vehiclePriceCents must be positive");
            }
            this.applicantId = Objects.requireNonNull(applicantId, "applicantId must not be null");
            this.vehiclePriceCents = vehiclePriceCents;
        }

        public Builder installments(int installments) {
            if (installments <= 0) {
                throw new IllegalArgumentException("installments must be positive");
            }
            this.installments = installments;
            return this;
        }

        public Builder withInsurance() {
            this.insuranceIncluded = true;
            return this;
        }

        public Builder withCollateral(String vehiclePlate) {
            this.collateralVehiclePlate = Objects.requireNonNull(vehiclePlate, "vehiclePlate must not be null");
            return this;
        }

        public Builder promotionalRate() {
            this.promotionalRate = true;
            return this;
        }

        public AutoLoanProposal build() {
            return new AutoLoanProposal(this);
        }
    }
}
