package com.idealsoft.insurance.service.mapper;


import com.idealsoft.insurance.domain.*;
import com.idealsoft.insurance.service.dto.InsuranceObjectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InsuranceObject} and its DTO {@link InsuranceObjectDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InsuranceObjectMapper extends EntityMapper<InsuranceObjectDTO, InsuranceObject> {


    @Mapping(target = "instances", ignore = true)
    @Mapping(target = "removeInstance", ignore = true)
    InsuranceObject toEntity(InsuranceObjectDTO insuranceObjectDTO);

    default InsuranceObject fromId(Long id) {
        if (id == null) {
            return null;
        }
        InsuranceObject insuranceObject = new InsuranceObject();
        insuranceObject.setId(id);
        return insuranceObject;
    }
}
