package com.idealsoft.insurance.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InsuranceObjectMapperTest {

    private InsuranceObjectMapper insuranceObjectMapper;

    @BeforeEach
    public void setUp() {
        insuranceObjectMapper = new InsuranceObjectMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(insuranceObjectMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(insuranceObjectMapper.fromId(null)).isNull();
    }
}
