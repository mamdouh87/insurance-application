package com.idealsoft.insurance.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.idealsoft.insurance.web.rest.TestUtil;

public class InsuranceObjectTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceObjectTypeDTO.class);
        InsuranceObjectTypeDTO insuranceObjectTypeDTO1 = new InsuranceObjectTypeDTO();
        insuranceObjectTypeDTO1.setId(1L);
        InsuranceObjectTypeDTO insuranceObjectTypeDTO2 = new InsuranceObjectTypeDTO();
        assertThat(insuranceObjectTypeDTO1).isNotEqualTo(insuranceObjectTypeDTO2);
        insuranceObjectTypeDTO2.setId(insuranceObjectTypeDTO1.getId());
        assertThat(insuranceObjectTypeDTO1).isEqualTo(insuranceObjectTypeDTO2);
        insuranceObjectTypeDTO2.setId(2L);
        assertThat(insuranceObjectTypeDTO1).isNotEqualTo(insuranceObjectTypeDTO2);
        insuranceObjectTypeDTO1.setId(null);
        assertThat(insuranceObjectTypeDTO1).isNotEqualTo(insuranceObjectTypeDTO2);
    }
}
