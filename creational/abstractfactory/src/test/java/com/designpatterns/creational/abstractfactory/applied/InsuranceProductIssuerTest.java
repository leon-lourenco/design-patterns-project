package com.designpatterns.creational.abstractfactory.applied;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InsuranceProductIssuerTest {

    @Test
    void theDomesticFactoryIssuesADomesticPolicyAtTheDomesticRate() {
        InsuranceProductIssuer issuer = new InsuranceProductIssuer(new DomesticInsuranceProductFactory());

        String result = issuer.issuePolicy("Maria Silva", 100_000_00L);

        assertThat(result).isEqualTo("APOLICE NACIONAL - Segurado: Maria Silva | Premium: 200000 cents");
    }

    @Test
    void theInternationalFactoryIssuesAnInternationalPolicyAtTheInternationalRate() {
        InsuranceProductIssuer issuer = new InsuranceProductIssuer(new InternationalInsuranceProductFactory());

        String result = issuer.issuePolicy("John Smith", 100_000_00L);

        assertThat(result).isEqualTo("INTERNATIONAL POLICY - Insured: John Smith | Premium: 350000 cents");
    }
}
