package com.idealsoft.insurance.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.idealsoft.insurance.web.rest.TestUtil;

public class InsuranceObjectTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceObjectType.class);
        InsuranceObjectType insuranceObjectType1 = new InsuranceObjectType();
        insuranceObjectType1.setId(1L);
        InsuranceObjectType insuranceObjectType2 = new InsuranceObjectType();
        insuranceObjectType2.setId(insuranceObjectType1.getId());
        assertThat(insuranceObjectType1).isEqualTo(insuranceObjectType2);
        insuranceObjectType2.setId(2L);
        assertThat(insuranceObjectType1).isNotEqualTo(insuranceObjectType2);
        insuranceObjectType1.setId(null);
        assertThat(insuranceObjectType1).isNotEqualTo(insuranceObjectType2);
    }
}
