package com.idealsoft.insurance.repository;

import com.idealsoft.insurance.domain.InsuranceObject;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InsuranceObject entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InsuranceObjectRepository extends JpaRepository<InsuranceObject, Long> {
}
