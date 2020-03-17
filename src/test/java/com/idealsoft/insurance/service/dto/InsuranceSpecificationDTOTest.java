package com.idealsoft.insurance.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.idealsoft.insurance.web.rest.TestUtil;

public class InsuranceSpecificationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsuranceSpecificationDTO.class);
        InsuranceSpecificationDTO insuranceSpecificationDTO1 = new InsuranceSpecificationDTO();
        insuranceSpecificationDTO1.setId(1L);
        InsuranceSpecificationDTO insuranceSpecificationDTO2 = new InsuranceSpecificationDTO();
        assertThat(insuranceSpecificationDTO1).isNotEqualTo(insuranceSpecificationDTO2);
        insuranceSpecificationDTO2.setId(insuranceSpecificationDTO1.getId());
        assertThat(insuranceSpecificationDTO1).isEqualTo(insuranceSpecificationDTO2);
        insuranceSpecificationDTO2.setId(2L);
        assertThat(insuranceSpecificationDTO1).isNotEqualTo(insuranceSpecificationDTO2);
        insuranceSpecificationDTO1.setId(null);
        assertThat(insuranceSpecificationDTO1).isNotEqualTo(insuranceSpecificationDTO2);
    }
}
