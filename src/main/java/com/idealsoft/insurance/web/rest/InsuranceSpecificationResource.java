package com.idealsoft.insurance.web.rest;

import com.idealsoft.insurance.domain.InsuranceSpecification;
import com.idealsoft.insurance.repository.InsuranceSpecificationRepository;
import com.idealsoft.insurance.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.idealsoft.insurance.domain.InsuranceSpecification}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InsuranceSpecificationResource {

    private final Logger log = LoggerFactory.getLogger(InsuranceSpecificationResource.class);

    private static final String ENTITY_NAME = "insuranceSpecification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InsuranceSpecificationRepository insuranceSpecificationRepository;

    public InsuranceSpecificationResource(InsuranceSpecificationRepository insuranceSpecificationRepository) {
        this.insuranceSpecificationRepository = insuranceSpecificationRepository;
    }

    /**
     * {@code POST  /insurance-specifications} : Create a new insuranceSpecification.
     *
     * @param insuranceSpecification the insuranceSpecification to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new insuranceSpecification, or with status {@code 400 (Bad Request)} if the insuranceSpecification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/insurance-specifications")
    public ResponseEntity<InsuranceSpecification> createInsuranceSpecification(@RequestBody InsuranceSpecification insuranceSpecification) throws URISyntaxException {
        log.debug("REST request to save InsuranceSpecification : {}", insuranceSpecification);
        if (insuranceSpecification.getId() != null) {
            throw new BadRequestAlertException("A new insuranceSpecification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InsuranceSpecification result = insuranceSpecificationRepository.save(insuranceSpecification);
        return ResponseEntity.created(new URI("/api/insurance-specifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /insurance-specifications} : Updates an existing insuranceSpecification.
     *
     * @param insuranceSpecification the insuranceSpecification to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceSpecification,
     * or with status {@code 400 (Bad Request)} if the insuranceSpecification is not valid,
     * or with status {@code 500 (Internal Server Error)} if the insuranceSpecification couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/insurance-specifications")
    public ResponseEntity<InsuranceSpecification> updateInsuranceSpecification(@RequestBody InsuranceSpecification insuranceSpecification) throws URISyntaxException {
        log.debug("REST request to update InsuranceSpecification : {}", insuranceSpecification);
        if (insuranceSpecification.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InsuranceSpecification result = insuranceSpecificationRepository.save(insuranceSpecification);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, insuranceSpecification.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /insurance-specifications} : get all the insuranceSpecifications.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of insuranceSpecifications in body.
     */
    @GetMapping("/insurance-specifications")
    public List<InsuranceSpecification> getAllInsuranceSpecifications() {
        log.debug("REST request to get all InsuranceSpecifications");
        return insuranceSpecificationRepository.findAll();
    }

    /**
     * {@code GET  /insurance-specifications/:id} : get the "id" insuranceSpecification.
     *
     * @param id the id of the insuranceSpecification to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the insuranceSpecification, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/insurance-specifications/{id}")
    public ResponseEntity<InsuranceSpecification> getInsuranceSpecification(@PathVariable Long id) {
        log.debug("REST request to get InsuranceSpecification : {}", id);
        Optional<InsuranceSpecification> insuranceSpecification = insuranceSpecificationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(insuranceSpecification);
    }

    /**
     * {@code DELETE  /insurance-specifications/:id} : delete the "id" insuranceSpecification.
     *
     * @param id the id of the insuranceSpecification to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/insurance-specifications/{id}")
    public ResponseEntity<Void> deleteInsuranceSpecification(@PathVariable Long id) {
        log.debug("REST request to delete InsuranceSpecification : {}", id);
        insuranceSpecificationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
