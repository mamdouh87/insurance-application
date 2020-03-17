package com.idealsoft.insurance.repository;

import com.idealsoft.insurance.domain.InsuranceSpecification;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InsuranceSpecification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InsuranceSpecificationRepository extends JpaRepository<InsuranceSpecification, Long> {
}
