package com.designpatterns.creational.builder.classic;

import java.util.Objects;

public final class Computer {

    private final String cpu;
    private final int ramGb;
    private final int storageGb;
    private final boolean hasGraphicsCard;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ramGb = builder.ramGb;
        this.storageGb = builder.storageGb;
        this.hasGraphicsCard = builder.hasGraphicsCard;
    }

    public String cpu() {
        return cpu;
    }

    public int ramGb() {
        return ramGb;
    }

    public int storageGb() {
        return storageGb;
    }

    public boolean hasGraphicsCard() {
        return hasGraphicsCard;
    }

    public static Builder builder(String cpu) {
        return new Builder(cpu);
    }

    public static final class Builder {

        private final String cpu;
        private int ramGb = 8;
        private int storageGb = 256;
        private boolean hasGraphicsCard = false;

        private Builder(String cpu) {
            this.cpu = Objects.requireNonNull(cpu, "cpu must not be null");
        }

        public Builder ramGb(int ramGb) {
            this.ramGb = ramGb;
            return this;
        }

        public Builder storageGb(int storageGb) {
            this.storageGb = storageGb;
            return this;
        }

        public Builder withGraphicsCard() {
            this.hasGraphicsCard = true;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}
