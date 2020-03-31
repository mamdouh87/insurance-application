package com.idealsoft.insurance.service;

import com.idealsoft.insurance.domain.InsuranceObjectType;
import com.idealsoft.insurance.repository.InsuranceObjectTypeRepository;
import com.idealsoft.insurance.service.dto.InsuranceObjectTypeDTO;
import com.idealsoft.insurance.service.mapper.InsuranceObjectTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link InsuranceObjectType}.
 */
@Service
@Transactional
public class InsuranceObjectTypeService {

    private final Logger log = LoggerFactory.getLogger(InsuranceObjectTypeService.class);

    private final InsuranceObjectTypeRepository insuranceObjectTypeRepository;

    private final InsuranceObjectTypeMapper insuranceObjectTypeMapper;

    public InsuranceObjectTypeService(InsuranceObjectTypeRepository insuranceObjectTypeRepository, InsuranceObjectTypeMapper insuranceObjectTypeMapper) {
        this.insuranceObjectTypeRepository = insuranceObjectTypeRepository;
        this.insuranceObjectTypeMapper = insuranceObjectTypeMapper;
    }

    /**
     * Save a insuranceObjectType.
     *
     * @param insuranceObjectTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public InsuranceObjectTypeDTO save(InsuranceObjectTypeDTO insuranceObjectTypeDTO) {
        log.debug("Request to save InsuranceObjectType : {}", insuranceObjectTypeDTO);
        InsuranceObjectType insuranceObjectType = insuranceObjectTypeMapper.toEntity(insuranceObjectTypeDTO);
        insuranceObjectType = insuranceObjectTypeRepository.save(insuranceObjectType);
        return insuranceObjectTypeMapper.toDto(insuranceObjectType);
    }

    /**
     * Get all the insuranceObjectTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InsuranceObjectTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InsuranceObjectTypes");
        return insuranceObjectTypeRepository.findAll(pageable)
            .map(insuranceObjectTypeMapper::toDto);
    }

    /**
     * Get one insuranceObjectType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InsuranceObjectTypeDTO> findOne(Long id) {
        log.debug("Request to get InsuranceObjectType : {}", id);
        return insuranceObjectTypeRepository.findById(id)
            .map(insuranceObjectTypeMapper::toDto);
    }

    /**
     * Delete the insuranceObjectType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InsuranceObjectType : {}", id);
        insuranceObjectTypeRepository.deleteById(id);
    }
}
