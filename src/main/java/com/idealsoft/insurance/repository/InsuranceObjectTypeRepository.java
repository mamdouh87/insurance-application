package com.idealsoft.insurance.repository;

import com.idealsoft.insurance.domain.InsuranceObjectType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the InsuranceObjectType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InsuranceObjectTypeRepository extends JpaRepository<InsuranceObjectType, Long> {
    Optional<InsuranceObjectType> findByCode(String objectType);
}
