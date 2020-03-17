package com.idealsoft.insurance.repository;

import com.idealsoft.insurance.domain.InsuranceInstance;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InsuranceInstance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InsuranceInstanceRepository extends JpaRepository<InsuranceInstance, Long> {
}
