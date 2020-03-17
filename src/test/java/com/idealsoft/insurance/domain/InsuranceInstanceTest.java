package com.idealsoft.insurance.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.idealsoft.insurance.web.rest.TestUtil;

public class InsuranceInstanceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceInstance.class);
        InsuranceInstance insuranceInstance1 = new InsuranceInstance();
        insuranceInstance1.setId(1L);
        InsuranceInstance insuranceInstance2 = new InsuranceInstance();
        insuranceInstance2.setId(insuranceInstance1.getId());
        assertThat(insuranceInstance1).isEqualTo(insuranceInstance2);
        insuranceInstance2.setId(2L);
        assertThat(insuranceInstance1).isNotEqualTo(insuranceInstance2);
        insuranceInstance1.setId(null);
        assertThat(insuranceInstance1).isNotEqualTo(insuranceInstance2);
    }
}
