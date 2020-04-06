package com.idealsoft.insurance.service;

import com.idealsoft.insurance.domain.InsuranceInstance;
import com.idealsoft.insurance.domain.InsuranceInstanceDetails;
import com.idealsoft.insurance.repository.InsuranceInstanceDetailsRepository;
import com.idealsoft.insurance.service.dto.InsuranceInstanceDetailsDTO;
import com.idealsoft.insurance.service.mapper.InsuranceInstanceDetailsMapper;
import com.idealsoft.insurance.service.mapper.InsuranceInstanceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Autowired
    InsuranceInstanceService insuranceInstanceService;

    @Autowired
    InsuranceInstanceMapper insuranceInstanceMapper;

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
     * Save a insuranceInstanceDetails.
     *
     * @param insuranceInstanceDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public InsuranceInstanceDetailsDTO updateInsuranceInstanceDetailsWithImagesAndComment(InsuranceInstanceDetailsDTO insuranceInstanceDetailsDTO) {
        log.debug("Request to save InsuranceInstanceDetails : {}", insuranceInstanceDetailsDTO);
        InsuranceInstanceDetails insuranceInstanceDetails = insuranceInstanceDetailsRepository.getOne(insuranceInstanceDetailsDTO.getId());//insuranceInstanceDetailsMapper.toEntity(insuranceInstanceDetailsDTO);
        insuranceInstanceDetails.setImage(insuranceInstanceDetailsDTO.getImage());
        insuranceInstanceDetails.setImageContentType(insuranceInstanceDetailsDTO.getImageContentType());
        insuranceInstanceDetails.setStatus(insuranceInstanceDetailsDTO.getStatus());
        insuranceInstanceDetails.setComments(insuranceInstanceDetailsDTO.getComments());
        insuranceInstanceDetails = insuranceInstanceDetailsRepository.save(insuranceInstanceDetails);
        List<InsuranceInstanceDetails> incompleteInstanceDetails = insuranceInstanceDetailsRepository.findIncompleteInstanceDetails(insuranceInstanceDetailsDTO.getInsuranceInstanceId());
        if (incompleteInstanceDetails!=null && incompleteInstanceDetails.size()==0) {
            InsuranceInstance insuranceInstance = insuranceInstanceDetails.getInsuranceInstance();
            insuranceInstance.setStatus(InsuranceInstance.Status.COMPLETED.getValue());
            insuranceInstanceService.save(insuranceInstanceMapper.toDto(insuranceInstance));
        }
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
    public Optional<InsuranceInstanceDetailsDTO> getInsuranceInstanceDetailsBySpecificationId(Long specificationId) {
        log.debug("Request getInsuranceInstanceDetailsBySpecificationId by specification Id: {}", specificationId);
        return insuranceInstanceDetailsRepository.getInsuranceInstanceDetailsBySpecificationId(specificationId)
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
