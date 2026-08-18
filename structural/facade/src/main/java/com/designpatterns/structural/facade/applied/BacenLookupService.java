package com.designpatterns.structural.facade.applied;

import java.util.Map;

public class BacenLookupService {

    private final Map<String, String> payrollBankByTaxId;

    public BacenLookupService(Map<String, String> payrollBankByTaxId) {
        this.payrollBankByTaxId = Map.copyOf(payrollBankByTaxId);
    }

    public String lookupCurrentPayrollBank(String taxId) {
        return payrollBankByTaxId.get(taxId);
    }
}
