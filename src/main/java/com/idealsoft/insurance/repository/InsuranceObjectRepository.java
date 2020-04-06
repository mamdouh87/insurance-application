package com.idealsoft.insurance.repository;

import com.idealsoft.insurance.domain.InsuranceObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the InsuranceObject entity.
 */

@Repository
public interface InsuranceObjectRepository extends JpaRepository<InsuranceObject, Long> {


    @Query("select obj from InsuranceObject obj where 1=1 and  (obj.identifier1= :id1) and  ( obj.identifier2= :id2) and  (obj.identifier3= :id3) and obj.type.code= :objectType")
    Optional<InsuranceObject> findByIdentifications(@Param("id1") String id1, @Param("id2") String id2, @Param("id3") String id3,@Param("objectType") String objectType);
}
