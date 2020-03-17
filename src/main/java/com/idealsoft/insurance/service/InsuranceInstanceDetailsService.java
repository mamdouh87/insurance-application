package com.idealsoft.insurance.service;

import com.idealsoft.insurance.domain.InsuranceInstanceDetails;
import com.idealsoft.insurance.repository.InsuranceInstanceDetailsRepository;
import com.idealsoft.insurance.service.dto.InsuranceInstanceDetailsDTO;
import com.idealsoft.insurance.service.mapper.InsuranceInstanceDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link InsuranceInstanceDetails}.
 */
@Service
@Transactional
public class InsuranceInstanceDetailsService {

    private final Logger log = LoggerFactory.getLogger(InsuranceInstanceDetailsService.class);

    private final InsuranceInstanceDetailsRepository insuranceInstanceDetailsRepository;

    private final InsuranceInstanceDetailsMapper insuranceInstanceDetailsMapper;

    public InsuranceInstanceDetailsService(InsuranceInstanceDetailsRepository insuranceInstanceDetailsRepository, InsuranceInstanceDetailsMapper insuranceInstanceDetailsMapper) {
        this.insuranceInstanceDetailsRepository = insuranceInstanceDetailsRepository;
        this.insuranceInstanceDetailsMapper = insuranceInstanceDetailsMapper;
    }

    /**
     * Save a insuranceInstanceDetails.
     *
     * @param insuranceInstanceDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public InsuranceInstanceDetailsDTO save(InsuranceInstanceDetailsDTO insuranceInstanceDetailsDTO) {
        log.debug("Request to save InsuranceInstanceDetails : {}", insuranceInstanceDetailsDTO);
        InsuranceInstanceDetails insuranceInstanceDetails = insuranceInstanceDetailsMapper.toEntity(insuranceInstanceDetailsDTO);
        insuranceInstanceDetails = insuranceInstanceDetailsRepository.save(insuranceInstanceDetails);
        return insuranceInstanceDetailsMapper.toDto(insuranceInstanceDetails);
    }

    /**
     * Get all the insuranceInstanceDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InsuranceInstanceDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InsuranceInstanceDetails");
        return insuranceInstanceDetailsRepository.findAll(pageable)
            .map(insuranceInstanceDetailsMapper::toDto);
    }

    /**
     * Get one insuranceInstanceDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InsuranceInstanceDetailsDTO> findOne(Long id) {
        log.debug("Request to get InsuranceInstanceDetails : {}", id);
        return insuranceInstanceDetailsRepository.findById(id)
            .map(insuranceInstanceDetailsMapper::toDto);
    }

    /**
     * Delete the insuranceInstanceDetails by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InsuranceInstanceDetails : {}", id);
        insuranceInstanceDetailsRepository.deleteById(id);
    }
}
