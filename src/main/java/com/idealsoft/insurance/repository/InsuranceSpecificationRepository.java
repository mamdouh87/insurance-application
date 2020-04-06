package com.idealsoft.insurance.repository;

import com.idealsoft.insurance.domain.InsuranceSpecification;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the InsuranceSpecification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InsuranceSpecificationRepository extends JpaRepository<InsuranceSpecification, Long> {

    List<InsuranceSpecification> findByInsuranceObjectType_Id(Long insuranceObjectTypeId);
}
