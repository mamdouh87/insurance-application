package com.idealsoft.insurance.service;

import com.idealsoft.insurance.domain.InsuranceInstance;
import com.idealsoft.insurance.domain.InsuranceInstanceDetails;
import com.idealsoft.insurance.domain.InsuranceSpecification;
import com.idealsoft.insurance.repository.InsuranceInstanceDetailsRepository;
import com.idealsoft.insurance.repository.InsuranceInstanceRepository;
import com.idealsoft.insurance.service.dto.InsuranceInstanceDTO;
import com.idealsoft.insurance.service.dto.InsuranceObjectDTO;
import com.idealsoft.insurance.service.mapper.InsuranceInstanceMapper;
import com.idealsoft.insurance.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    InsuranceObjectService insuranceObjectService;

    @Autowired
    InsuranceSpecificationService insuranceSpecificationService;

    @Autowired
    InsuranceInstanceDetailsRepository insuranceInstanceDetailsRepository;

    @Autowired
    UserService userService;

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
    public InsuranceInstanceDTO createInsuranceInstanceWithDetails(InsuranceInstanceDTO insuranceInstanceDTO) {

        InsuranceInstance insuranceInstance = insuranceInstanceMapper.toEntity(insuranceInstanceDTO);
        insuranceInstance.setStatus(InsuranceInstance.Status.IN_COMPLETED.getValue());
        insuranceInstance.setInstanceDate(Instant.now());
        insuranceInstance.setUser(userService.getUserWithAuthorities().get());
        insuranceInstance = insuranceInstanceRepository.save(insuranceInstance);

        Optional<InsuranceObjectDTO> insuranceObject = insuranceObjectService.findOne(insuranceInstanceDTO.getInsuranceObjectId());
        if (!insuranceObject.isPresent()) {
            throw new BadRequestAlertException("insurance Object is not exist","","");
        }
        Long insuranceObjectTypeId = insuranceObject.get().getTypeId();
        List<InsuranceSpecification> specificationList = insuranceSpecificationService.findByInsuranceObjectType_Id(insuranceObjectTypeId);
        List<InsuranceInstanceDetails>  insuranceInstanceDetailsList = new ArrayList<>();
        for (InsuranceSpecification spec : specificationList) {
            InsuranceInstanceDetails temp = new InsuranceInstanceDetails();
            temp.setInsuranceInstance(insuranceInstance);
            temp.setSpecification(spec);
            insuranceInstanceDetailsList.add(temp);
        }
        insuranceInstanceDetailsRepository.saveAll(insuranceInstanceDetailsList);

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
