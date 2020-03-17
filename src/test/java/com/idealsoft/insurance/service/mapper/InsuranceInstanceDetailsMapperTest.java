package com.idealsoft.insurance.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class InsuranceInstanceDetailsMapperTest {

    private InsuranceInstanceDetailsMapper insuranceInstanceDetailsMapper;

    @BeforeEach
    public void setUp() {
        insuranceInstanceDetailsMapper = new InsuranceInstanceDetailsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(insuranceInstanceDetailsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(insuranceInstanceDetailsMapper.fromId(null)).isNull();
    }
}
