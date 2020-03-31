package com.idealsoft.insurance.repository;

import com.idealsoft.insurance.domain.InsuranceObjectType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InsuranceObjectType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InsuranceObjectTypeRepository extends JpaRepository<InsuranceObjectType, Long> {
}
