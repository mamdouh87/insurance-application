package com.idealsoft.insurance.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.idealsoft.insurance.web.rest.TestUtil;

public class InsuranceInstanceDetailsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceInstanceDetailsDTO.class);
        InsuranceInstanceDetailsDTO insuranceInstanceDetailsDTO1 = new InsuranceInstanceDetailsDTO();
        insuranceInstanceDetailsDTO1.setId(1L);
        InsuranceInstanceDetailsDTO insuranceInstanceDetailsDTO2 = new InsuranceInstanceDetailsDTO();
        assertThat(insuranceInstanceDetailsDTO1).isNotEqualTo(insuranceInstanceDetailsDTO2);
        insuranceInstanceDetailsDTO2.setId(insuranceInstanceDetailsDTO1.getId());
        assertThat(insuranceInstanceDetailsDTO1).isEqualTo(insuranceInstanceDetailsDTO2);
        insuranceInstanceDetailsDTO2.setId(2L);
        assertThat(insuranceInstanceDetailsDTO1).isNotEqualTo(insuranceInstanceDetailsDTO2);
        insuranceInstanceDetailsDTO1.setId(null);
        assertThat(insuranceInstanceDetailsDTO1).isNotEqualTo(insuranceInstanceDetailsDTO2);
    }
}
