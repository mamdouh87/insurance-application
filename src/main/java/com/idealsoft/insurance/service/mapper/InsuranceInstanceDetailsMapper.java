package com.idealsoft.insurance.service.mapper;


import com.idealsoft.insurance.domain.*;
import com.idealsoft.insurance.service.dto.InsuranceInstanceDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InsuranceInstanceDetails} and its DTO {@link InsuranceInstanceDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {InsuranceSpecificationMapper.class, InsuranceInstanceMapper.class})
public interface InsuranceInstanceDetailsMapper extends EntityMapper<InsuranceInstanceDetailsDTO, InsuranceInstanceDetails> {

    @Mapping(source = "specification.id", target = "specificationId")
    @Mapping(source = "insuranceInstance.id", target = "insuranceInstanceId")
    InsuranceInstanceDetailsDTO toDto(InsuranceInstanceDetails insuranceInstanceDetails);

    @Mapping(source = "specificationId", target = "specification")
    @Mapping(source = "insuranceInstanceId", target = "insuranceInstance")
    InsuranceInstanceDetails toEntity(InsuranceInstanceDetailsDTO insuranceInstanceDetailsDTO);

    default InsuranceInstanceDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        InsuranceInstanceDetails insuranceInstanceDetails = new InsuranceInstanceDetails();
        insuranceInstanceDetails.setId(id);
        return insuranceInstanceDetails;
    }
}
