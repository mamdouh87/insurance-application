package com.idealsoft.insurance.repository;

import com.idealsoft.insurance.domain.InsuranceInstanceDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InsuranceInstanceDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InsuranceInstanceDetailsRepository extends JpaRepository<InsuranceInstanceDetails, Long> {
}
