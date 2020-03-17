package com.idealsoft.insurance.service;

import com.idealsoft.insurance.domain.InsuranceSpecification;
import com.idealsoft.insurance.repository.InsuranceSpecificationRepository;
import com.idealsoft.insurance.service.dto.InsuranceSpecificationDTO;
import com.idealsoft.insurance.service.mapper.InsuranceSpecificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link InsuranceSpecification}.
 */
@Service
@Transactional
public class InsuranceSpecificationService {

    private final Logger log = LoggerFactory.getLogger(InsuranceSpecificationService.class);

    private final InsuranceSpecificationRepository insuranceSpecificationRepository;

    private final InsuranceSpecificationMapper insuranceSpecificationMapper;

    public InsuranceSpecificationService(InsuranceSpecificationRepository insuranceSpecificationRepository, InsuranceSpecificationMapper insuranceSpecificationMapper) {
        this.insuranceSpecificationRepository = insuranceSpecificationRepository;
        this.insuranceSpecificationMapper = insuranceSpecificationMapper;
    }

    /**
     * Save a insuranceSpecification.
     *
     * @param insuranceSpecificationDTO the entity to save.
     * @return the persisted entity.
     */
    public InsuranceSpecificationDTO save(InsuranceSpecificationDTO insuranceSpecificationDTO) {
        log.debug("Request to save InsuranceSpecification : {}", insuranceSpecificationDTO);
        InsuranceSpecification insuranceSpecification = insuranceSpecificationMapper.toEntity(insuranceSpecificationDTO);
        insuranceSpecification = insuranceSpecificationRepository.save(insuranceSpecification);
        return insuranceSpecificationMapper.toDto(insuranceSpecification);
    }

    /**
     * Get all the insuranceSpecifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InsuranceSpecificationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InsuranceSpecifications");
        return insuranceSpecificationRepository.findAll(pageable)
            .map(insuranceSpecificationMapper::toDto);
    }

    /**
     * Get one insuranceSpecification by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InsuranceSpecificationDTO> findOne(Long id) {
        log.debug("Request to get InsuranceSpecification : {}", id);
        return insuranceSpecificationRepository.findById(id)
            .map(insuranceSpecificationMapper::toDto);
    }

    /**
     * Delete the insuranceSpecification by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InsuranceSpecification : {}", id);
        insuranceSpecificationRepository.deleteById(id);
    }
}
