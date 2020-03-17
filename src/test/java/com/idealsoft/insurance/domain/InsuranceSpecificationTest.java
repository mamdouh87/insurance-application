package com.idealsoft.insurance.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.idealsoft.insurance.web.rest.TestUtil;

public class InsuranceSpecificationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceSpecification.class);
        InsuranceSpecification insuranceSpecification1 = new InsuranceSpecification();
        insuranceSpecification1.setId(1L);
        InsuranceSpecification insuranceSpecification2 = new InsuranceSpecification();
        insuranceSpecification2.setId(insuranceSpecification1.getId());
        assertThat(insuranceSpecification1).isEqualTo(insuranceSpecification2);
        insuranceSpecification2.setId(2L);
        assertThat(insuranceSpecification1).isNotEqualTo(insuranceSpecification2);
        insuranceSpecification1.setId(null);
        assertThat(insuranceSpecification1).isNotEqualTo(insuranceSpecification2);
    }
}
