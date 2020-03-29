package com.idealsoft.insurance.service;

import com.idealsoft.insurance.domain.InsuranceObject;
import com.idealsoft.insurance.repository.InsuranceObjectRepository;
import com.idealsoft.insurance.service.dto.InsuranceObjectDTO;
import com.idealsoft.insurance.service.mapper.InsuranceObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link InsuranceObject}.
 */
@Service
@Transactional
public class InsuranceObjectService {

    private final Logger log = LoggerFactory.getLogger(InsuranceObjectService.class);

    private final InsuranceObjectRepository insuranceObjectRepository;

    private final InsuranceObjectMapper insuranceObjectMapper;

    public InsuranceObjectService(InsuranceObjectRepository insuranceObjectRepository, InsuranceObjectMapper insuranceObjectMapper) {
        this.insuranceObjectRepository = insuranceObjectRepository;
        this.insuranceObjectMapper = insuranceObjectMapper;
    }

    /**
     * Save a insuranceObject.
     *
     * @param insuranceObjectDTO the entity to save.
     * @return the persisted entity.
     */
    public InsuranceObjectDTO save(InsuranceObjectDTO insuranceObjectDTO) {
        log.debug("Request to save InsuranceObject : {}", insuranceObjectDTO);
        InsuranceObject insuranceObject = insuranceObjectMapper.toEntity(insuranceObjectDTO);
        insuranceObject = insuranceObjectRepository.save(insuranceObject);
        return insuranceObjectMapper.toDto(insuranceObject);
    }

    /**
     * Get all the insuranceObjects.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InsuranceObjectDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InsuranceObjects");
        return insuranceObjectRepository.findAll(pageable)
            .map(insuranceObjectMapper::toDto);
    }

    /**
     * Get all the insuranceObjects.
     *
     * @param id1
     * @param id2
     * @param id3
     * @return the list of entities.
     */
    public Optional<InsuranceObjectDTO> findByIdentifications(String id1,String id2,String id3) {
        log.debug("Request to get all InsuranceObjects");

        Optional<InsuranceObjectDTO> result = insuranceObjectRepository.findByIdentifications(id1, id2, id3)
            .map(insuranceObjectMapper::toDto);
        if (!result.isPresent()) {
            InsuranceObject insuranceObject = new InsuranceObject();
            insuranceObject.setIdentifier1(id1);
            insuranceObject.setIdentifier2(id2);
            insuranceObject.setIdentifier3(id3);
            insuranceObject = insuranceObjectRepository.save(insuranceObject);
            result = Optional.of(insuranceObject).map(insuranceObjectMapper::toDto);
        }
        return result;
    }

    /**
     * Get one insuranceObject by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InsuranceObjectDTO> findOne(Long id) {
        log.debug("Request to get InsuranceObject : {}", id);
        return insuranceObjectRepository.findById(id)
            .map(insuranceObjectMapper::toDto);
    }

    /**
     * Delete the insuranceObject by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InsuranceObject : {}", id);
        insuranceObjectRepository.deleteById(id);
    }
}
