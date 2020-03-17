package com.idealsoft.insurance.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InsuranceSpecificationMapperTest {

    private InsuranceSpecificationMapper insuranceSpecificationMapper;

    @BeforeEach
    public void setUp() {
        insuranceSpecificationMapper = new InsuranceSpecificationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(insuranceSpecificationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(insuranceSpecificationMapper.fromId(null)).isNull();
    }
}
