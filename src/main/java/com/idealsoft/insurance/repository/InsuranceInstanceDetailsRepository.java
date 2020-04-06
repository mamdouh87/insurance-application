package com.idealsoft.insurance.repository;

import com.idealsoft.insurance.domain.InsuranceInstanceDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the InsuranceInstanceDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InsuranceInstanceDetailsRepository extends JpaRepository<InsuranceInstanceDetails, Long> {
    @Query(" select insd from InsuranceInstanceDetails insd  where insd.specification.id = :specificationId  ")
    Optional<InsuranceInstanceDetails> getInsuranceInstanceDetailsBySpecificationId(@Param("specificationId") Long specificationId);
    @Query(" select insd from InsuranceInstanceDetails insd  where insd.insuranceInstance.id = :instanceId  and insd.image is null")
    List<InsuranceInstanceDetails> findIncompleteInstanceDetails(@Param("instanceId") Long instanceId);
}
