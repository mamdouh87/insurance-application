package com.idealsoft.insurance.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InsuranceObjectTypeMapperTest {

    private InsuranceObjectTypeMapper insuranceObjectTypeMapper;

    @BeforeEach
    public void setUp() {
        insuranceObjectTypeMapper = new InsuranceObjectTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(insuranceObjectTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(insuranceObjectTypeMapper.fromId(null)).isNull();
    }
}
