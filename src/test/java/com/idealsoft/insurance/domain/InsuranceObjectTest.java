package com.idealsoft.insurance.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.idealsoft.insurance.web.rest.TestUtil;

public class InsuranceObjectTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceObject.class);
        InsuranceObject insuranceObject1 = new InsuranceObject();
        insuranceObject1.setId(1L);
        InsuranceObject insuranceObject2 = new InsuranceObject();
        insuranceObject2.setId(insuranceObject1.getId());
        assertThat(insuranceObject1).isEqualTo(insuranceObject2);
        insuranceObject2.setId(2L);
        assertThat(insuranceObject1).isNotEqualTo(insuranceObject2);
        insuranceObject1.setId(null);
        assertThat(insuranceObject1).isNotEqualTo(insuranceObject2);
    }
}
