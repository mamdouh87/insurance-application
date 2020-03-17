package com.idealsoft.insurance.web.rest;

import com.idealsoft.insurance.domain.InsuranceInstance;
import com.idealsoft.insurance.repository.InsuranceInstanceRepository;
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
 * REST controller for managing {@link com.idealsoft.insurance.domain.InsuranceInstance}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InsuranceInstanceResource {

    private final Logger log = LoggerFactory.getLogger(InsuranceInstanceResource.class);

    private static final String ENTITY_NAME = "insuranceInstance";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InsuranceInstanceRepository insuranceInstanceRepository;

    public InsuranceInstanceResource(InsuranceInstanceRepository insuranceInstanceRepository) {
        this.insuranceInstanceRepository = insuranceInstanceRepository;
    }

    /**
     * {@code POST  /insurance-instances} : Create a new insuranceInstance.
     *
     * @param insuranceInstance the insuranceInstance to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new insuranceInstance, or with status {@code 400 (Bad Request)} if the insuranceInstance has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/insurance-instances")
    public ResponseEntity<InsuranceInstance> createInsuranceInstance(@RequestBody InsuranceInstance insuranceInstance) throws URISyntaxException {
        log.debug("REST request to save InsuranceInstance : {}", insuranceInstance);
        if (insuranceInstance.getId() != null) {
            throw new BadRequestAlertException("A new insuranceInstance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InsuranceInstance result = insuranceInstanceRepository.save(insuranceInstance);
        return ResponseEntity.created(new URI("/api/insurance-instances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /insurance-instances} : Updates an existing insuranceInstance.
     *
     * @param insuranceInstance the insuranceInstance to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceInstance,
     * or with status {@code 400 (Bad Request)} if the insuranceInstance is not valid,
     * or with status {@code 500 (Internal Server Error)} if the insuranceInstance couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/insurance-instances")
    public ResponseEntity<InsuranceInstance> updateInsuranceInstance(@RequestBody InsuranceInstance insuranceInstance) throws URISyntaxException {
        log.debug("REST request to update InsuranceInstance : {}", insuranceInstance);
        if (insuranceInstance.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InsuranceInstance result = insuranceInstanceRepository.save(insuranceInstance);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, insuranceInstance.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /insurance-instances} : get all the insuranceInstances.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of insuranceInstances in body.
     */
    @GetMapping("/insurance-instances")
    public List<InsuranceInstance> getAllInsuranceInstances() {
        log.debug("REST request to get all InsuranceInstances");
        return insuranceInstanceRepository.findAll();
    }

    /**
     * {@code GET  /insurance-instances/:id} : get the "id" insuranceInstance.
     *
     * @param id the id of the insuranceInstance to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the insuranceInstance, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/insurance-instances/{id}")
    public ResponseEntity<InsuranceInstance> getInsuranceInstance(@PathVariable Long id) {
        log.debug("REST request to get InsuranceInstance : {}", id);
        Optional<InsuranceInstance> insuranceInstance = insuranceInstanceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(insuranceInstance);
    }

    /**
     * {@code DELETE  /insurance-instances/:id} : delete the "id" insuranceInstance.
     *
     * @param id the id of the insuranceInstance to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/insurance-instances/{id}")
    public ResponseEntity<Void> deleteInsuranceInstance(@PathVariable Long id) {
        log.debug("REST request to delete InsuranceInstance : {}", id);
        insuranceInstanceRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
