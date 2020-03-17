package com.idealsoft.insurance.service;

import com.idealsoft.insurance.domain.InsuranceInstance;
import com.idealsoft.insurance.repository.InsuranceInstanceRepository;
import com.idealsoft.insurance.service.dto.InsuranceInstanceDTO;
import com.idealsoft.insurance.service.mapper.InsuranceInstanceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link InsuranceInstance}.
 */
@Service
@Transactional
public class InsuranceInstanceService {

    private final Logger log = LoggerFactory.getLogger(InsuranceInstanceService.class);

    private final InsuranceInstanceRepository insuranceInstanceRepository;

    private final InsuranceInstanceMapper insuranceInstanceMapper;

    public InsuranceInstanceService(InsuranceInstanceRepository insuranceInstanceRepository, InsuranceInstanceMapper insuranceInstanceMapper) {
        this.insuranceInstanceRepository = insuranceInstanceRepository;
        this.insuranceInstanceMapper = insuranceInstanceMapper;
    }

    /**
     * Save a insuranceInstance.
     *
     * @param insuranceInstanceDTO the entity to save.
     * @return the persisted entity.
     */
    public InsuranceInstanceDTO save(InsuranceInstanceDTO insuranceInstanceDTO) {
        log.debug("Request to save InsuranceInstance : {}", insuranceInstanceDTO);
        InsuranceInstance insuranceInstance = insuranceInstanceMapper.toEntity(insuranceInstanceDTO);
        insuranceInstance = insuranceInstanceRepository.save(insuranceInstance);
        return insuranceInstanceMapper.toDto(insuranceInstance);
    }

    /**
     * Get all the insuranceInstances.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InsuranceInstanceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InsuranceInstances");
        return insuranceInstanceRepository.findAll(pageable)
            .map(insuranceInstanceMapper::toDto);
    }

    /**
     * Get one insuranceInstance by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InsuranceInstanceDTO> findOne(Long id) {
        log.debug("Request to get InsuranceInstance : {}", id);
        return insuranceInstanceRepository.findById(id)
            .map(insuranceInstanceMapper::toDto);
    }

    /**
     * Delete the insuranceInstance by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InsuranceInstance : {}", id);
        insuranceInstanceRepository.deleteById(id);
    }
}
