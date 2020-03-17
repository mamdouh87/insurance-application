package com.idealsoft.insurance.service.mapper;


import com.idealsoft.insurance.domain.*;
import com.idealsoft.insurance.service.dto.InsuranceSpecificationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InsuranceSpecification} and its DTO {@link InsuranceSpecificationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InsuranceSpecificationMapper extends EntityMapper<InsuranceSpecificationDTO, InsuranceSpecification> {



    default InsuranceSpecification fromId(Long id) {
        if (id == null) {
            return null;
        }
        InsuranceSpecification insuranceSpecification = new InsuranceSpecification();
        insuranceSpecification.setId(id);
        return insuranceSpecification;
    }
}
