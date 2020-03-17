package com.idealsoft.insurance.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InsuranceInstanceMapperTest {

    private InsuranceInstanceMapper insuranceInstanceMapper;

    @BeforeEach
    public void setUp() {
        insuranceInstanceMapper = new InsuranceInstanceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(insuranceInstanceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(insuranceInstanceMapper.fromId(null)).isNull();
    }
}
