package com.idealsoft.insurance.service.mapper;


import com.idealsoft.insurance.domain.*;
import com.idealsoft.insurance.service.dto.InsuranceObjectTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InsuranceObjectType} and its DTO {@link InsuranceObjectTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InsuranceObjectTypeMapper extends EntityMapper<InsuranceObjectTypeDTO, InsuranceObjectType> {



    default InsuranceObjectType fromId(Long id) {
        if (id == null) {
            return null;
        }
        InsuranceObjectType insuranceObjectType = new InsuranceObjectType();
        insuranceObjectType.setId(id);
        return insuranceObjectType;
    }
}
