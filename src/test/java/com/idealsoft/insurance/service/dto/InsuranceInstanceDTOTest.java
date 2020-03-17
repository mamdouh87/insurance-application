package com.idealsoft.insurance.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.idealsoft.insurance.web.rest.TestUtil;

public class InsuranceInstanceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceInstanceDTO.class);
        InsuranceInstanceDTO insuranceInstanceDTO1 = new InsuranceInstanceDTO();
        insuranceInstanceDTO1.setId(1L);
        InsuranceInstanceDTO insuranceInstanceDTO2 = new InsuranceInstanceDTO();
        assertThat(insuranceInstanceDTO1).isNotEqualTo(insuranceInstanceDTO2);
        insuranceInstanceDTO2.setId(insuranceInstanceDTO1.getId());
        assertThat(insuranceInstanceDTO1).isEqualTo(insuranceInstanceDTO2);
        insuranceInstanceDTO2.setId(2L);
        assertThat(insuranceInstanceDTO1).isNotEqualTo(insuranceInstanceDTO2);
        insuranceInstanceDTO1.setId(null);
        assertThat(insuranceInstanceDTO1).isNotEqualTo(insuranceInstanceDTO2);
    }
}
