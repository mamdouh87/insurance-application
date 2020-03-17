package com.idealsoft.insurance.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.idealsoft.insurance.web.rest.TestUtil;

public class InsuranceInstanceDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceInstanceDetails.class);
        InsuranceInstanceDetails insuranceInstanceDetails1 = new InsuranceInstanceDetails();
        insuranceInstanceDetails1.setId(1L);
        InsuranceInstanceDetails insuranceInstanceDetails2 = new InsuranceInstanceDetails();
        insuranceInstanceDetails2.setId(insuranceInstanceDetails1.getId());
        assertThat(insuranceInstanceDetails1).isEqualTo(insuranceInstanceDetails2);
        insuranceInstanceDetails2.setId(2L);
        assertThat(insuranceInstanceDetails1).isNotEqualTo(insuranceInstanceDetails2);
        insuranceInstanceDetails1.setId(null);
        assertThat(insuranceInstanceDetails1).isNotEqualTo(insuranceInstanceDetails2);
    }
}
