package com.idealsoft.insurance.service.mapper;


import com.idealsoft.insurance.domain.*;
import com.idealsoft.insurance.service.dto.InsuranceInstanceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InsuranceInstance} and its DTO {@link InsuranceInstanceDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, InsuranceObjectMapper.class})
public interface InsuranceInstanceMapper extends EntityMapper<InsuranceInstanceDTO, InsuranceInstance> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "insuranceObject.id", target = "insuranceObjectId")
    InsuranceInstanceDTO toDto(InsuranceInstance insuranceInstance);

    @Mapping(source = "userId", target = "user")
    @Mapping(target = "details", ignore = true)
    @Mapping(target = "removeDetails", ignore = true)
    @Mapping(source = "insuranceObjectId", target = "insuranceObject")
    InsuranceInstance toEntity(InsuranceInstanceDTO insuranceInstanceDTO);

    default InsuranceInstance fromId(Long id) {
        if (id == null) {
            return null;
        }
        InsuranceInstance insuranceInstance = new InsuranceInstance();
        insuranceInstance.setId(id);
        return insuranceInstance;
    }
}
