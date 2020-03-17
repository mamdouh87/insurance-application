package com.idealsoft.insurance.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.idealsoft.insurance.web.rest.TestUtil;

public class InsuranceObjectDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceObjectDTO.class);
        InsuranceObjectDTO insuranceObjectDTO1 = new InsuranceObjectDTO();
        insuranceObjectDTO1.setId(1L);
        InsuranceObjectDTO insuranceObjectDTO2 = new InsuranceObjectDTO();
        assertThat(insuranceObjectDTO1).isNotEqualTo(insuranceObjectDTO2);
        insuranceObjectDTO2.setId(insuranceObjectDTO1.getId());
        assertThat(insuranceObjectDTO1).isEqualTo(insuranceObjectDTO2);
        insuranceObjectDTO2.setId(2L);
        assertThat(insuranceObjectDTO1).isNotEqualTo(insuranceObjectDTO2);
        insuranceObjectDTO1.setId(null);
        assertThat(insuranceObjectDTO1).isNotEqualTo(insuranceObjectDTO2);
    }
}
