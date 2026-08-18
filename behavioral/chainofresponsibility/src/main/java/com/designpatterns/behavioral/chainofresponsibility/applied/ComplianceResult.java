package com.designpatterns.behavioral.chainofresponsibility.applied;

public record ComplianceResult(boolean approved, String reason) {

    public static ComplianceResult pass() {
        return new ComplianceResult(true, null);
    }

    public static ComplianceResult rejected(String reason) {
        return new ComplianceResult(false, reason);
    }
}
